Ext.define('AM.view.speedcloud.env.EnvMachineDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.env.EnvMachineDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '环境设备关联详细信息'
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
                            itemId: 'evnField'
                            ,padding: '5 0 0 5'
                            ,name: 'evn'
                            ,fieldLabel: '环境'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('evnVO')?record.get('evnVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'machineField'
                            ,padding: '5 0 0 5'
                            ,name: 'machine'
                            ,fieldLabel: '机器'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('machineVO')?record.get('machineVO').name:'';
                            }
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