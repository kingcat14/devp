Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskEditWindow'
    ,requires:[
        'AM.store.speedcloud.project.ProjectStore'
    ],
    autoScroll: true,
    height: '60%',
    width: '60%',
    layout: {
        type: 'vbox'
    },
    title: '修改任务信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        var pipelineTaskTaskTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTaskTaskTypeStore.proxy.isSynchronous = true;
        pipelineTaskTaskTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETASK-TASKTYPE'}};
        pipelineTaskTaskTypeStore.load();

        var pipelineTaskExecTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTaskExecTypeStore.proxy.isSynchronous = true;
        pipelineTaskExecTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETASK-EXECTYPE'}};
        pipelineTaskExecTypeStore.load();

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10

                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
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
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:false
                                    ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '任务名称'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: pipelineTaskTaskTypeStore
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskTypeField'
                                    ,name: 'taskType'
                                    ,fieldLabel: '任务类型'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: pipelineTaskExecTypeStore
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'execTypeField'
                                    ,name: 'execType'
                                    ,fieldLabel: '执行计划'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskStartTimeField'
                                    ,name: 'taskStartTime'
                                    ,fieldLabel: '执行开始时间'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskDayOfWeeksField'
                                    ,name: 'taskDayOfWeeks'
                                    ,fieldLabel: '执行日'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
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
                                }
                            ]

                        }
                        ,{

                            xtype: 'textarea',
                            anchor: '96% 70%',
                            itemId: 'descriptionField',
                            padding: '5 0 0 5',
                            name: 'description',
                            fieldLabel: '任务描述',
                            labelAlign: 'top'
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


        //将form中的数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存任务成功!');
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

        this.setTitle("修改任务信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
       
        this.down('#taskTypeField').getStore().reload();
       
        this.down('#execTypeField').getStore().reload();
       
       
       
       
        this.down('#projectField').getStore().reload();
       
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});