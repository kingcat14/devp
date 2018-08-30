Ext.define('AM.view.speedcloud.env.MachineDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.env.MachineDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '服务器详细信息'
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
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '名称'
                        }
                        ,{
                            itemId: 'ipAddressField'
                            ,padding: '5 0 0 5'
                            ,name: 'ipAddress'
                            ,fieldLabel: 'IP地址'
                        }
                        ,{
                            itemId: 'portField'
                            ,padding: '5 0 0 5'
                            ,name: 'port'
                            ,fieldLabel: '端口'
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