Ext.define('AM.view.product.product.DevpPrdLinePrdAddWindow', {
    extend: 'Ext.window.Window'
    ,requires:[
        'AM.store.product.product.DevpPrdPrdlineStore'
        ,'AM.store.product.product.DevpPrdProductStore'
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新产品所属产品线定义'
    ,maximizable: true
    ,closeAction: 'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,items: [
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
                                            allowBlank:true,
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '名称'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '描述'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.product.product.DevpPrdProductStore"),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            allowBlank:true,
                                            itemId: 'prdRidField',
                                            name: 'prdRid',
                                            fieldLabel: '产品编号'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '类型'
                                        }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'scopeField',
                                            name: 'scope',
                                            fieldLabel: '范围'
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
                                            allowBlank:true,
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            allowBlank:true,
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '别名'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.product.product.DevpPrdPrdlineStore"),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            allowBlank:true,
                                            itemId: 'lineRidField',
                                            name: 'lineRid',
                                            fieldLabel: '产品线编号'
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
                                            itemId: 'cmodifyUcodeField',
                                            name: 'cmodifyUcode',
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
                Ext.MsgUtil.show('操作成功', '保存产品所属产品线定义成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.store.load({
                    callback: function (records, operation, success) {
                        if (success) {
                            Ext.MsgUtil.show('操作成功', '同步产品所属产品线定义列表成功');
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

        this.setTitle("修改产品所属产品线定义信息");
        if(model.phantom){
            this.setTitle("新增产品所属产品线定义信息");
        }
        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});