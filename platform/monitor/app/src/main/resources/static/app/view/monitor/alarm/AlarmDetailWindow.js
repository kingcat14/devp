Ext.define('AM.view.monitor.alarm.AlarmDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.alarm.AlarmDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '告警详细信息'
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
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('typeVO')?record.get('typeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'appField'
                            ,padding: '5 0 0 5'
                            ,name: 'app'
                            ,fieldLabel: '程序'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('appVO')?record.get('appVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'counterField'
                            ,padding: '5 0 0 5'
                            ,name: 'counter'
                            ,fieldLabel: '指标'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('counterVO')?record.get('counterVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'valueField'
                            ,padding: '5 0 0 5'
                            ,name: 'value'
                            ,fieldLabel: '指标值'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '状态'
                        }
                        ,{
                            itemId: 'occurTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'occurTime'
                            ,fieldLabel: '发生时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d H:i:s')
                            }
                        }
                        ,{
                            itemId: 'lastOccurTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'lastOccurTime'
                            ,fieldLabel: '最后发生时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d H:i:s')
                            }
                        }
                        ,{
                            itemId: 'disappearTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'disappearTime'
                            ,fieldLabel: '消失时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d H:i:s')
                            }
                        }
                        ,{
                            itemId: 'occurCountField'
                            ,padding: '5 0 0 5'
                            ,name: 'occurCount'
                            ,fieldLabel: '发生次数'
                        }
                        ,{
                            itemId: 'contentField'
                            ,padding: '5 0 0 5'
                            ,name: 'content'
                            ,fieldLabel: '内容'
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