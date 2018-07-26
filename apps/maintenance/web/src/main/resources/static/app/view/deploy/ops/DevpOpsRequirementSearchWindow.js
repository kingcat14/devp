Ext.define('AM.view.deploy.ops.DevpOpsRequirementSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsRequirementSearchWindow'
    ,alias: 'widget.deployopsDevpOpsRequirementSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '需求定义高级查询'
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
                                            fieldLabel: '需求代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '需求别名'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'nexusTypeField',
                                            name: 'nexusType',
                                            fieldLabel: '关联记录类型'
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
                                            itemId: 'typeNameField',
                                            name: 'typeName',
                                            fieldLabel: '类型名称'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'hasAttachmentField',
                                            name: 'hasAttachment',
                                            fieldLabel: '是否有附件'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'scopeField',
                                            name: 'scope',
                                            fieldLabel: '访问控制范围'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'phaseField',
                                            name: 'phase',
                                            fieldLabel: '阶段'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'recordStateField',
                                            name: 'recordState',
                                            fieldLabel: '记录状态'
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
                                            fieldLabel: '需求名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '需求描述',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'nexusRidField',
                                            padding: '5 0 0 5',
                                            name: 'nexusRid',
                                            fieldLabel: '关联记录编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeCodeField',
                                            padding: '5 0 0 5',
                                            name: 'typeCode',
                                            fieldLabel: '类型代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'contentField',
                                            padding: '5 0 0 5',
                                            name: 'content',
                                            fieldLabel: '内容',
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
        var nexusTypeField = me.down("#nexusTypeField");
        var nexusRidField = me.down("#nexusRidField");
        var seqField = me.down("#seqField");
        var typeCodeField = me.down("#typeCodeField");
        var typeNameField = me.down("#typeNameField");
        var contentField = me.down("#contentField");
        var hasAttachmentField = me.down("#hasAttachmentField");
        var stereotypeField = me.down("#stereotypeField");
        var scopeField = me.down("#scopeField");
        var versionField = me.down("#versionField");
        var phaseField = me.down("#phaseField");
        var statusField = me.down("#statusField");
        var recordStateField = me.down("#recordStateField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,nexusType:Ext.isEmpty(nexusTypeField.getValue())?null:nexusTypeField.getValue()
            ,nexusRid:Ext.isNumber(nexusRidField.getValue())?nexusRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,typeName:Ext.isEmpty(typeNameField.getValue())?null:typeNameField.getValue()
            ,content:Ext.isEmpty(contentField.getValue())?null:contentField.getValue()
            ,hasAttachment:Ext.isNumber(hasAttachmentField.getValue())?hasAttachmentField.getValue():null
            ,stereotype:Ext.isEmpty(stereotypeField.getValue())?null:stereotypeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,version:Ext.isEmpty(versionField.getValue())?null:versionField.getValue()
            ,phase:Ext.isEmpty(phaseField.getValue())?null:phaseField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});