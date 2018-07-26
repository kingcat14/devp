Ext.define('AM.view.product.系统.DevpSysElmInstInfoSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.product系统DevpSysElmInstInfoSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '系统元素实例高级查询',
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
                                            fieldLabel: '扩展信息名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '扩展信息描述'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'contRidField',
                                            name: 'contRid',
                                            fieldLabel: '关联连接编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'selmRidField',
                                            name: 'selmRid',
                                            fieldLabel: '来源系统元素编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'delmRidField',
                                            name: 'delmRid',
                                            fieldLabel: '目标系统元素编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'dinstRidField',
                                            name: 'dinstRid',
                                            fieldLabel: '目标系统元素实例编号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '设值方式'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoValue2Field',
                                            name: 'infoValue2',
                                            fieldLabel: '信息值2'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoValue4Field',
                                            name: 'infoValue4',
                                            fieldLabel: '信息值4'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'notesField',
                                            name: 'notes',
                                            fieldLabel: '备注'
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
                                            fieldLabel: '扩展信息代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'aliasField',
                                            padding: '5 0 0 5',
                                            name: 'alias',
                                            fieldLabel: '扩展信息别名',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'prdRidField',
                                            padding: '5 0 0 5',
                                            name: 'prdRid',
                                            fieldLabel: '产品编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'sprdRidField',
                                            padding: '5 0 0 5',
                                            name: 'sprdRid',
                                            fieldLabel: '来源产品编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'dprdRidField',
                                            padding: '5 0 0 5',
                                            name: 'dprdRid',
                                            fieldLabel: '目标产品编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'sinstRidField',
                                            padding: '5 0 0 5',
                                            name: 'sinstRid',
                                            fieldLabel: '来源系统元素实例编号',
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
                                            itemId: 'infoValue1Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue1',
                                            fieldLabel: '信息值1',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue3Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue3',
                                            fieldLabel: '信息值3',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue5Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue5',
                                            fieldLabel: '信息值5',
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
        var prdRidField = me.down("#prdRidField");
        var contRidField = me.down("#contRidField");
        var sprdRidField = me.down("#sprdRidField");
        var selmRidField = me.down("#selmRidField");
        var dprdRidField = me.down("#dprdRidField");
        var delmRidField = me.down("#delmRidField");
        var sinstRidField = me.down("#sinstRidField");
        var dinstRidField = me.down("#dinstRidField");
        var seqField = me.down("#seqField");
        var typeField = me.down("#typeField");
        var infoValue1Field = me.down("#infoValue1Field");
        var infoValue2Field = me.down("#infoValue2Field");
        var infoValue3Field = me.down("#infoValue3Field");
        var infoValue4Field = me.down("#infoValue4Field");
        var infoValue5Field = me.down("#infoValue5Field");
        var notesField = me.down("#notesField");
        var recordStateField = me.down("#recordStateField");
        var createUcodeField = me.down("#createUcodeField");
        var modifyUcodeField = me.down("#modifyUcodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,contRid:Ext.isNumber(contRidField.getValue())?contRidField.getValue():null
            ,sprdRid:Ext.isNumber(sprdRidField.getValue())?sprdRidField.getValue():null
            ,selmRid:Ext.isNumber(selmRidField.getValue())?selmRidField.getValue():null
            ,dprdRid:Ext.isNumber(dprdRidField.getValue())?dprdRidField.getValue():null
            ,delmRid:Ext.isNumber(delmRidField.getValue())?delmRidField.getValue():null
            ,sinstRid:Ext.isNumber(sinstRidField.getValue())?sinstRidField.getValue():null
            ,dinstRid:Ext.isNumber(dinstRidField.getValue())?dinstRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,infoValue1:Ext.isEmpty(infoValue1Field.getValue())?null:infoValue1Field.getValue()
            ,infoValue2:Ext.isEmpty(infoValue2Field.getValue())?null:infoValue2Field.getValue()
            ,infoValue3:Ext.isEmpty(infoValue3Field.getValue())?null:infoValue3Field.getValue()
            ,infoValue4:Ext.isEmpty(infoValue4Field.getValue())?null:infoValue4Field.getValue()
            ,infoValue5:Ext.isEmpty(infoValue5Field.getValue())?null:infoValue5Field.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
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