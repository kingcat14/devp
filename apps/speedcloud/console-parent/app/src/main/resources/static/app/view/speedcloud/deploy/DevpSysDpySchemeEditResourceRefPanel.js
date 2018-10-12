Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditResourceRefPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.deploy.DevpSysDpySchemeEditResourceRefPanel'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.deploy.DevpSysDpyResourcesStore'
        ,'AM.store.speedcloud.deploy.DevpSysDpyResourceRelationTypeStore'
        ,'AM.store.speedcloud.deploy.DevpSysDpySchemeStore'
    ]
    ,autoScroll: true
    ,layout: {
        type: 'vbox'
    }
    ,title: '资源关系'
    ,maximizable: true
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
                    }
                    ,items: [
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
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpyResourcesStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'resourceField'
                                    ,name: 'resource'
                                    ,fieldLabel: '主资源'
                                    ,bind:{readOnly:'{record.resource}'}
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpyResourceRelationTypeStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '关系'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpyResourcesStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'refResourceField'
                                    ,name: 'refResource'
                                    ,fieldLabel: '关联资源'
                                    ,bind:{readOnly:'{record.refResource}'}
                                }
                                , {
                                    xtype: 'fieldset'
                                    , title: '其他信息'
                                    , collapsible: true
                                    , collapsed: true
                                    ,items:[
                                        ,{
                                            xtype: 'combobox'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,editable:false
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'directionField'
                                            ,name: 'direction'
                                            ,fieldLabel: '对应关系方向'
                                            ,value:'EMPTY'
                                            ,store: [
                                                ['EMPTY','无']
                                                ,['FORWARD','正向']
                                                ,['BACKWARD','反向']
                                                ,['BOTH','双向']
                                            ]
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'nameField'
                                            ,name: 'name'
                                            ,fieldLabel: '对应关系名称'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'codeField'
                                            ,name: 'code'
                                            ,fieldLabel: '对应关系代码'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'aliasField'
                                            ,name: 'alias'
                                            ,fieldLabel: '对应关系别名'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'descriptionField'
                                            ,name: 'description'
                                            ,fieldLabel: '对应关系描述'
                                        }
                                        ,{
                                            xtype: 'numberfield'
                                            ,allowDecimals:false
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'seqField'
                                            ,name: 'seq'
                                            ,fieldLabel: '顺序号'
                                        }


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
    },

    onSaveButtonClick: function (button, e, options) {
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
                Ext.MsgUtil.show('操作成功', '保存方案资源间关系成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');

            }
        });
    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.down('form').getForm().reset();
        this.getViewModel().set('phantom', model.phantom);
        this.getViewModel().set('record', model);

        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        this.down('#resourceField').getStore().reload();
        this.down('#refResourceField').getStore().reload();
        this.down('#typeField').getStore().reload();
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});