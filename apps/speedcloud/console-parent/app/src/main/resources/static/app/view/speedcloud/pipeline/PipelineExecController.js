Ext.define('AM.view.speedcloud.pipeline.PipelineExecController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.store.speedcloud.pipeline.PipelineStageNodeStore'
        ,'AM.model.speedcloud.pipeline.exec.PipelineCustomExecInstance'
        ,'AM.model.speedcloud.pipeline.exec.PipelineExecInstance'
	]
	,alias: 'controller.speedcloud.pipeline.PipelineExecController'
    ,onPipelineContinueExecButtonClick:function(){
        var me = this;
        var pipelineStagePanel = me.lookup('pipelineStageListPanel');
        pipelineStagePanel.unmask()
    }
    ,addStagePanelGroup:function(stage, index){
        var me = this;

        var pipelineStagePanel = me.lookup('pipelineStageListPanel');

        var stagePanel = me.createStagePanel(stage)
        stagePanel.title = (index+1)+":"+stage.get('name')
        console.log('stagePanel.title:'+stagePanel.title)

        pipelineStagePanel.insert(index, stagePanel)
    }
    ,createStagePanel:function(stage){

        var me = this;
        var panel = {
            xtype:'panel'
            , width:300
            , frame:true
            , layout:'vbox'
            , margin:'0 0 0 20'
            , viewModel:{
                data:{stage:stage}
                ,stores:{
                    stageNodeStore:Ext.create('AM.store.speedcloud.pipeline.PipelineStageNodeStore').applyCondition({stage:stage.get('id')}).load()
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
                            ,width:35
                            ,dataIndex:'status'
                            ,renderer:function(value, metaData, record, rowIndex, colIndex, store, view){
                                var status = record.get('status');
                                var result = record.get('result');
                                console.log(record.get('name')+":"+status+"-"+result);
                                if(status == 'RUNNING'||status == 'PREPARED'){
                                    return '<i class="fas fa-spinner"></i>';
                                }
                                if(status == 'FINISH' && result == 'SUCCESS'){
                                    metaData.tdAttr = 'bgcolor="green"'
                                    return '<i class="fas fa-check"></i>'
                                }
                                if(status == 'FINISH' && result != 'SUCCESS'){
                                    metaData.tdAttr = 'bgcolor="red"'
                                    return '<i class="fas fa-times"></i>'
                                }
                                metaData.tdAttr = ''
                                return '';
                            }
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,flex:1
                            ,editor: {
                                xtype: 'textfield'
                            }
                            ,renderer: function (value, metaData) {
                                // metaData.tdAttr = 'bgcolor="red"';
                                return value;
                            }
                        }

                        ,{
                            xtype: 'gridcolumn'
                            ,width:35
                            ,renderer:function(){
                                return '<i class="far fa-eye"></i>'
                            }
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:25,align:'center'
                            , items: [{
                                    iconCls: 'fas fa-clipboard-list'
                                    , tooltip: '详情'
                                    , handler: 'onEditStageNodeClick'
                                    , padding: '0 10 0 0'
                                }]
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:25
                            ,align:'center'
                            , items: [{
                                    // iconCls: 'delete'
                                    iconCls:'far fa-caret-square-right'
                                    , tooltip: '单独执行'
                                    , handler: 'onDeleteStageNodeClick'
                            }]
                        }
                    ]
                    ,selModel: {type:'checkboxmodel',ignoreRightMouseSelection:true, mode:'SIMPLE'}
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

            for(var i = 0; i<records.length; i++){
                me.addStagePanelGroup(records[i], i+1)
            }
        })
    }

    ,onPipelineExecButtonClick:function () {
        var me = this;

        /*
         * 1.mask panel
         * 2.创建流水线执行任务
         * 3.刷新任务执行状态
         * */

        //1.
        var pipelineStagePanel = me.lookup('pipelineStageListPanel');
        pipelineStagePanel.mask('准备中......')

        //2.
        var customExecInstance = me.createCustomExec();
        if(customExecInstance == null){
            pipelineStagePanel.unmask()
            return ;
        }
        console.log(customExecInstance)
        customExecInstance.save({
            failure: function(record, operation) {
                pipelineStagePanel.unmask();
            }
            ,success: function(record, operation) {
                pipelineStagePanel.unmask()
                me.flushExecStatus(record);
            }
        })
    }

    ,createCustomExec:function(){
	    var me = this;
	    var pipeline = me.getViewModel().get('pipeline');
        /*
         * 1.找到所有stagePanel (pipelineStagePanel 中 index > 0 and index < length - 1)
         * 2.创建customExec
         */

        //1.
        var pipelineStagePanel = me.lookup('pipelineStageListPanel');
        var items = pipelineStagePanel.getLayout().getLayoutItems();

        var customExec = AM.model.speedcloud.pipeline.exec.PipelineCustomExecInstance.create({nodeId:pipeline.getId(), nodeType:'PIPELINE', subNodeList:[]});

        for(var i = 1; i< items.length -1;i++){
            var stage = items[i]
            console.log(stage.getTitle());
            var selection = stage.down('grid').getSelection();
            if(selection && selection.length > 0){
                var stage = {nodeId:stage.getViewModel().get('stage').getId(), nodeType:'STAGE', subNodeList:[]}
                for(var ii in selection){
                    selection[ii].set('status', 'RUNNING');
                    console.log(selection[ii]);
                    stage.subNodeList.push({nodeId:selection[ii].get("id"), nodeType:selection[ii].get('nodeType')});
                }
                customExec.get('subNodeList').push(stage);
            }
        }

        // if(customExec.get('subNodeList').length == 0){
        //     Ext.MessageBox.alert('不能开始执行', '至少选择一个任务节点');
        //     return null;
        // }


        return customExec;

    }
    ,flushExecStatus: function(customExecInstance){
        var me = this;
	    /*
         * 1.查询任务执行状态
         * 2.刷新页面执行状态
         * 3.
         */
        var pipelineStagePanel = me.lookup('pipelineStageListPanel');
        var items = pipelineStagePanel.getLayout().getLayoutItems();

        var tasks = []
        for(var i = 1; i< items.length -1;i++){
            var stage = items[i]

            var selection = stage.down('grid').getSelection();
            if(selection && selection.length > 0){

                for(var ii in selection){
                    tasks[selection[ii].getId()] = selection[ii];
                }

            }
        }

        pipelineStagePanel.mask('开始执行');
        var instance = AM.model.speedcloud.pipeline.exec.PipelineExecInstance.create({id:customExecInstance.getId()});

        var runner = new Ext.util.TaskRunner();

        var runCount = 0;

        var task = runner.newTask({
            run: function() {
                instance.load({
                    success: function(record, operation) {
                        runCount++;
                        if(record.get("status") == 'FINISH' ){
                            Ext.MessageBox.alert("执行完毕", "执行结果:"+record.get("result"));
                            pipelineStagePanel.unmask();
                            task.stop();
                        }

                        if(runCount > 100){
                            Ext.MessageBox.alert("执行超时", "执行超时");
                            pipelineStagePanel.unmask();
                            task.stop();
                        }

                        var nodeList = record.get("nodeList");
                        for(var i in nodeList){
                            var node = nodeList[i];
                            console.log(node)
                            // console.log(Ext.encode(node));
                            if(node.nodeType == "TASK"){
                                // console.log('=============='+(node.name))

                            }
                            if(node.nodeType == "TASK" && (node.status =='PREPARED' || node.status =='RUNNING')){
                                pipelineStagePanel.mask(node.name+":"+node.status);
                            }
                        }
                    }
                })
            }
            ,interval: 1000
        });
        task.start();

    }
})