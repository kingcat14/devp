Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskParamDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskParamDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '任务参数详细信息'
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
                            itemId: 'taskField'
                            ,padding: '5 0 0 5'
                            ,name: 'task'
                            ,fieldLabel: '所属任务'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('taskVO').name;
                            }
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '参数名称'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '参数类型'
                        }
                        ,{
                            itemId: 'defaultValueField'
                            ,padding: '5 0 0 5'
                            ,name: 'defaultValue'
                            ,fieldLabel: '默认值'
                        }
                        ,{
                            itemId: 'viewOrderField'
                            ,padding: '5 0 0 5'
                            ,name: 'viewOrder'
                            ,fieldLabel: '展现顺序'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '参数描述'
                        }
                        ,{
                            itemId: 'deletableField'
                            ,padding: '5 0 0 5'
                            ,name: 'deletable'
                            ,fieldLabel: '可删除'
                        }
                        ,{
                            itemId: 'enumValueField'
                            ,padding: '5 0 0 5'
                            ,name: 'enumValue'
                            ,fieldLabel: '可选值'
                        }
                        ,{
                            itemId: 'valueField'
                            ,padding: '5 0 0 5'
                            ,name: 'value'
                            ,fieldLabel: '参数值'
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