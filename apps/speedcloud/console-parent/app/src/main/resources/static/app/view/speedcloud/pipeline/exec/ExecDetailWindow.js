Ext.define('AM.view.speedcloud.pipeline.exec.ExecDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.ExecDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '运行实例详细信息'
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
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '编号'
                        }
                        ,{
                            itemId: 'runnerIdField'
                            ,padding: '5 0 0 5'
                            ,name: 'runnerId'
                            ,fieldLabel: '运行主体'
                        }
                        ,{
                            itemId: 'runnerTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'runnerType'
                            ,fieldLabel: '运行类型'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '运行状态'
                        }
                        ,{
                            itemId: 'resultField'
                            ,padding: '5 0 0 5'
                            ,name: 'result'
                            ,fieldLabel: '运行结果'
                        }
                        ,{
                            itemId: 'startTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'startTime'
                            ,fieldLabel: '开始时间'
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