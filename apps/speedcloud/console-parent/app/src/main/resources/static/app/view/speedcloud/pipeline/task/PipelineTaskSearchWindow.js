Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskSearchWindow'
    ,alias: 'widget.speedcloudpipeline.taskPipelineTaskSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '任务高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var pipelineTaskTaskTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTaskTaskTypeStore.proxy.isSynchronous = true;
        pipelineTaskTaskTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETASK-TASKTYPE'}};
        pipelineTaskTaskTypeStore.load();

        var pipelineTaskExecTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTaskExecTypeStore.proxy.isSynchronous = true;
        pipelineTaskExecTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETASK-EXECTYPE'}};
        pipelineTaskExecTypeStore.load();

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
                            ,store: pipelineTaskTaskTypeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'taskTypeField'
                            ,fieldLabel: '任务类型'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: pipelineTaskExecTypeStore
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

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'projectField'
                            ,fieldLabel: '所属产品'
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
        var nameField = me.down("#nameField");
        var taskTypeField = me.down("#taskTypeField");
        var execTypeField = me.down("#execTypeField");
        var taskStartTimeField = me.down("#taskStartTimeField");
        var taskDayOfWeeksField = me.down("#taskDayOfWeeksField");
        var descriptionField = me.down("#descriptionField");
        var projectField = me.down("#projectField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,taskType:Ext.isEmpty(taskTypeField.getValue())?null:taskTypeField.getValue()
            ,execType:Ext.isEmpty(execTypeField.getValue())?null:execTypeField.getValue()
            ,taskStartTime:Ext.isEmpty(taskStartTimeField.getValue())?null:taskStartTimeField.getValue()
            ,taskDayOfWeeks:Ext.isEmpty(taskDayOfWeeksField.getValue())?null:taskDayOfWeeksField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,project:Ext.isEmpty(projectField.getValue())?null:projectField.getValue()
        };

        return condition;
    }

});