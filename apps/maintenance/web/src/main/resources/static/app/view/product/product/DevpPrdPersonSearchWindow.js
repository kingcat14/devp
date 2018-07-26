Ext.define('AM.view.product.product.DevpPrdPersonSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.productproductDevpPrdPersonSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '产品干系人高级查询',
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
                    items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [

                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'tidField',
                                            name: 'tid',
                                            fieldLabel: '租户编号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '用户名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '用户描述'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'nexusRidField',
                                            name: 'nexusRid',
                                            fieldLabel: '关联元素编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'uidField',
                                            name: 'uid',
                                            fieldLabel: '用户编号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'roleField',
                                            name: 'role',
                                            fieldLabel: '用户角色'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'userTidField',
                                            name: 'userTid',
                                            fieldLabel: '用户租户编号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'orgNameField',
                                            name: 'orgName',
                                            fieldLabel: '组织名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'createUcodeField',
                                            name: 'createUcode',
                                            fieldLabel: '创建用户代码'
                                        }

                                    ]
                                },
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'codeField',
                                            padding: '5 0 0 5',
                                            name: 'code',
                                            fieldLabel: '用户代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'aliasField',
                                            padding: '5 0 0 5',
                                            name: 'alias',
                                            fieldLabel: '用户别名',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'nexusTypeField',
                                            padding: '5 0 0 5',
                                            name: 'nexusType',
                                            fieldLabel: '关联元素类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'seqField',
                                            padding: '5 0 0 5',
                                            name: 'seq',
                                            fieldLabel: '顺序号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeField',
                                            padding: '5 0 0 5',
                                            name: 'type',
                                            fieldLabel: '用户类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'statusField',
                                            padding: '5 0 0 5',
                                            name: 'status',
                                            fieldLabel: '状态',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'orgRidField',
                                            padding: '5 0 0 5',
                                            name: 'orgRid',
                                            fieldLabel: '组织编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'recordStateField',
                                            padding: '5 0 0 5',
                                            name: 'recordState',
                                            fieldLabel: '记录状态',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'modifyUcodeField',
                                            padding: '5 0 0 5',
                                            name: 'modifyUcode',
                                            fieldLabel: '修改用户代码',
                                            labelAlign: 'top'
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
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var tidField = me.down("#tidField");
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var nexusTypeField = me.down("#nexusTypeField");
        var nexusRidField = me.down("#nexusRidField");
        var seqField = me.down("#seqField");
        var uidField = me.down("#uidField");
        var typeField = me.down("#typeField");
        var roleField = me.down("#roleField");
        var statusField = me.down("#statusField");
        var userTidField = me.down("#userTidField");
        var orgRidField = me.down("#orgRidField");
        var orgNameField = me.down("#orgNameField");
        var recordStateField = me.down("#recordStateField");
        var createUcodeField = me.down("#createUcodeField");
        var modifyUcodeField = me.down("#modifyUcodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,nexusType:Ext.isEmpty(nexusTypeField.getValue())?null:nexusTypeField.getValue()
            ,nexusRid:Ext.isNumber(nexusRidField.getValue())?nexusRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,uid:Ext.isNumber(uidField.getValue())?uidField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,role:Ext.isEmpty(roleField.getValue())?null:roleField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,userTid:Ext.isNumber(userTidField.getValue())?userTidField.getValue():null
            ,orgRid:Ext.isNumber(orgRidField.getValue())?orgRidField.getValue():null
            ,orgName:Ext.isEmpty(orgNameField.getValue())?null:orgNameField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,createUcode:Ext.isEmpty(createUcodeField.getValue())?null:createUcodeField.getValue()
            ,modifyUcode:Ext.isEmpty(modifyUcodeField.getValue())?null:modifyUcodeField.getValue()
        };

        this.store.proxy.extraParams={searchCondition:condition};
        this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }
    ,onRestButtonClick: function (button, e, options) {

        this.down('form').getForm().reset();

        this.store.proxy.extraParams={searchCondition:{}};
            this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }

    ,setStore: function (store) {
        this.store = store;
    }

});