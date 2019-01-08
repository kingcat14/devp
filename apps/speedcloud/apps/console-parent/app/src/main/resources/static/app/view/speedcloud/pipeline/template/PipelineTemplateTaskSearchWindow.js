Ext.define('AM.view.speedcloud.pipeline.template.PipelineTemplateTaskSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.template.PipelineTemplateTaskSearchWindow'
    ,alias: 'widget.speedcloudpipeline.templatePipelineTemplateTaskSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '任务模板高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var pipelineTemplateTaskExecTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTemplateTaskExecTypeStore.proxy.isSynchronous = true;
        pipelineTemplateTaskExecTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETEMPLATETASK-EXECTYPE'}};
        pipelineTemplateTaskExecTypeStore.load();

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
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '任务名称'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.config.PipelineTaskTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'taskTypeField'
                            ,fieldLabel: '任务类型'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: pipelineTemplateTaskExecTypeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'execTypeField'
                            ,fieldLabel: '执行计划'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'taskStartTimeField'
                            ,fieldLabel: '执行开始时间'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'taskDayOfWeeksField'
                            ,fieldLabel: '执行日'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '任务描述'
                        }

                            ]
                }
            ]
            ,dockedItems: [
                {
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button'
                            ,iconCls: 'page_white'
                            ,text: '重置'
                            ,listeners: {
                                click: {fn: me.onRestButtonClick,scope: me}
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {fn: me.onSearchButtonClick,scope: me}
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
        var nameField = me.down("#nameField");
        var taskTypeField = me.down("#taskTypeField");
        var execTypeField = me.down("#execTypeField");
        var taskStartTimeField = me.down("#taskStartTimeField");
        var taskDayOfWeeksField = me.down("#taskDayOfWeeksField");
        var descriptionField = me.down("#descriptionField");

        var condition = {
            name:Ext.valueFrom(nameField.getValue(), null)
            ,taskType:Ext.valueFrom(taskTypeField.getValue(), null)
            ,execType:Ext.valueFrom(execTypeField.getValue(), null)
            ,taskStartTime:Ext.valueFrom(taskStartTimeField.getValue(), null)
            ,taskDayOfWeeks:Ext.valueFrom(taskDayOfWeeksField.getValue(), null)
            ,description:Ext.valueFrom(descriptionField.getValue(), null)
        };

        return condition;
    }

});