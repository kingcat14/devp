Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceSearchWindow'
    ,alias: 'widget.speedcloudpipeline.execPipelineExecInstanceSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '运行计划高级查询'
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
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'executeTargetIdField'
                            ,fieldLabel: '运行主体'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'executeTargetIdMaxField'
                            ,fieldLabel: '运行主体'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'executeTargetIdMinField'
                            ,fieldLabel: '运行主体'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'executeTargetTypeField'
                            ,fieldLabel: '运行类型'
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
        var codeField = me.down("#codeField");
        var executeTargetIdField = me.down("#executeTargetIdField");
        var executeTargetIdMaxField = me.down("#executeTargetIdMaxField");
        var executeTargetIdMinField = me.down("#executeTargetIdMinField");
        var executeTargetTypeField = me.down("#executeTargetTypeField");
        var statusField = me.down("#statusField");
        var resultField = me.down("#resultField");
        var startTimeStartField = me.down("#startTimeStartField");
        var startTimeEndField = me.down("#startTimeEndField");
        var startTimeField = me.down("#startTimeField");

        var condition = {
            code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,executeTargetId:Ext.isNumber(executeTargetIdField.getValue())?executeTargetIdField.getValue():null
            ,executeTargetIdMax:Ext.isNumber(executeTargetIdMaxField.getValue())?executeTargetIdMaxField.getValue():null
            ,executeTargetIdMin:Ext.isNumber(executeTargetIdMinField.getValue())?executeTargetIdMinField.getValue():null
            ,executeTargetType:Ext.isEmpty(executeTargetTypeField.getValue())?null:executeTargetTypeField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,result:Ext.isEmpty(resultField.getValue())?null:resultField.getValue()
            ,startTime:Ext.isEmpty(startTimeField.getValue())?null:Ext.Date.format(startTimeField.getValue(),'Y-m-d H:i:s')
            ,startTimeStart:Ext.isEmpty(startTimeStartField.getValue())?null:Ext.Date.format(startTimeStartField.getValue(),'Y-m-d H:i:s')
            ,startTimeEnd:Ext.isEmpty(startTimeEndField.getValue())?null:Ext.Date.format(startTimeEndField.getValue(),'Y-m-d H:i:s')
        };

        return condition;
    }

});