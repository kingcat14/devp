Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '运行实例节点参数详细信息'
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
                            itemId: 'nodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'node'
                            ,fieldLabel: '所属节点'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('nodeVO').name;
                            }
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '属性名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '属性代码'
                        }
                        ,{
                            itemId: 'viewOrderField'
                            ,padding: '5 0 0 5'
                            ,name: 'viewOrder'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'valueField'
                            ,padding: '5 0 0 5'
                            ,name: 'value'
                            ,fieldLabel: '属性值'
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