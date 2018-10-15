Ext.define('AM.view.platform.platform.application.ApplicationPasswordDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.application.ApplicationPasswordDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '应用密码详细信息'
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
                            itemId: 'appCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'appCode'
                            ,fieldLabel: '应用代码'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('appIdVO').name;
                            }
                        }
                        ,{
                            itemId: 'accessIdField'
                            ,padding: '5 0 0 5'
                            ,name: 'accessId'
                            ,fieldLabel: '访问ID'
                        }
                        ,{
                            itemId: 'accessKeyField'
                            ,padding: '5 0 0 5'
                            ,name: 'accessKey'
                            ,fieldLabel: '访问密码'
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