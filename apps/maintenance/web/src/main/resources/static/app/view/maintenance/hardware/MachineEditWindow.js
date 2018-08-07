Ext.define('AM.view.maintenance.hardware.MachineEditWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'maintenance.hardware.MachineEditWindow'
    ,requires:[
        'AM.view.ux.FileUploadPanel'
    ],
    autoScroll: true,
    height: 350,
    width: 600,
    layout: {
        type: 'vbox'
    },
    title: '修改服务器信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    region: 'center',
                    autoScroll: true,
                    bodyPadding: 10,
                    width:'100%',
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
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'codeField',
                            name: 'code',
                            fieldLabel: '代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'aliasField',
                            name: 'alias',
                            fieldLabel: '别名'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'descriptionField',
                            name: 'description',
                            fieldLabel: '描述'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'typeCodeField',
                            name: 'typeCode',
                            fieldLabel: '类型代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'typeNameField',
                            name: 'typeName',
                            fieldLabel: '类型名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'hardwareModelField',
                            name: 'hardwareModel',
                            fieldLabel: '硬件型号'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'softwareModelField',
                            name: 'softwareModel',
                            fieldLabel: '软件型号'

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
                            itemId: 'statusField',
                            name: 'status',
                            fieldLabel: '状态'

                        }
                        ,{
                            xtype: 'datefield',
                            format: 'Y-m-d',
                            allowBlank:true,
                            itemId: 'expireDateField',
                            name: 'expireDate',
                            fieldLabel: '到期时间'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetProjectField',
                            name: 'assetProject',
                            fieldLabel: '所属项目'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetAreaField',
                            name: 'assetArea',
                            fieldLabel: '所属区域'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetLocationField',
                            name: 'assetLocation',
                            fieldLabel: '资产位置'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'intAccessAddrField',
                            name: 'intAccessAddr',
                            fieldLabel: '内部访问地址'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'extAccessAddrField',
                            name: 'extAccessAddr',
                            fieldLabel: '外部访问地址'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'acquisitionModeField',
                            name: 'acquisitionMode',
                            fieldLabel: '获取方式'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'acquisitionDescField',
                            name: 'acquisitionDesc',
                            fieldLabel: '获取方式说明'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetDeptField',
                            name: 'assetDept',
                            fieldLabel: '归属部门'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'assetManagerField',
                            name: 'assetManager',
                            fieldLabel: '资产负责人'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'useDeptField',
                            name: 'useDept',
                            fieldLabel: '使用部门'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'useManagerField',
                            name: 'useManager',
                            fieldLabel: '使用负责人'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'opsDeptField',
                            name: 'opsDept',
                            fieldLabel: '维护部门'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'opsManagerField',
                            name: 'opsManager',
                            fieldLabel: '维护负责人'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'bizLineField',
                            name: 'bizLine',
                            fieldLabel: '业务线'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'bizManagerField',
                            name: 'bizManager',
                            fieldLabel: '业务代表'

                        }
                        ,{
                            xtype: 'datefield',
                            format: 'Y-m-d',
                            allowBlank:true,
                            itemId: 'goliveDateField',
                            name: 'goliveDate',
                            fieldLabel: '启用时间'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'majorCustField',
                            name: 'majorCust',
                            fieldLabel: '主要客户'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'custManagerField',
                            name: 'custManager',
                            fieldLabel: '客户代表'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'custUsageField',
                            name: 'custUsage',
                            fieldLabel: '使用情况'

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
                            allowBlank:true,
                            itemId: 'prdRidField',
                            name: 'prdRid',
                            fieldLabel: '关联产品记录编号'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'parasCodeField',
                            name: 'parasCode',
                            fieldLabel: '参数定义标识'

                        }


                    ]
                }
                ,{
                    xtype:'grid'
                    ,width:'96%'
                    ,padding: '0 10 0 15'
                    ,selModel: 'checkboxmodel'

                    // ,frame:true
                    ,title:'附件'
                    ,collapsible:true
                    ,border:true
                    ,columnLines:true
                    ,store:Ext.create('AM.store.deploy.ops.DevpOpsAttachmentStore')
                    ,tbar: [
                        {   xtype:'form'
                            ,itemId:'fileForm'
                            ,items:[{
                                xtype: 'filefield'
                                ,buttonOnly:true
                                , buttonText:'新增附件'
                                ,listeners:{
                                    change:function (field, value) {
                                        var form = me.down('#fileForm').getForm();

                                        if (form.isValid()) {
                                            form.submit({
                                                url: 'common/attachment/upload'
                                                ,waitMsg: '附件上传中...'
                                                ,success: function(formPanel, action) {
                                                    console.log(action)
                                                    var attachment = action.result.initialPreviewConfig[0];
                                                    var record = me.down('form').getForm().getRecord();

                                                    me.down('grid').getStore().add({
                                                        name:attachment.caption
                                                        ,address:'common/attachment/download/'+attachment.extra.id
                                                        ,code:attachment.extra.id
                                                        ,nexusRid:record.get('id')
                                                        ,nexusType:'Machine'
                                                    });
                                                }
                                                ,failure: function(form, action) {
                                                    console.log(action);
                                                    Ext.Msg.alert('failure', action.failureType);
                                                }
                                            });
                                        }
                                    }
                            }
                        }]}
                    ]
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,menuDisabled: true
                            ,text: '名称'
                            ,flex: 1
                            ,renderer: function (value, field, record) {
                                return '<a href="'+record.get('address')+'">'+record.get('name')+'</a>';
                            }
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'delete'
                                ,tooltip: '删除'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getStore().remove(record);
                                }
                            }]
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
                Ext.MsgUtil.show('操作成功', '保存服务器成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                me.hide(this.targetComp);


                //保存附件与记录的关系()
                // var attachmentRelation = Ext.create('AM.model.deploy.ops.DevpOpsAttachment',{
                //     nexusRid:newRecord.get('id')
                //     ,nexusType:'Machine'
                //     ,code:me.down('#attachmentField').getValue()
                //     ,address:"common/attachment/download/"+me.down('#attachmentField').getValue()
                // });
                // attachmentRelation.save({
                //     success:function () {
                //         Ext.MsgUtil.show('操作成功', '保存附件成功!');
                //     }
                // });

                me.down('grid').getStore().sync({
                    success: function (batch, options) {
                        Ext.MsgUtil.show('操作成功','同步附件成功!');
                    }
                })

            }
        });



    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改服务器信息");

        this.down('form').getForm().loadRecord(model);

        //处理附件的列表
        var attachmentCondition = {nexusRid:model.get('id')}

        var attachmentStore = this.down('grid').getStore();
        attachmentStore.proxy.extraParams = {searchCondition:attachmentCondition};
        attachmentStore.load({
            params:{
                page:0
            }
        });
    },
    setStore: function (store) {
        this.store = store;
    }

});