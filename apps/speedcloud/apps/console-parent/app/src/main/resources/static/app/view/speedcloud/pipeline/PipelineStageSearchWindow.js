Ext.define('AM.view.speedcloud.pipeline.PipelineStageSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineStageSearchWindow'
    ,alias: 'widget.speedcloudpipelinePipelineStageSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '阶段高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var pipelineStageFlowTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineStageFlowTypeStore.proxy.isSynchronous = true;
        pipelineStageFlowTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINESTAGE-FLOWTYPE'}};
        pipelineStageFlowTypeStore.load();

        var pipelineStageExecModeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineStageExecModeStore.proxy.isSynchronous = true;
        pipelineStageExecModeStore.proxy.extraParams={searchCondition:{configType:'PIPELINESTAGE-EXECMODE'}};
        pipelineStageExecModeStore.load();

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
                            ,store: Ext.create("AM.store.speedcloud.pipeline.PipelineStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'pipelineField'
                            ,fieldLabel: '所属流水线'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '阶段名称'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: pipelineStageFlowTypeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'flowTypeField'
                            ,fieldLabel: '流转方式'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: pipelineStageExecModeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'execModeField'
                            ,fieldLabel: '执行方式'
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
        var pipelineField = me.down("#pipelineField");
        var nameField = me.down("#nameField");
        var flowTypeField = me.down("#flowTypeField");
        var execModeField = me.down("#execModeField");

        var condition = {
            pipeline:Ext.isEmpty(pipelineField.getValue())?null:pipelineField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,flowType:Ext.isEmpty(flowTypeField.getValue())?null:flowTypeField.getValue()
            ,execMode:Ext.isEmpty(execModeField.getValue())?null:execModeField.getValue()
        };

        return condition;
    }

});