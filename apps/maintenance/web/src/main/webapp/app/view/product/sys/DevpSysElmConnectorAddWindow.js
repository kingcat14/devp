Ext.define('AM.view.product.sys.DevpSysElmConnectorAddWindow', {
    extend: 'Ext.window.Window'
    ,requires:[
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新系统元素间关系'
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
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'column'
                            },
                            items: [
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    },
                                    items: [
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            itemId: 'idField',
                                            name: 'id',
                                            fieldLabel: 'Label'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'tidField',
                                            name: 'tid',
                                            fieldLabel: '租户编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '对应关系名称'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '对应关系描述'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'selmRidField',
                                            name: 'selmRid',
                                            fieldLabel: '来源系统元素编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'delmRidField',
                                            name: 'delmRid',
                                            fieldLabel: '目标系统元素编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'dinstRidField',
                                            name: 'dinstRid',
                                            fieldLabel: '目标系统元素实例编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '对应关系类型'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'stereotypeField',
                                            name: 'stereotype',
                                            fieldLabel: '构造型'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'directionField',
                                            name: 'direction',
                                            fieldLabel: '方向'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'srcRoleField',
                                            name: 'srcRole',
                                            fieldLabel: '来源角色'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'destMultiField',
                                            name: 'destMulti',
                                            fieldLabel: '目标对应数量'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'destRoleTypeField',
                                            name: 'destRoleType',
                                            fieldLabel: '目标角色类型'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'recordStateField',
                                            name: 'recordState',
                                            fieldLabel: '记录状态'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'modifyUcodeField',
                                            name: 'modifyUcode',
                                            fieldLabel: '修改用户代码'
                                        }

                                    ]
                                },
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    },
                                    items: [
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            itemId: 'versionField',
                                            name: 'version',
                                            fieldLabel: 'Label'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '对应关系代码'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '对应关系别名'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'sprdRidField',
                                            name: 'sprdRid',
                                            fieldLabel: '来源产品编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'dprdRidField',
                                            name: 'dprdRid',
                                            fieldLabel: '目标产品编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'sinstRidField',
                                            name: 'sinstRid',
                                            fieldLabel: '来源系统元素实例编号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'seqField',
                                            name: 'seq',
                                            fieldLabel: '顺序号'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'subTypeField',
                                            name: 'subType',
                                            fieldLabel: '对应关系子类型'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'scopeField',
                                            name: 'scope',
                                            fieldLabel: '范围'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'srcMultiField',
                                            name: 'srcMulti',
                                            fieldLabel: '来源对应数量'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'srcRoleTypeField',
                                            name: 'srcRoleType',
                                            fieldLabel: '来源角色类型'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'destRoleField',
                                            name: 'destRole',
                                            fieldLabel: '目标角色'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'attrRelationField',
                                            name: 'attrRelation',
                                            fieldLabel: '属性对应关系'
                                        }
                                        ,{
                                            xtype: 'hiddenfield',
                                            allowBlank:true,
                                            itemId: 'createUcodeField',
                                            name: 'createUcode',
                                            fieldLabel: '创建用户代码'
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
        });

        me.callParent(arguments);
    },

    onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存系统元素间关系成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.store.load({
                    callback: function (records, operation, success) {
                        if (success) {
                            Ext.MsgUtil.show('操作成功', '同步系统元素间关系列表成功');
                        }

                    }
                });
            }
        });


        this.hide(this.targetComp);
    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改系统元素间关系信息");
        if(model.phantom){
            this.setTitle("新增系统元素间关系信息");
        }
        this.down('form').getForm().loadRecord(model);
    },
    setStore: function (store) {
        this.store = store;
    }

});