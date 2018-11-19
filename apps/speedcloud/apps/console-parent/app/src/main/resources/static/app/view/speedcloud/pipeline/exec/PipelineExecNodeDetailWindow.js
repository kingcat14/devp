Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecNodeDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecNodeDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '运行实例节点详细信息'
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
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '节点名称'
                        }
                        ,{
                            itemId: 'nodeTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'nodeType'
                            ,fieldLabel: '节点类型'
                        }
                        ,{
                            itemId: 'execModeField'
                            ,padding: '5 0 0 5'
                            ,name: 'execMode'
                            ,fieldLabel: '执行方式'
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
                            itemId: 'execField'
                            ,padding: '5 0 0 5'
                            ,name: 'exec'
                            ,fieldLabel: '所属实例'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('execVO').code;
                            }
                        }
                        ,{
                            itemId: 'resultMessageField'
                            ,padding: '5 0 0 5'
                            ,name: 'resultMessage'
                            ,fieldLabel: '结果消息'
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
                        ,{
                            itemId: 'parentIdField'
                            ,padding: '5 0 0 5'
                            ,name: 'parentId'
                            ,fieldLabel: '上级节点'
                        }
                        ,{
                            itemId: 'taskField'
                            ,padding: '5 0 0 5'
                            ,name: 'task'
                            ,fieldLabel: '关联任务'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('taskVO').name;
                            }
                        }
                        ,{
                            itemId: 'autoStartField'
                            ,padding: '5 0 0 5'
                            ,name: 'autoStart'
                            ,fieldLabel: '自动运行'
                        }
                        ,{
                            itemId: 'execIndexField'
                            ,padding: '5 0 0 5'
                            ,name: 'execIndex'
                            ,fieldLabel: '节点排序'
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