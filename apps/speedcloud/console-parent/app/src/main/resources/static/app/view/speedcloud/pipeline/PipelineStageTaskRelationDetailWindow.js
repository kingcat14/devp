Ext.define('AM.view.speedcloud.pipeline.PipelineStageTaskRelationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineStageTaskRelationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '阶段任务关联详细信息'
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
                            itemId: 'stageField'
                            ,padding: '5 0 0 5'
                            ,name: 'stage'
                            ,fieldLabel: '阶段'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('stageVO').name;
                            }
                        }
                        ,{
                            itemId: 'taskField'
                            ,padding: '5 0 0 5'
                            ,name: 'task'
                            ,fieldLabel: '任务'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('taskVO').name;
                            }
                        }
                        ,{
                            itemId: 'execOrderField'
                            ,padding: '5 0 0 5'
                            ,name: 'execOrder'
                            ,fieldLabel: '执行排序'
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