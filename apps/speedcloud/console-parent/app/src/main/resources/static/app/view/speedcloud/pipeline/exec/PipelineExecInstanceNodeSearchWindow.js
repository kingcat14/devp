Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeSearchWindow'
    ,alias: 'widget.speedcloudpipeline.execPipelineExecInstanceNodeSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '运行实例节点高级查询'
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
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '编号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '节点名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nodeTypeField'
                            ,fieldLabel: '节点类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'execModeField'
                            ,fieldLabel: '执行方式'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'statusField'
                            ,fieldLabel: '运行状态'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'resultField'
                            ,fieldLabel: '运行结果'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.exec.PipelineExecInstanceStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'code'
                            ,valueField:'id'
                            ,itemId: 'execField'
                            ,fieldLabel: '所属实例'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'resultMessageField'
                            ,fieldLabel: '结果消息'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'startTimeField'
                            ,fieldLabel: '开始时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'startTimeStartField'
                            ,fieldLabel: '起始开始时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'startTimeEndField'
                            ,fieldLabel: '结束开始时间'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'parentIdField'
                            ,fieldLabel: '上级节点'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'taskField'
                            ,fieldLabel: '关联任务'
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
                            ,itemId: 'autoStartField'
                            ,fieldLabel: '自动运行'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexField'
                            ,fieldLabel: '节点排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexMaxField'
                            ,fieldLabel: '节点排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execIndexMinField'
                            ,fieldLabel: '节点排序'
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
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var nodeTypeField = me.down("#nodeTypeField");
        var execModeField = me.down("#execModeField");
        var statusField = me.down("#statusField");
        var resultField = me.down("#resultField");
        var execField = me.down("#execField");
        var resultMessageField = me.down("#resultMessageField");
        var startTimeStartField = me.down("#startTimeStartField");
        var startTimeEndField = me.down("#startTimeEndField");
        var startTimeField = me.down("#startTimeField");
        var parentIdField = me.down("#parentIdField");
        var taskField = me.down("#taskField");
        var autoStartField = me.down("#autoStartField");
        var execIndexField = me.down("#execIndexField");
        var execIndexMaxField = me.down("#execIndexMaxField");
        var execIndexMinField = me.down("#execIndexMinField");

        var condition = {
            code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,nodeType:Ext.isEmpty(nodeTypeField.getValue())?null:nodeTypeField.getValue()
            ,execMode:Ext.isEmpty(execModeField.getValue())?null:execModeField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,result:Ext.isEmpty(resultField.getValue())?null:resultField.getValue()
            ,exec:Ext.isEmpty(execField.getValue())?null:execField.getValue()
            ,resultMessage:Ext.isEmpty(resultMessageField.getValue())?null:resultMessageField.getValue()
            ,startTime:Ext.isEmpty(startTimeField.getValue())?null:Ext.Date.format(startTimeField.getValue(),'Y-m-d H:i:s')
            ,startTimeStart:Ext.isEmpty(startTimeStartField.getValue())?null:Ext.Date.format(startTimeStartField.getValue(),'Y-m-d H:i:s')
            ,startTimeEnd:Ext.isEmpty(startTimeEndField.getValue())?null:Ext.Date.format(startTimeEndField.getValue(),'Y-m-d H:i:s')
            ,parentId:Ext.isEmpty(parentIdField.getValue())?null:parentIdField.getValue()
            ,task:Ext.isEmpty(taskField.getValue())?null:taskField.getValue()
            ,autoStart:Ext.isEmpty(autoStartField.getValue())?null:autoStartField.getValue()
            ,execIndex:Ext.isNumber(execIndexField.getValue())?execIndexField.getValue():null
            ,execIndexMax:Ext.isNumber(execIndexMaxField.getValue())?execIndexMaxField.getValue():null
            ,execIndexMin:Ext.isNumber(execIndexMinField.getValue())?execIndexMinField.getValue():null
        };

        return condition;
    }

});