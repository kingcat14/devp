Ext.define('AM.view.deploy.deploy.DevpSysDpyResInstSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.deploy.DevpSysDpyResInstSearchWindow'
    ,alias: 'widget.deploydeployDevpSysDpyResInstSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '部署关联资源实例定义高级查询'
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
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '系统元素名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '系统元素别名'
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
                                            itemId: 'subTypeField',
                                            name: 'subType',
                                            fieldLabel: '子类型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'scopeField',
                                            name: 'scope',
                                            fieldLabel: '范围'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'phaseField',
                                            name: 'phase',
                                            fieldLabel: '阶段'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'notesField',
                                            name: 'notes',
                                            fieldLabel: '备注'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'resRidField',
                                            name: 'resRid',
                                            fieldLabel: '关联资源编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'seqField',
                                            name: 'seq',
                                            fieldLabel: '顺序号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetEtypeField',
                                            name: 'assetEtype',
                                            fieldLabel: '关联IT资产元素类型'
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
                                            itemId: 'codeField',
                                            padding: '5 0 0 5',
                                            name: 'code',
                                            fieldLabel: '系统元素代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '系统元素描述',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeField',
                                            padding: '5 0 0 5',
                                            name: 'type',
                                            fieldLabel: '类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'stereotypeField',
                                            padding: '5 0 0 5',
                                            name: 'stereotype',
                                            fieldLabel: '构造型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'versionField',
                                            padding: '5 0 0 5',
                                            name: 'version',
                                            fieldLabel: '版本',
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
                                            itemId: 'parentRidField',
                                            padding: '5 0 0 5',
                                            name: 'parentRid',
                                            fieldLabel: '父包编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'assetRidField',
                                            padding: '5 0 0 5',
                                            name: 'assetRid',
                                            fieldLabel: '关联IT资产编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetTypeCodeField',
                                            padding: '5 0 0 5',
                                            name: 'assetTypeCode',
                                            fieldLabel: '关联IT资产类型代码',
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var recordStateField = me.down("#recordStateField");
        var typeField = me.down("#typeField");
        var subTypeField = me.down("#subTypeField");
        var stereotypeField = me.down("#stereotypeField");
        var scopeField = me.down("#scopeField");
        var versionField = me.down("#versionField");
        var phaseField = me.down("#phaseField");
        var statusField = me.down("#statusField");
        var notesField = me.down("#notesField");
        var prdRidField = me.down("#prdRidField");
        var resRidField = me.down("#resRidField");
        var parentRidField = me.down("#parentRidField");
        var seqField = me.down("#seqField");
        var assetRidField = me.down("#assetRidField");
        var assetEtypeField = me.down("#assetEtypeField");
        var assetTypeCodeField = me.down("#assetTypeCodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,subType:Ext.isEmpty(subTypeField.getValue())?null:subTypeField.getValue()
            ,stereotype:Ext.isEmpty(stereotypeField.getValue())?null:stereotypeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,version:Ext.isEmpty(versionField.getValue())?null:versionField.getValue()
            ,phase:Ext.isEmpty(phaseField.getValue())?null:phaseField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,resRid:Ext.isNumber(resRidField.getValue())?resRidField.getValue():null
            ,parentRid:Ext.isNumber(parentRidField.getValue())?parentRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,assetRid:Ext.isNumber(assetRidField.getValue())?assetRidField.getValue():null
            ,assetEtype:Ext.isEmpty(assetEtypeField.getValue())?null:assetEtypeField.getValue()
            ,assetTypeCode:Ext.isEmpty(assetTypeCodeField.getValue())?null:assetTypeCodeField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});