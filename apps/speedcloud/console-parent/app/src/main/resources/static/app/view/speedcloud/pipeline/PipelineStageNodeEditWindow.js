Ext.define('AM.view.speedcloud.pipeline.PipelineStageNodeEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineStageNodeEditWindow'
    ,requires:[
        'AM.store.speedcloud.pipeline.PipelineStageStore'
    ],
    autoScroll: true,
    height: '60%',
    width: '60%',
    layout: {
        type: 'vbox'
    },
    title: '修改阶段执行节点信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        var pipelineStageNodeNodeTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineStageNodeNodeTypeStore.proxy.isSynchronous = true;
        pipelineStageNodeNodeTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINESTAGENODE-NODETYPE'}};
        pipelineStageNodeNodeTypeStore.load();

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10

                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
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
                                columns: 3,
                                tableAttrs: {
                                    style: {
                                        width: '100%'
                                    }
                                }
                            }
                            ,defaults:{width:'100%'}
                            ,items:[
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '名称'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.pipeline.PipelineStageStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'stageField'
                                    ,name: 'stage'
                                    ,fieldLabel: '阶段'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: pipelineStageNodeNodeTypeStore
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nodeTypeField'
                                    ,name: 'nodeType'
                                    ,fieldLabel: '节点类型'
                                }
                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nodeIdField'
                                    ,name: 'nodeId'
                                    ,fieldLabel: '节点节点ID'
                                }
                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'execOrderField'
                                    ,name: 'execOrder'
                                    ,fieldLabel: '执行排序'
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
    },

    onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        //var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中的数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存阶段执行节点成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);
            }
        });



    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改阶段执行节点信息");

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
       
        this.down('#stageField').getStore().reload();
       
        this.down('#nodeTypeField').getStore().reload();
       
       
       
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});