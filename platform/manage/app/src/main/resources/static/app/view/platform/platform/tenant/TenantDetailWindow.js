Ext.define('AM.view.platform.platform.tenant.TenantDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.tenant.TenantDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '租户详细信息'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,layout: {
                        type: 'anchor'
                    }
                    ,defaults: {
                        labelAlign: 'right'
                        ,xtype: 'displayfield'
                        ,padding: '5 0 0 5'
                        ,anchor: '100%'
                        ,labelWidth:150
                    }
                    ,items: [
                        ,{
                            itemId: 'tenantCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'tenantCode'
                            ,fieldLabel: '租户代号'
                        }
                        ,{
                            itemId: 'tenantTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'tenantType'
                            ,fieldLabel: '租户类型'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('tenantTypeVO').name;
                            }
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '租户名称'
                        }
                        ,{
                            itemId: 'countryField'
                            ,padding: '5 0 0 5'
                            ,name: 'country'
                            ,fieldLabel: '国家'
                        }
                        ,{
                            itemId: 'provinceField'
                            ,padding: '5 0 0 5'
                            ,name: 'province'
                            ,fieldLabel: '省份'
                        }
                        ,{
                            itemId: 'cityField'
                            ,padding: '5 0 0 5'
                            ,name: 'city'
                            ,fieldLabel: '市、县'
                        }
                        ,{
                            itemId: 'addressField'
                            ,padding: '5 0 0 5'
                            ,name: 'address'
                            ,fieldLabel: '详细地址'
                        }
                        ,{
                            itemId: 'faxField'
                            ,padding: '5 0 0 5'
                            ,name: 'fax'
                            ,fieldLabel: '传真'
                        }
                        ,{
                            itemId: 'telephoneNoField'
                            ,padding: '5 0 0 5'
                            ,name: 'telephoneNo'
                            ,fieldLabel: '联系电话'
                        }
                        ,{
                            itemId: 'crmCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'crmCode'
                            ,fieldLabel: 'CRM系统代码'
                        }
                        ,{
                            itemId: 'prefixDomainNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'prefixDomainName'
                            ,fieldLabel: '域名前缀'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '状态'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});