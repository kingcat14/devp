Ext.define('AM.view.speedcloud.pipeline.jenkins.JobMappingDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JobMappingDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '任务映射详细信息'
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
                            itemId: 'projectField'
                            ,padding: '5 0 0 5'
                            ,name: 'project'
                            ,fieldLabel: '所属产品'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('projectVO')?record.get('projectVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'taskTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskType'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'jobInPlatformField'
                            ,padding: '5 0 0 5'
                            ,name: 'jobInPlatform'
                            ,fieldLabel: '任务或流水线'
                        }
                        ,{
                            itemId: 'jobInPlatformNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'jobInPlatformName'
                            ,fieldLabel: '任务或流水线名称'
                        }
                        ,{
                            itemId: 'jobInJenkinsNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'jobInJenkinsName'
                            ,fieldLabel: 'Jenkins中任务名称'
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