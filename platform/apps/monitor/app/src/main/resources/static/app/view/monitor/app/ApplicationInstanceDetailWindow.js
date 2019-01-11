Ext.define('AM.view.monitor.app.ApplicationInstanceDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.app.ApplicationInstanceDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '程序实例详细信息'
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
                            itemId: 'hostField'
                            ,padding: '5 0 0 5'
                            ,name: 'host'
                            ,fieldLabel: '主机'
                        }
                        ,{
                            itemId: 'portField'
                            ,padding: '5 0 0 5'
                            ,name: 'port'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            itemId: 'aliveField'
                            ,padding: '5 0 0 5'
                            ,name: 'alive'
                            ,fieldLabel: '运行中'
                        }
                        ,{
                            itemId: 'alarmField'
                            ,padding: '5 0 0 5'
                            ,name: 'alarm'
                            ,fieldLabel: '停运告警'
                        }
                        ,{
                            itemId: 'aliveTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'aliveTime'
                            ,fieldLabel: '最近活跃时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d H:i:s')
                            }
                        }
                        ,{
                            itemId: 'detectionTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'detectionTime'
                            ,fieldLabel: '最近检测时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d H:i:s')
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