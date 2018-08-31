Ext.define('AM.view.speedcloud.pipeline.PipelineTaskDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineTaskDetailWindow'
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
                            itemId: 'stageField'
                            ,padding: '5 0 0 5'
                            ,name: 'stage'
                            ,fieldLabel: '所属阶段'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('stageVO').name;
                            }
                        }
                        ,{
                            itemId: 'execOrderField'
                            ,padding: '5 0 0 5'
                            ,name: 'execOrder'
                            ,fieldLabel: '执行排序'
                        }
                        ,{
                            itemId: 'taskTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskType'
                            ,fieldLabel: '任务类型'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('taskTypeVO').name;
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