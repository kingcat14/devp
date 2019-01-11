Ext.define('AM.view.speedcloud.pipeline.jenkins.JobMappingAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JobMappingAddWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.project.ProjectStore'

    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
    }
    ,title: '添加新任务映射'
    ,maximizable: true
    ,closeAction: 'hide'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    projectStore: Ext.create("AM.store.speedcloud.project.ProjectStore")
                    ,appEnvConfigStore: Ext.create("AM.store.speedcloud.env.AppEnvConfigStore").applyCondition({project:-999}).load()
                    ,pipelineStore: Ext.create("AM.store.speedcloud.pipeline.PipelineStore")
                    ,pipelineTaskStore: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskStore").applyCondition({project:-999}).load()
                }
            }
        }, cfg)])
    }
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,anchor: '96%'
                    }
                    ,items: [
                        {
                            xtype:'container'
                            ,anchor: '96% 70%'
                            ,layout: {
                                type: 'table',
                                columns: 3,
                                tableAttrs: {
                                    style: {
                                        width: '100%'
                                    }
                                }
                            }
                            ,defaults:{width:'100%'}
                            ,items:[

                                ,{
                                    xtype: 'combobox'
                                    // ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                                    ,bind:{store: '{projectStore}'}
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'projectField'
                                    ,name: 'project'
                                    ,fieldLabel: '所属产品'
                                    ,listeners:{
                                        change:function(field, newValue, oldValue){

                                            var type = me.down('#taskTypeField').getValue();
                                            if(!type){
                                                type = 'TASK';
                                                me.down('#taskTypeField').setValue(type);
                                            }

                                            me.onConditionChange(newValue, type);

                                        }
                                    }
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskTypeField'
                                    ,name: 'taskType'
                                    ,fieldLabel: '类型'
                                    ,value:'TASK'
                                    ,store:[['TASK','任务'],['PIPELINE','流水线']]
                                    ,listeners:{
                                        change:function(field, newValue, oldValue){

                                            var projectId = me.down('#projectField').getValue();
                                            if(!projectId){
                                                Ext.MessageBox.alert('请选择所属产品', '请选择所属产品');
                                                return;
                                            }

                                        me.onConditionChange(projectId, newValue);

                                        }
                                    }
                                }

                                ,{
                                    xtype: 'combobox'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,itemId: 'jobInPlatformField'
                                    ,name: 'jobInPlatform'
                                    ,fieldLabel: '任务或流水线'
                                    
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'jobInJenkinsNameField'
                                    ,name: 'jobInJenkinsName'
                                    ,fieldLabel: 'Jenkins中任务名称'
                                    
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
                            iconCls: 'fas fa-save',
                            text: '确定',
                            listeners: {
                                click: {
                                    fn: me.onSaveButtonClick,
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
    },

    onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        //var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.notification('操作成功', '保存任务映射成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);

            }
        });
    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        this.down('#projectField').getStore().reload();
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
    ,onConditionChange:function(projectId, type){
        var me = this;
        var targetStore = me.getViewModel().getStore('pipelineTaskStore');
        if(type == 'PIPELINE'){
            targetStore = me.getViewModel().getStore('pipelineStore');
        }
        me.down('#jobInPlatformField').setStore(targetStore)
        targetStore.applyCondition({project:projectId}).load({callback: function(records, operation, success) {
            if(success) {
                me.down('#jobInPlatformField').clearValue();
                if(records && records.length > 0){
                    me.down('#jobInPlatformField').select(records[0])
                }
            }
        }})
    }
});