 Ext.define('AM.view.speedcloud.pipeline.PipelineEditStageNodeWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineEditStageNodeWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
         ,'AM.store.speedcloud.pipeline.task.PipelineTaskStore'
         ,'AM.store.speedcloud.pipeline.PipelineStageSelectableNodeStore'
         ,'AM.store.speedcloud.pipeline.PipelineStageSelectableNodeParamStore'
    ]
    ,autoScroll: true
    ,height: 500
    ,width: 500
    ,layout: {
        type: 'vbox'
    }
    ,title: '任务详情信息'
    ,bind:{title:'{stage.name}'}
    ,maximizable: true
    ,closeAction:'hide'
    ,viewModel:{
        data:{stageNode:null}
        ,stores:{
             selectableNodeStore: Ext.create('AM.store.speedcloud.pipeline.PipelineStageSelectableNodeStore')
             ,selectableNodeParamStore: Ext.create('AM.store.speedcloud.pipeline.PipelineStageSelectableNodeParamStore')
             ,taskParamStore: Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskParamStore').applyCondition({task:-999})
             ,stageNodeParamStore: Ext.create('AM.store.speedcloud.pipeline.PipelineStageNodeParamStore').applyCondition({pipelineStageNode:-999})
        }
    }
    ,referenceHolder:true
    ,initComponent: function () {
        var me = this;
        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10

                    ,width:'100%'
                    ,
                    items: [
                        {
                            xtype:'container'
                            ,layout: 'vbox'
                            ,defaults:{width:'100%'}
                            ,items:[
                                {
                                    xtype: 'textfield'
                                    ,reference:'nodeNameField'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,name: 'name'
                                    ,fieldLabel: '名称'
                                }
                                ,{
                                    xtype:'combo'
                                    ,reference:'objTypeField'
                                    ,store: Ext.create("AM.store.common.SimpleConfigStore").applyCondition({configType: "PIPELINETASK-TASKTYPE"}).load()
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:false
                                    ,forceSelection:true
                                    ,afterLabelTextTpl: []
                                    ,value:'COMPILE'
                                    ,name: 'objType'
                                    ,fieldLabel: '类型'
                                    ,listeners:{
                                        change:{fn:me.loadSelectableNode, scope:me}
                                    }

                                }
                                ,{xtype: 'label',text:'　'}


                                ,{
                                    xtype:'grid'
                                    ,border:true
                                    ,title:'请选择需要调用的任务'
                                    ,tools:[{xtype:'label',text:'找不到合适的任务?'}]
                                    ,reference:'selectableNodeGrid'
                                    ,width:'100%'
                                    ,bind:{store:'{selectableNodeStore}'}
                                    ,columns: [
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            ,flex:1
                                        }
                                    ]
                                    ,selModel: {type:'checkboxmodel', mode:'SINGLE'}
                                    // ,selType: 'checkboxmodel'
                                    ,listeners:{
                                        // selectionchange:me.loadSelectionParam
                                    }
                                    ,dockedItems: [
                                        {
                                            xtype: 'toolbar'
                                            ,dock: 'top'
                                            ,items:[
                                                {
                                                    xtype:'combo'
                                                    ,value:'本项目'
                                                    ,store:['本项目','全部项目']
                                                }
                                                ,{
                                                    xtype:'textfield'
                                                    ,emptyText:'请输入关键字'
                                                }
                                                ,{
                                                    xtype:'button'
                                                    // ,scale: 'medium'
                                                    ,iconCls:'x-fa fa-search'
                                                }
                                            ]
                                        }
                                    ]

                                }
                                ,{xtype: 'label',text:'　'}
                                ,{
                                    xtype:'grid'
                                    ,border:true
                                    ,collapsible:true
                                    ,title:'设置任务参数'
                                    ,reference:'selectableNodeParamGrid'
                                    ,columns: [
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            ,text:'节点参数'
                                            ,flex:1
                                        }
                                        ,{
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'defaultValue'
                                            ,text:'参数值'
                                            ,flex:1
                                            ,editor:'textfield'
                                        }
                                    ]
                                    ,bind:{store:'{selectableNodeParamStore}'}
                                    ,plugins: {
                                        ptype: 'cellediting', clicksToEdit: 1
                                    }

                                }
                            ]

                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        '->'
                        ,{
                            xtype: 'button'
                        ,iconCls:'fas fa-check-circle'
                            ,text: '确定'
                            ,listeners: {
                                click: {
                                    fn: me.onSaveButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            //,iconCls:'x-fa fa-times-circle'
                            ,iconCls:'fas fa-times-circle'

                            ,text: '取消'
                            ,listeners: {
                                click: {fn: me._hide, scope: me}
                            }
                        }
                    ]
                }
            ]
            ,listeners: {
                beforeshow: me.onBeforeShow
            }
        });

        me.callParent(arguments);
    }

    ,_hide:function(){
        var sourceCmp = this.getViewModel().get('sourceCmp');

        this.hide(sourceCmp);
     }
    ,onSaveButtonClick: function (button, e, options) {

        var me = this;

        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var selection = me.lookup('selectableNodeGrid').getSelection()

        if(selection.length > 0){

            var stageNode = this.getViewModel().get('stageNode')

            if(!stageNode){
                stageNode = Ext.create('AM.model.speedcloud.pipeline.PipelineStageNode');
                var stageNodeStore = me.getViewModel().get("stageNodeStore");
                stageNodeStore.add(stageNode);
            }

            var nodeName = me.lookup('nodeNameField').getValue();
            stageNode.set('name', Ext.isEmpty(nodeName)?selection[0].get('name'):nodeName);
            stageNode.set('objType', me.lookup('objTypeField').getValue());
            stageNode.set('objId', selection[0].getId());
            var paramList = [];
            var selectableNodeParamStore = this.getViewModel().getStore('selectableNodeParamStore');
            selectableNodeParamStore.each(function(param){
                paramList.push({name:param.get("name"), value:param.get("defaultValue"), viewOrder:param.get("viewOrder")})
            })
            stageNode.set("paramList", paramList);

        }
        me._hide();
    }
    ,onBeforeShow: function() {

        var me = this;
        var stageNode = this.getViewModel().get('stageNode');

        console.log(stageNode);

        me.lookup('nodeNameField').reset();
        me.lookup('objTypeField').reset();

        if(stageNode){
            this.lookup('nodeNameField').setValue(stageNode.get('name'))
            this.lookup('objTypeField').setValue(stageNode.get('objType'))
        }

        me.loadSelectableNode();
    }
    ,loadSelectableNode:function(){

        var me = this;
        var stageNode = this.getViewModel().get('stageNode');
         var selectableNodeParamGrid = this.lookup('selectableNodeParamGrid');

        this.getViewModel().getStore('selectableNodeParamStore').removeAll();
        this.lookup('selectableNodeGrid').getSelectionModel().deselectAll();
         selectableNodeParamGrid.setVisible(false);

        var selectableNodeGrid = this.lookup('selectableNodeGrid');
        selectableNodeGrid.un('rowclick', me.loadClickNodeParam);

        var selectableNodeParamStore = this.getViewModel().getStore('selectableNodeParamStore');

        var selectableNodeStore = this.getViewModel().getStore('selectableNodeStore').applyCondition({type:me.lookup('objTypeField').getValue()});
        selectableNodeStore.load({
            scope: me
            ,callback: function(records, operation, success) {
                if(success && stageNode) {
                    for (var i in records) {
                        if (records[i].getId() == stageNode.get('objId')) {

                            selectableNodeGrid.setSelection(records[i])

                            selectableNodeParamStore.removeAll();

                            var paramList = stageNode.get("paramList")
                            for(var i in paramList){
                                selectableNodeParamStore.add({name:paramList[i].name,defaultValue:paramList[i].value})
                                selectableNodeParamGrid.setVisible(true);
                            }
                        }
                    }
                }
                selectableNodeGrid.on('rowclick', me.loadClickNodeParam, me);
            }
        });
    }

    ,loadClickNodeParam: function(grid, stageNode){

        var me = this;
        var selectableNodeParamGrid = this.lookup('selectableNodeParamGrid');

        var selectableNodeParamStore = this.getViewModel().getStore('selectableNodeParamStore');

        if(!stageNode){
            selectableNodeParamStore.removeAll();
            return;
        }

        selectableNodeParamStore.applyCondition({nodeId:stageNode.getId()}).load({

            callback: function(records, operation, success) {
                selectableNodeParamGrid.setVisible((records && records.length > 0));
            }
        })
    }

});