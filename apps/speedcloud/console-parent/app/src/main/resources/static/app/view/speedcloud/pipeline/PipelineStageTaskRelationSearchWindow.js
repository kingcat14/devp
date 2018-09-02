Ext.define('AM.view.speedcloud.pipeline.PipelineStageTaskRelationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineStageTaskRelationSearchWindow'
    ,alias: 'widget.speedcloudpipelinePipelineStageTaskRelationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '阶段任务关联高级查询'
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
                            ,store: Ext.create("AM.store.speedcloud.pipeline.PipelineStageStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'stageField'
                            ,fieldLabel: '阶段'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.task.PipelineTaskStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'taskField'
                            ,fieldLabel: '任务'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execOrderField'
                            ,fieldLabel: '执行排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execOrderMaxField'
                            ,fieldLabel: '执行排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'execOrderMinField'
                            ,fieldLabel: '执行排序'
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
        var stageField = me.down("#stageField");
        var taskField = me.down("#taskField");
        var execOrderField = me.down("#execOrderField");
        var execOrderMaxField = me.down("#execOrderMaxField");
        var execOrderMinField = me.down("#execOrderMinField");

        var condition = {
            stage:Ext.isEmpty(stageField.getValue())?null:stageField.getValue()
            ,task:Ext.isEmpty(taskField.getValue())?null:taskField.getValue()
            ,execOrder:Ext.isNumber(execOrderField.getValue())?execOrderField.getValue():null
            ,execOrderMax:Ext.isNumber(execOrderMaxField.getValue())?execOrderMaxField.getValue():null
            ,execOrderMin:Ext.isNumber(execOrderMinField.getValue())?execOrderMinField.getValue():null
        };

        return condition;
    }

});