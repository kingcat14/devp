Ext.define('AM.view.speedcloud.pipeline.PipelineStageDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineStageDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '阶段详细信息'
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
                            itemId: 'pipelineField'
                            ,padding: '5 0 0 5'
                            ,name: 'pipeline'
                            ,fieldLabel: '所属流水线'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('pipelineVO').name;
                            }
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '阶段名称'
                        }
                        ,{
                            itemId: 'flowTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'flowType'
                            ,fieldLabel: '流转方式'
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