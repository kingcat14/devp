Ext.define('AM.view.devp.ops.DevpOpsElementInfoSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.ops.DevpOpsElementInfoSearchWindow'
    ,alias: 'widget.deployopsDevpOpsElementInfoSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '系统元素扩充信息高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
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
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '扩展信息代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '扩展信息别名'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'recordStateField',
                                            name: 'recordState',
                                            fieldLabel: '记录状态'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'instRidField',
                                            name: 'instRid',
                                            fieldLabel: '元素实例编号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoCode1Field',
                                            name: 'infoCode1',
                                            fieldLabel: '扩展信息代码1'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoCode2Field',
                                            name: 'infoCode2',
                                            fieldLabel: '扩展信息代码2'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoCode3Field',
                                            name: 'infoCode3',
                                            fieldLabel: '扩展信息代码3'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoCode4Field',
                                            name: 'infoCode4',
                                            fieldLabel: '扩展信息代码4'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'infoCode5Field',
                                            name: 'infoCode5',
                                            fieldLabel: '扩展信息代码5'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'notesField',
                                            name: 'notes',
                                            fieldLabel: '备注'
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
                                            itemId: 'etypeField',
                                            padding: '5 0 0 5',
                                            name: 'etype',
                                            fieldLabel: '元素类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'nameField',
                                            padding: '5 0 0 5',
                                            name: 'name',
                                            fieldLabel: '扩展信息名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '扩展信息描述',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'elmRidField',
                                            padding: '5 0 0 5',
                                            name: 'elmRid',
                                            fieldLabel: '元素编号',
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
                                            fieldLabel: '扩展信息值1',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue2Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue2',
                                            fieldLabel: '扩展信息值2',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue3Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue3',
                                            fieldLabel: '扩展信息值3',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue4Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue4',
                                            fieldLabel: '扩展信息值4',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'infoValue5Field',
                                            padding: '5 0 0 5',
                                            name: 'infoValue5',
                                            fieldLabel: '扩展信息值5',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'parasCodeField',
                                            padding: '5 0 0 5',
                                            name: 'parasCode',
                                            fieldLabel: '参数定义标识',
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

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();
        
        me.fireEvent('saved');

       
    }
    ,getCondition: function(){

        var me = this;
        var tidField = me.down("#tidField");
        var etypeField = me.down("#etypeField");
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var recordStateField = me.down("#recordStateField");
        var elmRidField = me.down("#elmRidField");
        var instRidField = me.down("#instRidField");
        var seqField = me.down("#seqField");
        var infoCode1Field = me.down("#infoCode1Field");
        var infoValue1Field = me.down("#infoValue1Field");
        var infoCode2Field = me.down("#infoCode2Field");
        var infoValue2Field = me.down("#infoValue2Field");
        var infoCode3Field = me.down("#infoCode3Field");
        var infoValue3Field = me.down("#infoValue3Field");
        var infoCode4Field = me.down("#infoCode4Field");
        var infoValue4Field = me.down("#infoValue4Field");
        var infoCode5Field = me.down("#infoCode5Field");
        var infoValue5Field = me.down("#infoValue5Field");
        var notesField = me.down("#notesField");
        var parasCodeField = me.down("#parasCodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,elmRid:Ext.isNumber(elmRidField.getValue())?elmRidField.getValue():null
            ,instRid:Ext.isNumber(instRidField.getValue())?instRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,infoCode1:Ext.isEmpty(infoCode1Field.getValue())?null:infoCode1Field.getValue()
            ,infoValue1:Ext.isEmpty(infoValue1Field.getValue())?null:infoValue1Field.getValue()
            ,infoCode2:Ext.isEmpty(infoCode2Field.getValue())?null:infoCode2Field.getValue()
            ,infoValue2:Ext.isEmpty(infoValue2Field.getValue())?null:infoValue2Field.getValue()
            ,infoCode3:Ext.isEmpty(infoCode3Field.getValue())?null:infoCode3Field.getValue()
            ,infoValue3:Ext.isEmpty(infoValue3Field.getValue())?null:infoValue3Field.getValue()
            ,infoCode4:Ext.isEmpty(infoCode4Field.getValue())?null:infoCode4Field.getValue()
            ,infoValue4:Ext.isEmpty(infoValue4Field.getValue())?null:infoValue4Field.getValue()
            ,infoCode5:Ext.isEmpty(infoCode5Field.getValue())?null:infoCode5Field.getValue()
            ,infoValue5:Ext.isEmpty(infoValue5Field.getValue())?null:infoValue5Field.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,parasCode:Ext.isEmpty(parasCodeField.getValue())?null:parasCodeField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});