Ext.define('AM.view.speedcloud.pipeline.jenkins.JobMappingEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JobMappingEditWindow'
    ,requires:[
        'AM.store.speedcloud.project.ProjectStore'
    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '修改任务映射信息'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10
                    ,layout: {
                      type: 'vbox'
                      ,pack: 'start'
                      ,align: 'stretch'
                    }
                  	,flex:1
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
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'projectField'
                                    ,name: 'project'
                                    ,fieldLabel: '所属产品'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskTypeField'
                                    ,name: 'taskType'
                                    ,fieldLabel: '类型'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
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
                                    ,itemId: 'jobInPlatformNameField'
                                    ,name: 'jobInPlatformName'
                                    ,fieldLabel: '任务或流水线名称'
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


        //将form中的数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.notification('操作成功', '保存任务映射成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);
            }
            ,failure: function(record, operation) {
                //console.log(operation.responseText)
                var response = operation.getResponse();
                var responseBody = Ext.decode(response.responseText)
                Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+responseBody.message);
            }
        });



    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改任务映射信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        this.down('#projectField').getStore().reload();
      
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});