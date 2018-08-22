Ext.define('AM.view.devp.product.DevpPrdPersonEditWindow', {
    extend: 'Ext.window.Window',
    requires:[
    ],
    autoScroll: true,
    height: 350,
    width: 600,
    layout: {
        type: 'fit'
    },
    title: '修改产品干系人',
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
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'tidField',
                                            name: 'tid',
                                            fieldLabel: '租户编号'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '用户名称'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '用户描述'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'nexusRidField',
                                            name: 'nexusRid',
                                            fieldLabel: '关联元素编号'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'uidField',
                                            name: 'uid',
                                            fieldLabel: '用户编号'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'roleField',
                                            name: 'role',
                                            fieldLabel: '用户角色'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
                                            allowBlank:true,
                                            itemId: 'userTidField',
                                            name: 'userTid',
                                            fieldLabel: '用户租户编号'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'orgNameField',
                                            name: 'orgName',
                                            fieldLabel: '组织名称'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'createUcodeField',
                                            name: 'createUcode',
                                            fieldLabel: '创建用户代码'
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
                                            fieldLabel: '用户代码'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:true,
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '用户别名'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:false,
	                                        afterLabelTextTpl: [
	                                        '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
	                                        ],
                                            itemId: 'nexusTypeField',
                                            name: 'nexusType',
                                            fieldLabel: '关联元素类型'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
	                                        allowBlank:true,
                                            itemId: 'seqField',
                                            name: 'seq',
                                            fieldLabel: '顺序号'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:true,
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '用户类型'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:true,
                                            itemId: 'statusField',
                                            name: 'status',
                                            fieldLabel: '状态'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
	                                        allowBlank:true,
                                            itemId: 'orgRidField',
                                            name: 'orgRid',
                                            fieldLabel: '组织编号'
                                        }
                                        ,{
	                                        xtype: 'numberfield',
	                                        allowDecimals:false,
	                                        allowBlank:true,
                                            itemId: 'recordStateField',
                                            name: 'recordState',
                                            fieldLabel: '记录状态'
                                        }
                                        ,{
	                                        xtype: 'textfield',
	                                        allowBlank:true,
                                            itemId: 'modifyUcodeField',
                                            name: 'modifyUcode',
                                            fieldLabel: '修改用户代码'
                                        }
                                    ]
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
                Ext.MsgUtil.show('操作成功', '保存产品干系人成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.store.load({
                    callback: function (records, operation, success) {
                        if (success) {
                            Ext.MsgUtil.show('操作成功', '同步产品干系人列表成功');
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

        this.setTitle("修改产品干系人信息");
        if(model.phantom){
            this.setTitle("新增产品干系人信息");
        }
        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});