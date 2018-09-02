Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '任务详细信息'
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
                            ,fieldLabel: '任务名称'
                        }
                        ,{
                            itemId: 'taskTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskType'
                            ,fieldLabel: '任务类型'
                        }
                        ,{
                            itemId: 'execTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'execType'
                            ,fieldLabel: '执行计划'
                        }
                        ,{
                            itemId: 'taskStartTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskStartTime'
                            ,fieldLabel: '执行开始时间'
                        }
                        ,{
                            itemId: 'taskDayOfWeeksField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskDayOfWeeks'
                            ,fieldLabel: '执行日'
                        }
                        ,{
                            itemId: 'projectField'
                            ,padding: '5 0 0 5'
                            ,name: 'project'
                            ,fieldLabel: '所属产品'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('projectVO').name;
                            }
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '任务描述'
                            ,labelAlign: 'top'
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