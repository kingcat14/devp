Ext.define('AM.view.speedcloud.app.AppDevelopConfigDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.AppDevelopConfigDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '应用开发配置详细信息'
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
                            itemId: 'appField'
                            ,padding: '5 0 0 5'
                            ,name: 'app'
                            ,fieldLabel: '应用'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('appVO')?record.get('appVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'developDatabaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'developDatabase'
                            ,fieldLabel: '开发环境DB'
                        }
                        ,{
                            itemId: 'developDomainNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'developDomainName'
                            ,fieldLabel: '开发环境域名'
                        }
                        ,{
                            itemId: 'testDatabaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'testDatabase'
                            ,fieldLabel: '测试环境DB'
                        }
                        ,{
                            itemId: 'testDomainNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'testDomainName'
                            ,fieldLabel: '测试环境域名'
                        }
                        ,{
                            itemId: 'productionDatabaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'productionDatabase'
                            ,fieldLabel: '生产环境DB'
                        }
                        ,{
                            itemId: 'productionDomainNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'productionDomainName'
                            ,fieldLabel: '生产环境域名'
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