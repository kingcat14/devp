Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionSearchWindow'
    ,alias: 'widget.speedcloudpipeline.taskPipelineTaskActionSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '操作高级查询'
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
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'taskField'
                            ,fieldLabel: '所属任务'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '操作名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'memoField'
                            ,fieldLabel: '操作说明'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexField'
                            ,fieldLabel: '执行顺序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexMaxField'
                            ,fieldLabel: '执行顺序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexMinField'
                            ,fieldLabel: '执行顺序'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskActionTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'typeField'
                            ,fieldLabel: '操作类型'
                        }

                            ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();

        me.fireEvent('saved');


    }
    ,getCondition: function(){

        var me = this;
        var taskField = me.down("#taskField");
        var nameField = me.down("#nameField");
        var memoField = me.down("#memoField");
        var execIndexField = me.down("#execIndexField");
        var execIndexMaxField = me.down("#execIndexMaxField");
        var execIndexMinField = me.down("#execIndexMinField");
        var typeField = me.down("#typeField");

        var condition = {
            task:Ext.isEmpty(taskField.getValue())?null:taskField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,memo:Ext.isEmpty(memoField.getValue())?null:memoField.getValue()
            ,execIndex:Ext.isNumber(execIndexField.getValue())?execIndexField.getValue():null
            ,execIndexMax:Ext.isNumber(execIndexMaxField.getValue())?execIndexMaxField.getValue():null
            ,execIndexMin:Ext.isNumber(execIndexMinField.getValue())?execIndexMinField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
        };

        return condition;
    }

});