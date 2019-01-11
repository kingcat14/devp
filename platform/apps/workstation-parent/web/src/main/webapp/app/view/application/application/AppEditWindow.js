Ext.define('AM.view.application.application.AppEditWindow', {
    extend: 'Ext.window.Window',
    requires:[
        'AM.store.application.application.AppCategoryStore'
    ],
    autoScroll: true,
    height: 350,
    width: 600,
    layout: {
        type: 'fit'
    },
    title: '修改应用',
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
                                            fieldLabel: '应用名称'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'tenantIdField',
                                            name: 'tenantId',
                                            fieldLabel: '所属租户'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            value:true,
                                            typeAhead:false,
                                            editable:false,
                                            allowBlank:true,
                                            itemId: 'enableField',
                                            name: 'enable',
                                            fieldLabel: '已启用'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'urlField',
                                            name: 'url',
                                            fieldLabel: '应用链接'
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
                                            fieldLabel: '应用代码'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.application.application.AppCategoryStore", {asynchronousLoad:false, autoLoad:true, pageSize:500}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
	                                        allowBlank:true,
                                            itemId: 'appCategoryIdField',
                                            name: 'appCategoryId',
                                            fieldLabel: '应用类别'
                                        }
                                        ,{
	                                        xtype: 'datefield',
	                                        format: 'Y-m-d',
	                                        allowBlank:true,
                                            itemId: 'onBoardTimeField',
                                            name: 'onBoardTime',
                                            fieldLabel: '上架时间'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                            [true,'是'],
                                            [false,'否']
                                            ],
                                            value:true,
                                            typeAhead:false,
                                            editable:false,
	                                        allowBlank:true,
                                            itemId: 'visibleField',
                                            name: 'visible',
                                            fieldLabel: '可见'
                                        }
                                    ]
                                }
                            ]
                        }
                        ,{

                            xtype: 'textarea',
                            anchor: '98% 70%',
                            itemId: 'labelField',
                            padding: '5 0 0 5',
                            name: 'label',
                            fieldLabel: '标签',
                            labelAlign: 'top'
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
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存应用成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.store.load({
                    callback: function (records, operation, success) {
                        if (success) {
                            Ext.MsgUtil.show('操作成功', '同步应用列表成功');
                        }
                    }
                });
            }
        });


        this.hide(this.targetComp);
    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改应用信息");
        if(model.phantom){
            this.setTitle("新增应用信息");
        }
        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});