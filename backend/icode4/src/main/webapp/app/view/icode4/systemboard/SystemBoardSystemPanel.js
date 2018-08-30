Ext.define('AM.view.icode4.systemboard.SystemBoardSystemPanel', {
    extend: 'Ext.panel.Panel',
    requires:[
    ],
    autoScroll: true,
    layout: {
        type: 'fit'
    },
    title: '系统信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10,
                    fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'column'
                            },
                            items: [
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    },
                                    items: [
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            itemId: 'idField',
                                            name: 'id',
                                            fieldLabel: 'Label'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '系统名称'
                                        }
	                                    ,{
		                                    xtype: 'textfield',
		                                    allowBlank:false,
		                                    afterLabelTextTpl: [
			                                    '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
		                                    ],
		                                    itemId: 'basePackageField',
		                                    name: 'basePackage',
		                                    fieldLabel: '基础包名称'
	                                    }
                                    ]
                                },
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    },
                                    items: [
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            itemId: 'versionField',
                                            name: 'version',
                                            fieldLabel: 'Label'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:false,
	                                        afterLabelTextTpl: [
	                                        '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
	                                        ],
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '系统代码'
                                        }
                                    ]
                                }
                            ]
                        }
	                    ,{

		                    xtype: 'textarea',
		                    anchor: '98% 70%',
		                    itemId: 'descriptionField',
		                    padding: '5 0 0 5',
		                    name: 'description',
		                    fieldLabel: '描述',
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
        });

        me.callParent(arguments);
    },

    onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        console.log('before record:'+record.get('id'))
        record.save({
            success: function (newRecord) {
                console.log('after record:'+record.get('id'))
                Ext.MsgUtil.show('操作成功', '保存系统信息成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
            }
            ,failure: function(record, operation) {
                var response = Ext.decode(operation.getError().response.responseText)
                Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+response.exception+"<br/>"+response.message);
            }
        });

    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改系统信息");
        if(model.phantom){
            this.setTitle("新增系统信息");
        }
        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});