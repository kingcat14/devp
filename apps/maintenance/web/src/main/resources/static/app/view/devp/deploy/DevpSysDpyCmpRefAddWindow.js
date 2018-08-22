Ext.define('AM.view.devp.deploy.DevpSysDpyCmpRefAddWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.deploy.DevpSysDpyCmpRefAddWindow'
    ,requires:[
    ]
    ,autoScroll: true
    ,height: 350
    ,width: 600
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新系统元素间关系定义'
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
                            fieldLabel: '对应关系代码'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'nameField',
                            name: 'name',
                            fieldLabel: '对应关系名称'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'aliasField',
                            name: 'alias',
                            fieldLabel: '对应关系别名'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'descriptionField',
                            name: 'description',
                            fieldLabel: '对应关系描述'

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
                            itemId: 'schemeRidField',
                            name: 'schemeRid',
                            fieldLabel: '部署方案编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'cmpRidField',
                            name: 'cmpRid',
                            fieldLabel: '组件编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'refEtypeField',
                            name: 'refEtype',
                            fieldLabel: '关联元素类型'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'refPrdRidField',
                            name: 'refPrdRid',
                            fieldLabel: '关联产品编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'refElmRidField',
                            name: 'refElmRid',
                            fieldLabel: '关联元素编号'

                        }
                        ,{
                            xtype: 'numberfield',
                            allowDecimals:false,
                            allowBlank:false,
                            afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                            ],
                            itemId: 'refInstRidField',
                            name: 'refInstRid',
                            fieldLabel: '关联元素实例编号'

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
                            itemId: 'directionField',
                            name: 'direction',
                            fieldLabel: '方向'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'srcMultiField',
                            name: 'srcMulti',
                            fieldLabel: '来源对应数量'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'srcRoleField',
                            name: 'srcRole',
                            fieldLabel: '来源角色'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'srcRoleTypeField',
                            name: 'srcRoleType',
                            fieldLabel: '来源角色类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'destMultiField',
                            name: 'destMulti',
                            fieldLabel: '目标对应数量'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'destRoleField',
                            name: 'destRole',
                            fieldLabel: '目标角色'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'destRoleTypeField',
                            name: 'destRoleType',
                            fieldLabel: '目标角色类型'

                        }
                        ,{
                            xtype: 'textfield',
                            allowBlank:true,
                            itemId: 'attrRelationField',
                            name: 'attrRelation',
                            fieldLabel: '属性对应关系'

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
                Ext.MsgUtil.show('操作成功', '保存系统元素间关系定义成功!');
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

        this.setTitle("修改系统元素间关系定义信息");
        if(model.phantom){
            this.setTitle("新增系统元素间关系定义信息");
        }
        this.down('form').getForm().loadRecord(model);
    }

});