Ext.define('AM.view.platform.platform.tenant.TenantAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.tenant.TenantAddWindow'
    ,requires:[
        'AM.store.platform.platform.tenant.TenantTypeStore'
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新租户'
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
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,

                    items: [
                        ,{
                            xtype: 'textfield',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'tenantCodeField',
                            name: 'tenantCode',
                            fieldLabel: '租户代号'

                        }
                        ,{
                            xtype: 'combobox',
                            store: Ext.create("AM.store.platform.platform.tenant.TenantTypeStore"),
                            typeAhead:false,
                            editable:false,
                            displayField:'name',
                            valueField:'id',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'tenantTypeField',
                            name: 'tenantType',
                            fieldLabel: '租户类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '租户名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'countryField',
                            name: 'country',
                            fieldLabel: '国家'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'provinceField',
                            name: 'province',
                            fieldLabel: '省份'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'cityField',
                            name: 'city',
                            fieldLabel: '市、县'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'addressField',
                            name: 'address',
                            fieldLabel: '详细地址'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'faxField',
                            name: 'fax',
                            fieldLabel: '传真'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'telephoneNoField',
                            name: 'telephoneNo',
                            fieldLabel: '联系电话'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'crmCodeField',
                            name: 'crmCode',
                            fieldLabel: 'CRM系统代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'prefixDomainNameField',
                            name: 'prefixDomainName',
                            fieldLabel: '域名前缀'

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
                            itemId: 'statusField',
                            name: 'status',
                            fieldLabel: '状态'

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

        //var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存租户成功!');
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

        this.setTitle("修改租户信息");
        if(model.phantom){
            this.setTitle("新增租户信息");
        }
        this.down('form').getForm().loadRecord(model);
    }

})