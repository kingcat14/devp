Ext.define('AM.view.speedcloud.app.AppDevelopConfigEditPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.AppDevelopConfigEditPanel'
    ,requires:[
        'AM.store.speedcloud.app.AppBaseInfoStore'
    ]
    ,autoScroll: true
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '开发配置'
    ,bind:{disabled:'{!record}'}
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    record: null
                    // ,phantom:true
                }
            }
        }, cfg)])
    }
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
                                {
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'developDatabaseField'
                                    ,name: 'developDatabase'
                                    ,fieldLabel: '开发环境DB'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'developDomainNameField'
                                    ,name: 'developDomainName'
                                    ,fieldLabel: '开发环境域名'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'testDatabaseField'
                                    ,name: 'testDatabase'
                                    ,fieldLabel: '测试环境DB'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'testDomainNameField'
                                    ,name: 'testDomainName'
                                    ,fieldLabel: '测试环境域名'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'productionDatabaseField'
                                    ,name: 'productionDatabase'
                                    ,fieldLabel: '生产环境DB'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'productionDomainNameField'
                                    ,name: 'productionDomainName'
                                    ,fieldLabel: '生产环境域名'
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
                    layout:'center',
                    items: [
                        {
                            xtype: 'button'
                            ,iconCls: 'fas fa-save'
                            ,scale:'medium'
                            ,text: '保存开发信息'
                            ,listeners: {
                                click: {
                                    fn: me.onSaveButtonClick
                                    ,scope: me
                                }
                            }
                        }
                    ]
                }
            ]
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
                Ext.MsgUtil.notification('操作成功', '保存应用开发配置成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
            }
        });



    }

    ,setModel: function (model) {
        if(!model){
            this.down('form').getForm().reset();
        }else{
            this.down('form').getForm().loadRecord(model);
        }

        this.getViewModel().set('record', model);
    }
});