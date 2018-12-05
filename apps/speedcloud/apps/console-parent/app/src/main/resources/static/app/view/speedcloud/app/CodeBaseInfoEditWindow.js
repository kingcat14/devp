Ext.define('AM.view.speedcloud.app.CodeBaseInfoEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeBaseInfoEditWindow'
    ,requires:[
        'AM.store.speedcloud.app.CodeRepositoryStore'
    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '修改代码基本信息信息'
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
                                    ,store: Ext.create("AM.store.speedcloud.app.CodeRepositoryStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'url'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeRepositoryField'
                                    ,name: 'codeRepository'
                                    ,fieldLabel: '代码库'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'languageField'
                                    ,name: 'language'
                                    ,fieldLabel: '开发语言'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'languageLevelField'
                                    ,name: 'languageLevel'
                                    ,fieldLabel: '语言级别'
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
                Ext.MsgUtil.notification('操作成功', '保存代码基本信息成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);
            }
        });



    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改代码基本信息信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        this.down('#codeRepositoryField').getStore().reload();
      
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});