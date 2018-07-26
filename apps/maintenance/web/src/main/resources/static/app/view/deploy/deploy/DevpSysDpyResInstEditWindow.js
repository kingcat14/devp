Ext.define('AM.view.deploy.deploy.DevpSysDpyResInstEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.deploy.DevpSysDpyResInstEditWindow'
    ,requires:[
    ],
    autoScroll: true,
    height: 350,
    width: 600,
    layout: {
        type: 'fit'
    },
    title: '修改部署关联资源实例定义信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10,
                    fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        ,{
                            xtype: 'textfield',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'etypeField',
                            name: 'etype',
                            fieldLabel: '元素类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '系统元素名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'codeField',
                            name: 'code',
                            fieldLabel: '系统元素代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'aliasField',
                            name: 'alias',
                            fieldLabel: '系统元素别名'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'descriptionField',
                            name: 'description',
                            fieldLabel: '系统元素描述'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:true,
                            itemId: 'recordStateField',
                            name: 'recordState',
                            fieldLabel: '记录状态'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'typeField',
                            name: 'type',
                            fieldLabel: '类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'subTypeField',
                            name: 'subType',
                            fieldLabel: '子类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'stereotypeField',
                            name: 'stereotype',
                            fieldLabel: '构造型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'scopeField',
                            name: 'scope',
                            fieldLabel: '范围'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'versionField',
                            name: 'version',
                            fieldLabel: '版本'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'phaseField',
                            name: 'phase',
                            fieldLabel: '阶段'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'statusField',
                            name: 'status',
                            fieldLabel: '状态'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'notesField',
                            name: 'notes',
                            fieldLabel: '备注'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'prdRidField',
                            name: 'prdRid',
                            fieldLabel: '产品编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'resRidField',
                            name: 'resRid',
                            fieldLabel: '关联资源编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:true,
                            itemId: 'parentRidField',
                            name: 'parentRid',
                            fieldLabel: '父包编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:true,
                            itemId: 'seqField',
                            name: 'seq',
                            fieldLabel: '顺序号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:true,
                            itemId: 'assetRidField',
                            name: 'assetRid',
                            fieldLabel: '关联IT资产编号'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetEtypeField',
                            name: 'assetEtype',
                            fieldLabel: '关联IT资产元素类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetTypeCodeField',
                            name: 'assetTypeCode',
                            fieldLabel: '关联IT资产类型代码'

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
                Ext.MsgUtil.show('操作成功', '保存部署关联资源实例定义成功!');
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

        this.setTitle("修改部署关联资源实例定义信息");

        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});