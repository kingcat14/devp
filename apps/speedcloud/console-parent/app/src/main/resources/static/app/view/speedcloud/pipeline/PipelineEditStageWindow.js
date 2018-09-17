Ext.define('AM.view.speedcloud.pipeline.PipelineEditStageWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineEditStageWindow'

    ,autoScroll: true
    ,height: 300
    ,width: 400
    ,layout: {
        type: 'vbox'
    }
    ,title: '修改阶段信息'
    ,bind:{title:'{stage.name}'}
    ,maximizable: true
    ,closeAction:'hide'
    ,viewModel:true
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
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        {
                            xtype:'container'
                            ,anchor: '96% 70%'
                            ,layout: {
                                type: 'table',
                                columns: 1,
                                tableAttrs: {
                                    style: {
                                        width: '100%'
                                    }
                                }
                            }
                            ,defaults:{width:'100%'}
                            ,items:[
                                {
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '阶段名称'
                                    ,bind:'{stage.name}'
                                }
                                ,{
                                    xtype: 'radiogroup'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'flowTypeField'
                                    ,name: 'flowType'
                                    ,simpleValue: true
                                    ,fieldLabel: '流转方式'
                                    ,bind:'{stage.flowType}'
                                    ,items:[
                                        {boxLabel: '自动执行', inputValue: 'AUTO', checked: true}
                                        ,{boxLabel: '手工执行', inputValue: 'MANUAL'}

                                    ]
                                }
                                ,{
                                    xtype: 'radiogroup'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,simpleValue: true
                                    ,itemId: 'execModeField'
                                    ,name: 'execMode'
                                    ,fieldLabel: '执行方式'
                                    ,bind:'{stage.execMode}'
                                    ,items:[
                                        {boxLabel: '串行执行', inputValue: 'SYNC', checked: true,}
                                        ,{boxLabel: '并行执行', inputValue: 'ASYNC'}
                                    ]
                                }
                            ]

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
                        },
                        {
                            xtype: 'button',
                            iconCls: 'accept',
                            text: '确定',
                            listeners: {
                                click: {
                                    fn: me.onSaveButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
            ,listeners: {
                beforeshow: me.onBeforeShow
            }
        });

        me.callParent(arguments);
    }
    ,onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        me.hide();

    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改阶段信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
       
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});