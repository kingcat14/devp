Ext.define('AM.view.monitor.app.ApplicationInstanceEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.app.ApplicationInstanceEditWindow'
    ,requires:[
        'AM.store.monitor.app.ApplicationStore'
    ],
    autoScroll: true,
    height: '60%',
    width: '60%',
    layout: {
        type: 'vbox'
    },
    title: '修改程序实例信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

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
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.monitor.app.ApplicationStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'appField'
                                    ,name: 'app'
                                    ,fieldLabel: '应用'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'hostField'
                                    ,name: 'host'
                                    ,fieldLabel: '主机'
                                }
                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'portField'
                                    ,name: 'port'
                                    ,fieldLabel: '端口'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: [
                                        [true,'是']
                                        ,[false,'否']
                                    ]
                                    ,value:true
                                    ,typeAhead:false
                                    ,editable:false
                                    ,hidden: true
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'aliveField'
                                    ,name: 'alive'
                                    ,fieldLabel: '运行中'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: [
                                        [true,'是']
                                        ,[false,'否']
                                    ]
                                    ,value:true
                                    ,typeAhead:false
                                    ,editable:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'alarmField'
                                    ,name: 'alarm'
                                    ,fieldLabel: '停运告警'
                                }
                                ,{
                                    xtype: 'datefield'
                                    ,format: 'Y-m-d'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'aliveTimeField'
                                    ,name: 'aliveTime'
                                    ,fieldLabel: '最近活跃时间'
                                }
                                ,{
                                    xtype: 'datefield'
                                    ,format: 'Y-m-d'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'detectionTimeField'
                                    ,name: 'detectionTime'
                                    ,fieldLabel: '最近检测时间'
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


        //将form中的数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.notification('操作成功', '保存程序实例成功!');
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

        this.setTitle("修改程序实例信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        this.down('#appField').getStore().reload();
       
       
       
       
       
       
       
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});