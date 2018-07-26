Ext.define('AM.view.deploy.ops.DevpOpsIssueAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsIssueAddWindow'
    ,requires:[
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新问题记录'
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
                            allowBlank:true,
                            itemId: 'codeField',
                            name: 'code',
                            fieldLabel: '问题代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '问题名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'aliasField',
                            name: 'alias',
                            fieldLabel: '问题别名'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'descriptionField',
                            name: 'description',
                            fieldLabel: '问题描述'

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
                            fieldLabel: '问题类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'issueField',
                            name: 'issue',
                            fieldLabel: '问题说明'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'replyField',
                            name: 'reply',
                            fieldLabel: '问题回复'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'statusField',
                            name: 'status',
                            fieldLabel: '处理状态'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:true,
                            itemId: 'hasAttachmentField',
                            name: 'hasAttachment',
                            fieldLabel: '是否有附件'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'nexusTypeField',
                            name: 'nexusType',
                            fieldLabel: '关联记录类型'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'nexusRidField',
                            name: 'nexusRid',
                            fieldLabel: '关联记录编号'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'nexusVersionField',
                            name: 'nexusVersion',
                            fieldLabel: '关联记录版本'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'nexusPhaseField',
                            name: 'nexusPhase',
                            fieldLabel: '关联记录阶段'

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
                            itemId: 'parasCodeField',
                            name: 'parasCode',
                            fieldLabel: '参数定义标识'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'cmodifyUcodeField',
                            name: 'cmodifyUcode',
                            fieldLabel: '修改用户代码'

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
                Ext.MsgUtil.show('操作成功', '保存问题记录成功!');
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

        this.setTitle("修改问题记录信息");
        if(model.phantom){
            this.setTitle("新增问题记录信息");
        }
        this.down('form').getForm().loadRecord(model);
    }

});