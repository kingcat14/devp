Ext.define('AM.view.devp.ops.DevpOpsElementInfoAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.ops.DevpOpsElementInfoAddWindow'
    ,requires:[
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新系统元素扩充信息'
    ,maximizable: true
    ,closeAction: 'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,fieldDefaults: {
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,

                    items: [
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'tidField',
                            name: 'tid',
                            fieldLabel: '租户编号'

                        }
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
                            itemId: 'codeField',
                            name: 'code',
                            fieldLabel: '扩展信息代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '扩展信息名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'aliasField',
                            name: 'alias',
                            fieldLabel: '扩展信息别名'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'descriptionField',
                            name: 'description',
                            fieldLabel: '扩展信息描述'

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
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'elmRidField',
                            name: 'elmRid',
                            fieldLabel: '元素编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'instRidField',
                            name: 'instRid',
                            fieldLabel: '元素实例编号'

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
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoCode1Field',
                            name: 'infoCode1',
                            fieldLabel: '扩展信息代码1'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoValue1Field',
                            name: 'infoValue1',
                            fieldLabel: '扩展信息值1'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoCode2Field',
                            name: 'infoCode2',
                            fieldLabel: '扩展信息代码2'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoValue2Field',
                            name: 'infoValue2',
                            fieldLabel: '扩展信息值2'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoCode3Field',
                            name: 'infoCode3',
                            fieldLabel: '扩展信息代码3'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoValue3Field',
                            name: 'infoValue3',
                            fieldLabel: '扩展信息值3'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoCode4Field',
                            name: 'infoCode4',
                            fieldLabel: '扩展信息代码4'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoValue4Field',
                            name: 'infoValue4',
                            fieldLabel: '扩展信息值4'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoCode5Field',
                            name: 'infoCode5',
                            fieldLabel: '扩展信息代码5'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'infoValue5Field',
                            name: 'infoValue5',
                            fieldLabel: '扩展信息值5'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'notesField',
                            name: 'notes',
                            fieldLabel: '备注'

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
                Ext.MsgUtil.show('操作成功', '保存系统元素扩充信息成功!');
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

        this.setTitle("修改系统元素扩充信息信息");
        if(model.phantom){
            this.setTitle("新增系统元素扩充信息信息");
        }
        this.down('form').getForm().loadRecord(model);
    }

});