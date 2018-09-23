Ext.define('AM.view.speedcloud.pipeline.PipelineEditController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.view.speedcloud.pipeline.PipelineEditStageWindow'
        ,'AM.store.speedcloud.pipeline.PipelineStageNodeStore'
	]
	,alias: 'controller.speedcloud.pipeline.PipelineEditController'

    ,onAddPipelineParamClick:function(){
        var modelConfig = {defaultValue:'defaultValue', type:'String'}
        var pipelineParam = Ext.create('AM.model.speedcloud.pipeline.PipelineParam', modelConfig);

        this.lookup('pipelineParamGrid').getStore().add(pipelineParam);

    }
    ,onDeletePipelineParamClick:function (grid, rowIndex, colIndex){

        grid.getStore().getAt(rowIndex).drop();

    }
    ,onAddStageButtonClick:function(button, event){

	    var me = this;
	    var buttonPanel = button.up('panel');

	    var pipelineStagePanel = this.lookup('pipelineStageListPanel');

        var index = 0;
        for(var i =0; i < pipelineStagePanel.items.getCount();i++){
            console.log(pipelineStagePanel.items.getAt(i).getId())
            if(pipelineStagePanel.items.getAt(i) == buttonPanel){
                index = i;
            }
        }

        console.log('index:'+index);



        var stageStore = this.getViewModel().getStore('stageStore');
        var stage = Ext.create('AM.model.speedcloud.pipeline.PipelineStage',{name:'Stage_'+stageStore.getCount(), execMode:'SERIALIZED'})
        stageStore.add(stage);
        me.addStagePanelGroup(stage, index+1);

    }
    ,onDeleteStageButtonClick:function(stagePanel, tool){

        var me = this;

	    Ext.MessageBox.confirm('Confirm', '你确定要删除这个阶段吗?', function(btn){
            console.log(btn)
            if(btn=='yes'){
                stagePanel.getViewModel().get('stage').drop();
                stagePanel.nextSibling().destroy();
                stagePanel.destroy();
            }

        }, this);
    }
    ,onDeleteStageNodeClick:function (grid, rowIndex, colIndex){
        Ext.MessageBox.confirm('Confirm', '你确定要删除这个任务码?', function(btn){
            if(btn=='yes'){
                grid.getStore().getAt(rowIndex).drop();
            }
        }, this);


    }
    ,onEditStageButtonClick:function(stagePanel, tool){

	    var me = this;

	    var stage = stagePanel.getViewModel().get('stage')

	    var stageWindow = me.lookup('stageWindow');
        stageWindow.getViewModel().set('stage', stage);

        stageWindow.show(tool)
	}
    ,onAddStageNodeButtonClick:function(button){

        var me = this;

        var stagePanel = button.up('panel').up('panel');
        var stageNodeStore = stagePanel.getViewModel().getStore('stageNodeStore');


        me.showStageNodeEditWindow(button, stageNodeStore);
    }
    ,onEditStageNodeClick:function (grid, rowIndex, colIndex){

        var me = this;

        var stageNode = grid.getStore().getAt(rowIndex);

        var stagePanel = grid.up('grid').up('panel');
        var stageNodeStore = stagePanel.getViewModel().getStore('stageNodeStore');


        me.showStageNodeEditWindow(grid, stageNodeStore, stageNode);

    }
    ,showStageNodeEditWindow:function (sourceCmp, stageNodeStore, stageNode) {

	    var me = this;
        var stageNodeWindow = me.lookup('stageNodeWindow');
        stageNodeWindow.getViewModel().set('stageNodeStore', stageNodeStore);
        stageNodeWindow.getViewModel().set('stageNode', stageNode);
        stageNodeWindow.getViewModel().set('sourceCmp', sourceCmp);
        stageNodeWindow.show(sourceCmp)
    }
    ,onPipelineSaveButtonClick:function(){

	    var me = this;
        var stageList = [];
        var paramList = [];


	    var pipeline = this.getViewModel().get('record');
        pipeline.set("stageList", stageList);
        pipeline.set("paramList", paramList);

        var pipelineStagePanel = this.lookup('pipelineStageListPanel');
        console.log(pipelineStagePanel.items.getCount())

        for(var i =0; i < pipelineStagePanel.items.getCount(); i++){

            var panel = pipelineStagePanel.items.getAt(i);

            if(panel.getViewModel() && panel.getViewModel().get('stage')){

                var stage = panel.getViewModel().get('stage')
                var stageNodeStore = panel.getViewModel().getStore('stageNodeStore');

                var stageJson = stage.getData();

                stageJson.nodeList = [];
                stageNodeStore.each(function(node){
                    console.log(node)
                    console.log(node.getData())
                    stageJson.nodeList.push(node.getData());

                });

                stageList.push(stageJson);
            }
        }

        if(stageList.length == 0){
            Ext.MessageBox.alert('提交失败', '至少需要一个阶段');
            return;
        }


        var paramStore = this.getViewModel().getStore('paramStore')
        paramStore.each(function (param) {
            paramList.push(param.getData())
        })

        console.log(pipeline)

        pipeline.save({
            success: function () {
                Ext.MsgUtil.show('操作成功', '保存任务成功!');
                //me.getView().destroy();
            }
        });
    }

    ,addStagePanelGroup:function(stage, index){
        var me = this;
        var pipelineStagePanel = me.lookup('pipelineStageListPanel');

        //加号
        pipelineStagePanel.insert(index, {
            xtype:'panel'
            // ,layout:'fit'
            ,width:50
            ,dockedItems: [
                {
                    xtype: 'toolbar'
                    , dock: 'bottom'
                    , layout:'fit'
                    , items:{
                        xtype: 'button'
                        // ,scale:'medium'
                        ,iconCls: 'fas fa-plus-circle orange'
                        ,style:{color:'blue'}
                        ,handler:'onAddStageButtonClick'
                    }
                }]
        })

        var stagePanel = me.createStagePanel(stage)
        pipelineStagePanel.insert(index, stagePanel)
    }
    ,createStagePanel:function(stage){

        var me = this;
        var panel = {
            xtype:'panel'
            , title:'Stage'
            , width:300
            , frame:true
            , layout:'vbox'
            , bind:{title:'{stage.name}' }
            , viewModel:{
                data:{stage:stage}
                ,stores:{
                    stageNodeStore:Ext.create('AM.store.speedcloud.pipeline.PipelineStageNodeStore').applyCondition({stage:(!stage.phantom)?stage.get('id')+"":-999}).load()
                }
            }
            , tools:[{type:'gear', callback:'onEditStageButtonClick'},{ iconCls:'x-fa fa-minus-circle', callback:'onDeleteStageButtonClick'}]
            , items:[
                {
                    xtype:'grid'
                    ,width:'100%'
                    ,bind:{store:'{stageNodeStore}'}
                    ,columns:[
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,flex:1
                            ,editor: {
                                xtype: 'textfield'
                            }
                        }
                        , {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:60
                            , items: [
                                {
                                    iconCls: 'x-fa fa-pencil'
                                    , tooltip: '修改'
                                    , handler: 'onEditStageNodeClick'
                                }
                                ,'-'
                                ,{
                                    // iconCls: 'delete'
                                    iconCls:'x-fa fa-trash'
                                    , tooltip: '删除'
                                    , handler: 'onDeleteStageNodeClick'
                                }]
                        }
                    ]
                    ,dockedItems: [
                        {
                            xtype: 'toolbar'
                            ,dock: 'bottom'
                            , layout:'fit'
                            ,items:{
                                xtype: 'button'
                                // ,scale:'medium'
                                ,iconCls: 'x-fa fa-plus-square orange'
                                ,text: '添加任务'
                                ,bodyCls:'red'
                                ,listeners: {
                                    click: 'onAddStageNodeButtonClick'
                                }
                            }
                        }
                    ]
                }
            ]

        }
        return panel;
    }

    ,initPipelinePanel: function(){
        var me = this;

        var stageStore = this.getViewModel().getStore('stageStore');
        stageStore.on('datachanged', function(store, records){

            me.lookup('endStageContainer').getViewModel().set('stageCount', stageStore.getCount());
        })
        stageStore.on('load', function(store, records){

            for(var i in records){
                me.addStagePanelGroup(records[i], i*2 + 2)
            }
        })
    }
})