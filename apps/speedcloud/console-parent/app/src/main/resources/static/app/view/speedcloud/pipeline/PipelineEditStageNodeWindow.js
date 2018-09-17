Ext.define('AM.view.speedcloud.pipeline.PipelineEditStageNodeWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineEditStageNodeWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.pipeline.task.PipelineTaskStore'
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
            taskStore: Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskStore').load()
            ,taskParamStore: Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskParamStore').applyCondition({task:-999}).load()
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
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '名称'
                                    ,bind:'{node.name}'
                                }
                                ,{
                                    xtype:'combo'
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
                                    ,itemId: 'nodeTypeField'
                                    ,name: 'nodeType'
                                    ,fieldLabel: '类型'
                                    ,bind:'{node.nodeType}'
                                }
                                ,{xtype: 'label',text:'　'}
                                ,{
                                    xtype: 'label'
                                    ,dock: 'top'
                                    ,text:'请选择需要调用的任务'
                                }
                                ,{
                                    xtype:'panel'
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
                                ,{
                                    xtype:'grid'
                                    ,reference:'taskGrid'
                                    ,itemId:'taskGrid'
                                    ,width:'100%'
                                    ,bind:{store:'{taskStore}'}
                                    ,columns: [
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            ,text:'a'
                                            // ,flex:1
                                        }
                                    ]
                                    // ,selModel: {type:'checkboxmodel', mode:'SINGLE'}
                                    ,selType: 'checkboxmodel'
                                }
                                ,{xtype: 'label',text:'　'}
                                ,{
                                    xtype:'grid'
                                    ,columns: [
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            ,text:'参数名'
                                            ,flex:1
                                        }
                                    ]
                                    ,bind:{store:'{taskParamStore}'}
                                    ,selType: 'checkboxmodel'
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
                        {
                            xtype: 'tbfill'
                        },
                        {
                            xtype: 'button',
                            iconCls: 'accept',
                            text: '确定',
                            listeners: {
                                click: {
                                    fn: me.onSaveButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,
                        {
                            xtype: 'button',
                            iconCls: 'cancel',
                            text: '取消',
                            listeners: {
                                click: {
                                    fn: me.hide,
                                    scope: me
                                }
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
    ,onSaveButtonClick: function (button, e, options) {

        var me = this;

        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var selection = me.lookup('taskGrid').getSelection()

        if(selection.length > 0){

            var stageNode = this.getViewModel().get('stageNode')

            if(!stageNode){
                stageNode = Ext.create('AM.model.speedcloud.pipeline.PipelineStageNode');
                var stageNodeStore = me.getViewModel().get("stageNodeStore");
                stageNodeStore.add(stageNode);
            }

            stageNode.set('name', selection[0].get('name'));
            stageNode.set('nodeType', "TASK");
            stageNode.set('nodeId', selection[0].getId());

        }
        me.hide();

    }
    ,setModel: function (model) {

        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改阶段信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        // this.lookup('taskGrid').getSelectionModel().deselectAll()
        this.lookup('taskGrid');
        var stageNode = this.getViewModel().get('stageNode');
        if(stageNode){
            console.log(stageNode)
        }
    }
});