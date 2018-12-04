Ext.define('AM.view.monitor.supporter.SupporterAppRelationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.supporter.SupporterAppRelationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '支持应用详细信息'
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
                            itemId: 'supporterField'
                            ,padding: '5 0 0 5'
                            ,name: 'supporter'
                            ,fieldLabel: '运维人员'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('supporterVO')?record.get('supporterVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'applicationField'
                            ,padding: '5 0 0 5'
                            ,name: 'application'
                            ,fieldLabel: '支持程序'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('applicationVO')?record.get('applicationVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'notificationTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'notificationType'
                            ,fieldLabel: '接收通知方式'
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