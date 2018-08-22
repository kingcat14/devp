Ext.define('AM.view.maintenance.software.SoftwareLicenseAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'maintenance.software.SoftwareLicenseAddWindow'
    ,requires:[
        'AM.store.application.common.SimpleConfigStore'

    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
    }
    ,title: '添加新服务许可'
    ,maximizable: true
    ,closeAction: 'hide'
    ,initComponent: function () {
        var me = this;

        var softwareLicenseStatusStore = Ext.create("AM.store.application.common.SimpleConfigStore")
        softwareLicenseStatusStore.proxy.isSynchronous = true;
        softwareLicenseStatusStore.proxy.extraParams={searchCondition:{configType:'OPS_ASSET_STATUS'}};
        softwareLicenseStatusStore.load();
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
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '代码'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'aliasField'
                                    ,name: 'alias'
                                    ,fieldLabel: '别名'
                                }




                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.maintenance.asset.info.AssetTypeStore").applyCondition({})
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeCodeField'
                                    ,name: 'typeCode'
                                    ,fieldLabel: '类型代码'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'hardwareModelField'
                                    ,name: 'hardwareModel'
                                    ,fieldLabel: '硬件型号'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'softwareModelField'
                                    ,name: 'softwareModel'
                                    ,fieldLabel: '软件型号'
                                }


                                ,{
                                    xtype: 'combobox'
                                    ,store: softwareLicenseStatusStore
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'displayName'
                                    ,valueField:'value'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'statusField'
                                    ,name: 'status'
                                    ,fieldLabel: '状态'
                                }


                                ,{
                                    xtype: 'datefield'
                                    ,format: 'Y-m-d'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'createDateField'
                                    ,name: 'createDate'
                                    ,fieldLabel: '创建时间'
                                }


                                ,{
                                    xtype: 'datefield'
                                    ,format: 'Y-m-d'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'expireDateField'
                                    ,name: 'expireDate'
                                    ,fieldLabel: '到期时间'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'assetProjectField'
                                    ,name: 'assetProject'
                                    ,fieldLabel: '所属项目'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'assetAreaField'
                                    ,name: 'assetArea'
                                    ,fieldLabel: '所属区域'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'assetLocationField'
                                    ,name: 'assetLocation'
                                    ,fieldLabel: '资产位置'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'acquisitionModeField'
                                    ,name: 'acquisitionMode'
                                    ,fieldLabel: '获取方式'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'acquisitionDescField'
                                    ,name: 'acquisitionDesc'
                                    ,fieldLabel: '获取方式说明'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'assetDeptField'
                                    ,name: 'assetDept'
                                    ,fieldLabel: '归属部门'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'assetManagerField'
                                    ,name: 'assetManager'
                                    ,fieldLabel: '资产负责人'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'useDeptField'
                                    ,name: 'useDept'
                                    ,fieldLabel: '使用部门'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'useManagerField'
                                    ,name: 'useManager'
                                    ,fieldLabel: '使用负责人'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'opsDeptField'
                                    ,name: 'opsDept'
                                    ,fieldLabel: '维护部门'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'opsManagerField'
                                    ,name: 'opsManager'
                                    ,fieldLabel: '维护负责人'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'bizLineField'
                                    ,name: 'bizLine'
                                    ,fieldLabel: '业务线'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'bizManagerField'
                                    ,name: 'bizManager'
                                    ,fieldLabel: '业务代表'
                                }


                                ,{
                                    xtype: 'datefield'
                                    ,format: 'Y-m-d'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'goliveDateField'
                                    ,name: 'goliveDate'
                                    ,fieldLabel: '启用时间'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'majorCustField'
                                    ,name: 'majorCust'
                                    ,fieldLabel: '主要客户'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'custManagerField'
                                    ,name: 'custManager'
                                    ,fieldLabel: '客户代表'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'custUsageField'
                                    ,name: 'custUsage'
                                    ,fieldLabel: '使用情况'
                                }




                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'prdRidField'
                                    ,name: 'prdRid'
                                    ,fieldLabel: '关联产品记录编号'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'attachmentField'
                                    ,name: 'attachment'
                                    ,fieldLabel: '附件'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'acquisitionProviderField'
                                    ,name: 'acquisitionProvider'
                                    ,fieldLabel: '供应商'
                                }

                            ]
                        }
                        ,{

                            xtype: 'textarea',
                            anchor: '96% 70%',
                            itemId: 'descriptionField',
                            padding: '5 0 0 5',
                            name: 'description',
                            fieldLabel: '描述',
                            labelAlign: 'top'
                        }
                        ,{

                            xtype: 'textarea',
                            anchor: '96% 70%',
                            itemId: 'notesField',
                            padding: '5 0 0 5',
                            name: 'notes',
                            fieldLabel: '备注',
                            labelAlign: 'top'
                        }
                    ]
                }
                ,{xtype:'fileuploadpanel', itemId:'fileuploadpanel-attachment'}
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


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存服务许可成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);

                me.down('#fileuploadpanel-attachment').save(newRecord, 'SoftwareLicense-attachment')
            }
        });
    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        if(!Ext.isEmpty(this.down('#typeCodeField').getValue())){
            model.set('typeCode',this.down('#typeCodeField').getValue());
        }

        this.down('form').getForm().loadRecord(model);

        this.down('#fileuploadpanel-attachment').reset(model);
    }

});