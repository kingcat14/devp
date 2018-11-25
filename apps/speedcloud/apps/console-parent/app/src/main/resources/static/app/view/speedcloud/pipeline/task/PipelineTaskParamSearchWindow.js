Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskParamSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskParamSearchWindow'
    ,alias: 'widget.speedcloudpipeline.taskPipelineTaskParamSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '任务参数高级查询'
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
                            ,fieldLabel: '参数名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeField'
                            ,fieldLabel: '参数类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'defaultValueField'
                            ,fieldLabel: '默认值'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderField'
                            ,fieldLabel: '展现顺序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderMaxField'
                            ,fieldLabel: '展现顺序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewOrderMinField'
                            ,fieldLabel: '展现顺序'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '参数描述'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: [
                                [true,'是']
                                ,[false,'否']
                            ]
                            ,value:true
                            ,typeAhead:false
                            ,editable:false
                            ,itemId: 'deletableField'
                            ,fieldLabel: '可删除'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'enumValueField'
                            ,fieldLabel: '可选值'
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
        var taskField = me.down("#taskField");
        var nameField = me.down("#nameField");
        var typeField = me.down("#typeField");
        var defaultValueField = me.down("#defaultValueField");
        var viewOrderField = me.down("#viewOrderField");
        var viewOrderMaxField = me.down("#viewOrderMaxField");
        var viewOrderMinField = me.down("#viewOrderMinField");
        var descriptionField = me.down("#descriptionField");
        var deletableField = me.down("#deletableField");
        var enumValueField = me.down("#enumValueField");

        var condition = {
            task:Ext.isEmpty(taskField.getValue())?null:taskField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,defaultValue:Ext.isEmpty(defaultValueField.getValue())?null:defaultValueField.getValue()
            ,viewOrder:Ext.isNumber(viewOrderField.getValue())?viewOrderField.getValue():null
            ,viewOrderMax:Ext.isNumber(viewOrderMaxField.getValue())?viewOrderMaxField.getValue():null
            ,viewOrderMin:Ext.isNumber(viewOrderMinField.getValue())?viewOrderMinField.getValue():null
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,deletable:Ext.isEmpty(deletableField.getValue())?null:deletableField.getValue()
            ,enumValue:Ext.isEmpty(enumValueField.getValue())?null:enumValueField.getValue()
        };

        return condition;
    }

});