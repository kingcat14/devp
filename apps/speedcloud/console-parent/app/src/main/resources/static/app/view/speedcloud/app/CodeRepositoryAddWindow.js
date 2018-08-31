Ext.define('AM.view.speedcloud.app.CodeRepositoryAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeRepositoryAddWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.config.DevelopTypeStore'

    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
    }
    ,title: '添加新代码库'
    ,maximizable: true
    ,closeAction: 'hide'
    ,initComponent: function () {
        var me = this;

        var codeRepositoryTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        codeRepositoryTypeStore.proxy.isSynchronous = true;
        codeRepositoryTypeStore.proxy.extraParams={searchCondition:{configType:'CODEREPOSITORY-TYPE'}};
        codeRepositoryTypeStore.load();
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
                        ,blankText:'该字段为必填项'
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
                                    ,store: codeRepositoryTypeStore
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '类型'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'urlField'
                                    ,name: 'url'
                                    ,fieldLabel: 'url'
                                }


                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.config.DevelopTypeStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'developTypeField'
                                    ,name: 'developType'
                                    ,fieldLabel: '开发模式'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'usernameField'
                                    ,name: 'username'
                                    ,fieldLabel: '用户名'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'passwordField'
                                    ,name: 'password'
                                    ,fieldLabel: '密码'
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
                Ext.MsgUtil.show('操作成功', '保存代码库成功!');
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
        this.down('#typeField').getStore().reload();
        this.down('#developTypeField').getStore().reload();
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});