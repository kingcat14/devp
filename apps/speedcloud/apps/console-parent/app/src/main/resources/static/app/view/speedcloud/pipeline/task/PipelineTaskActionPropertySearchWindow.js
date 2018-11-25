Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertySearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionPropertySearchWindow'
    ,alias: 'widget.speedcloudpipeline.taskPipelineTaskActionPropertySearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '操作属性高级查询'
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
                            ,store: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskActionStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'taskTypeField'
                            ,fieldLabel: '所属操作'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '属性名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '属性代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'valueField'
                            ,fieldLabel: '属性值'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderMaxField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderMinField'
                            ,fieldLabel: '排序'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
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
                            iconCls: 'fas fa-search',
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
        var taskTypeField = me.down("#taskTypeField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var valueField = me.down("#valueField");
        var viewOrderField = me.down("#viewOrderField");
        var viewOrderMaxField = me.down("#viewOrderMaxField");
        var viewOrderMinField = me.down("#viewOrderMinField");
        var typeField = me.down("#typeField");

        var condition = {
            taskType:Ext.isEmpty(taskTypeField.getValue())?null:taskTypeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,value:Ext.isEmpty(valueField.getValue())?null:valueField.getValue()
            ,viewOrder:Ext.isNumber(viewOrderField.getValue())?viewOrderField.getValue():null
            ,viewOrderMax:Ext.isNumber(viewOrderMaxField.getValue())?viewOrderMaxField.getValue():null
            ,viewOrderMin:Ext.isNumber(viewOrderMinField.getValue())?viewOrderMinField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
        };

        return condition;
    }

});