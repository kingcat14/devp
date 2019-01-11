Ext.define('AM.view.monitor.app.ApplicationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.app.ApplicationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '程序详细信息'
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
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '代码'
                        }
                        ,{
                            itemId: 'totalCountField'
                            ,padding: '5 0 0 5'
                            ,name: 'totalCount'
                            ,fieldLabel: '配置实例数量'
                        }
                        ,{
                            itemId: 'aliveCountField'
                            ,padding: '5 0 0 5'
                            ,name: 'aliveCount'
                            ,fieldLabel: '当前实例数量'
                        }
                        ,{
                            itemId: 'alarmField'
                            ,padding: '5 0 0 5'
                            ,name: 'alarm'
                            ,fieldLabel: '低实例告警'
                        }
                        ,{
                            itemId: 'enableField'
                            ,padding: '5 0 0 5'
                            ,name: 'enable'
                            ,fieldLabel: '启动监控'
                        }
                        ,{
                            itemId: 'thresholdValueField'
                            ,padding: '5 0 0 5'
                            ,name: 'thresholdValue'
                            ,fieldLabel: '告警数量'
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