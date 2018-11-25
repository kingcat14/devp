Ext.define('AM.view.icode.domainboard.DomainBoardEntityActionPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode.domainboard.DomainBoardEntityActionPanel'
    ,requires:[
        'AM.view.icode.domainboard.EntityActionParameterPropertyPanel'
        ,'AM.store.common.SimpleConfigStore'

    ]
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '领域对象服务'
    ,referenceHolder:true
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,flex:1
                    ,layout: {
                        type: 'vbox'
                        ,pack: 'start'
                        ,align: 'stretch'
                    }
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,anchor: '96%'
                    }
                    ,items: [
                        {
                            xtype:'container'
                            ,anchor: '96% 70%'
                            ,layout: {
                                type: 'table',
                                columns: 2,
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
                                    ,allowBlank:false
                                    ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '代码'
                                    ,emptyText:'该字段为必填项'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:false
                                    ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '名称'
                                    ,emptyText:'该字段为必填项'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'memoField'
                                    ,name: 'memo'
                                    ,fieldLabel: '备注'
                                }



                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '行为类型'
                                    
                                }

                            ]
                        }
                        ,{

                            xtype: 'textarea'
                            ,anchor: '96% 70%'
                            ,flex:1
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '行为描述'
                            ,labelAlign: 'top'
                            
                        }
                    ]

                }
            ]
            ,dockedItems: [
                {
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,layout:'center'
                    ,items: [
                        {
                            xtype: 'button'
                            ,iconCls: 'fas fa-save'
                            ,scala:''
                            ,text: '保存'
                            ,listeners: {
                                click: {
                                    fn: me.onSaveButtonClick
                                    ,scope: me
                                }
                            }
                        }
                    ]
                }
            ]


        });

        me.callParent(arguments);
    }

    ,onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        //var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.notification('操作成功', '保存领域对象行为成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.lookupReference('inParameterPropertyPanel').setModel(newRecord)
                me.fireEvent('saved', {record:newRecord});

            }
        });
    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.down('form').getForm().loadRecord(model);

    }

});