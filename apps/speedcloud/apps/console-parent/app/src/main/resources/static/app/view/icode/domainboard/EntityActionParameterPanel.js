Ext.define('AM.view.icode.domainboard.EntityActionParameterPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode.domainboard.EntityActionParameterPanel'
    ,requires:[
        'AM.view.icode.domainboard.EntityActionParameterPropertyPanel'
        ,'AM.store.common.SimpleConfigStore'
        ,'AM.store.icode.domain.EntityActionStore'
    ]
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    // ,title: '设置领域服务参数'
    ,referenceHolder: true
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    type: 'IN'//当前设置的是输入还是输出参数
                    ,entityAction: null
                }
            }
        }, cfg)])
    }
    // ,bind: {title:'设置领域服务参数({type})'}
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            title:'领域服务参数'
            ,items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
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

                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '对象代码'
                                    
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '对象名称'
                                    
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
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.icode.domain.EntityActionStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'entityActionField'
                                    ,name: 'entityAction'
                                    ,fieldLabel: '领域对象行为'
                                                         
                                }

                            ]
                        }
                        ,{

                            xtype: 'textarea'
                            ,anchor: '96% 70%'
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
                            ,labelAlign: 'top'

                        }
                    ]
                }
                ,{
                    xtype: 'icode.domainboard.EntityActionParameterPropertyPanel'
                    ,reference: 'parameterPropertyPanel'
                    ,flex: 1
                    ,title: '输入对象'
                }
            ],
            dockedItems: [
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
                            ,iconCls: 'fas fa-save'
                            ,text: '保存'
                            ,listeners: {
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

        var record = this.down('form').getForm().getRecord();

        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.notification('操作成功', '保存领域对象行为参数成功!');
                me.down('form').getForm().loadRecord(newRecord);
                var entityAction = me.getViewModel().get('entityAction');

                var type = me.getViewModel().get('type');

                //如果是直接从树形菜单上点击的,则entityAction为空
                if(entityAction && type=='IN'){
                    entityAction.set('request', newRecord.getId())
                    entityAction.save();
                }
                if(entityAction && type=='OUT'){
                    entityAction.set('response', newRecord.getId())
                    entityAction.save();
                }

                var parameterPropertyPanel = me.lookupReference('parameterPropertyPanel');

                var propertyStore = parameterPropertyPanel.getViewModel().getStore('store')

                propertyStore.each(function(model){
                    model.set('actionParameter', newRecord.getId());
                });

                propertyStore.sync({
                    success:function () {

                        parameterPropertyPanel.setParameter(newRecord);

                        Ext.MsgUtil.show('操作成功', '保存行为参数成功!');
                    }
                    ,failure:function () {
                        Ext.MessageBox.show({title: '操作失败', msg: '同步行为参数失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    }
                });
                me.fireEvent('saved', {record:newRecord});

                me.fireEvent('saved');
            }
        });
    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.down('form').getForm().loadRecord(model);

        this.lookupReference('parameterPropertyPanel').setParameter(model);

    }
    ,onBeforeShow:function() {
        this.down('#entityActionField').getStore().reload();
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});