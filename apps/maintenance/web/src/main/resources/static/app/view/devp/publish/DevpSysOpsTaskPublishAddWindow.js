Ext.define('AM.view.devp.publish.DevpSysOpsTaskPublishAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsTaskPublishAddWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'

    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
    }
    ,title: '添加新发布设置'
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
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'tidField'
                                    ,name: 'tid'
                                    ,fieldLabel: '租户编号'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'etypeField'
                                    ,name: 'etype'
                                    ,fieldLabel: '元素类型'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '系统元素名称'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '系统元素代码'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'aliasField'
                                    ,name: 'alias'
                                    ,fieldLabel: '系统元素别名'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'descriptionField'
                                    ,name: 'description'
                                    ,fieldLabel: '系统元素描述'
                                }


                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'recordStateField'
                                    ,name: 'recordState'
                                    ,fieldLabel: '记录状态'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '类型'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'subTypeField'
                                    ,name: 'subType'
                                    ,fieldLabel: '子类型'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'templateField'
                                    ,name: 'template'
                                    ,fieldLabel: '参考模板'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'groupField'
                                    ,name: 'group'
                                    ,fieldLabel: '发布物分组'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'artifactField'
                                    ,name: 'artifact'
                                    ,fieldLabel: '发布物'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'versionField'
                                    ,name: 'version'
                                    ,fieldLabel: '版本标识'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'publishFileField'
                                    ,name: 'publishFile'
                                    ,fieldLabel: '发布文件名'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'statusField'
                                    ,name: 'status'
                                    ,fieldLabel: '状态'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'notesField'
                                    ,name: 'notes'
                                    ,fieldLabel: '备注'
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
                                    ,fieldLabel: '产品编号'
                                }


                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'schemeRidField'
                                    ,name: 'schemeRid'
                                    ,fieldLabel: '部署方案编号'
                                }


                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'cmpRidField'
                                    ,name: 'cmpRid'
                                    ,fieldLabel: '组件编号'
                                }


                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'taskRidField'
                                    ,name: 'taskRid'
                                    ,fieldLabel: '任务编号'
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


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'createUcodeField'
                                    ,name: 'createUcode'
                                    ,fieldLabel: '创建用户代码'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'createUnameField'
                                    ,name: 'createUname'
                                    ,fieldLabel: '创建用户姓名'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'modifyUcodeField'
                                    ,name: 'modifyUcode'
                                    ,fieldLabel: '修改用户代码'
                                }


                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'modifyUnameField'
                                    ,name: 'modifyUname'
                                    ,fieldLabel: '修改用户姓名'
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
                Ext.MsgUtil.show('操作成功', '保存发布设置成功!');
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
        this.down('form').getForm().loadRecord(model);

    }

});