Ext.define('AM.view.product.系统.DevpSysDiagramSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.product系统DevpSysDiagramSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: 'UML图高级查询',
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
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '系统元素代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '系统元素描述'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '类型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'stereotypeField',
                                            name: 'stereotype',
                                            fieldLabel: '构造型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'versionField',
                                            name: 'version',
                                            fieldLabel: '版本'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'statusField',
                                            name: 'status',
                                            fieldLabel: '状态'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'elmRidField',
                                            name: 'elmRid',
                                            fieldLabel: '系统元素编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'recordStateField',
                                            name: 'recordState',
                                            fieldLabel: '记录状态'
                                        }
                                        ,{
                                            xtype: 'textfield',
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
                                            itemId: 'nameField',
                                            padding: '5 0 0 5',
                                            name: 'name',
                                            fieldLabel: '系统元素名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'aliasField',
                                            padding: '5 0 0 5',
                                            name: 'alias',
                                            fieldLabel: '系统元素别名',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'dgmFlagField',
                                            padding: '5 0 0 5',
                                            name: 'dgmFlag',
                                            fieldLabel: '系统元素所属类型标识',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'subTypeField',
                                            padding: '5 0 0 5',
                                            name: 'subType',
                                            fieldLabel: '子类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'scopeField',
                                            padding: '5 0 0 5',
                                            name: 'scope',
                                            fieldLabel: '范围',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'phaseField',
                                            padding: '5 0 0 5',
                                            name: 'phase',
                                            fieldLabel: '阶段',
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
                                            itemId: 'seqField',
                                            padding: '5 0 0 5',
                                            name: 'seq',
                                            fieldLabel: '顺序号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'createUcodeField',
                                            padding: '5 0 0 5',
                                            name: 'createUcode',
                                            fieldLabel: '创建用户代码',
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var dgmFlagField = me.down("#dgmFlagField");
        var typeField = me.down("#typeField");
        var subTypeField = me.down("#subTypeField");
        var stereotypeField = me.down("#stereotypeField");
        var scopeField = me.down("#scopeField");
        var versionField = me.down("#versionField");
        var phaseField = me.down("#phaseField");
        var statusField = me.down("#statusField");
        var prdRidField = me.down("#prdRidField");
        var elmRidField = me.down("#elmRidField");
        var seqField = me.down("#seqField");
        var recordStateField = me.down("#recordStateField");
        var createUcodeField = me.down("#createUcodeField");
        var modifyUcodeField = me.down("#modifyUcodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,dgmFlag:Ext.isEmpty(dgmFlagField.getValue())?null:dgmFlagField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,subType:Ext.isEmpty(subTypeField.getValue())?null:subTypeField.getValue()
            ,stereotype:Ext.isEmpty(stereotypeField.getValue())?null:stereotypeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,version:Ext.isEmpty(versionField.getValue())?null:versionField.getValue()
            ,phase:Ext.isEmpty(phaseField.getValue())?null:phaseField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,elmRid:Ext.isNumber(elmRidField.getValue())?elmRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
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