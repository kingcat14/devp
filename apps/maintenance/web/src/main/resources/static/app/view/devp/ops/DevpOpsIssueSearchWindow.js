Ext.define('AM.view.devp.ops.DevpOpsIssueSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.ops.DevpOpsIssueSearchWindow'
    ,alias: 'widget.deployopsDevpOpsIssueSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '问题记录高级查询'
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
                                            fieldLabel: '问题代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '问题别名'
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
                                            itemId: 'issueField',
                                            name: 'issue',
                                            fieldLabel: '问题说明'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'statusField',
                                            name: 'status',
                                            fieldLabel: '处理状态'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'nexusTypeField',
                                            name: 'nexusType',
                                            fieldLabel: '关联记录类型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'nexusVersionField',
                                            name: 'nexusVersion',
                                            fieldLabel: '关联记录版本'
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
                                            itemId: 'cmodifyUcodeField',
                                            name: 'cmodifyUcode',
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
                                            fieldLabel: '问题名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '问题描述',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeField',
                                            padding: '5 0 0 5',
                                            name: 'type',
                                            fieldLabel: '问题类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'replyField',
                                            padding: '5 0 0 5',
                                            name: 'reply',
                                            fieldLabel: '问题回复',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'hasAttachmentField',
                                            padding: '5 0 0 5',
                                            name: 'hasAttachment',
                                            fieldLabel: '是否有附件',
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
                                            itemId: 'nexusPhaseField',
                                            padding: '5 0 0 5',
                                            name: 'nexusPhase',
                                            fieldLabel: '关联记录阶段',
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
        var typeField = me.down("#typeField");
        var issueField = me.down("#issueField");
        var replyField = me.down("#replyField");
        var statusField = me.down("#statusField");
        var hasAttachmentField = me.down("#hasAttachmentField");
        var nexusTypeField = me.down("#nexusTypeField");
        var nexusRidField = me.down("#nexusRidField");
        var nexusVersionField = me.down("#nexusVersionField");
        var nexusPhaseField = me.down("#nexusPhaseField");
        var seqField = me.down("#seqField");
        var parasCodeField = me.down("#parasCodeField");
        var cmodifyUcodeField = me.down("#cmodifyUcodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,issue:Ext.isEmpty(issueField.getValue())?null:issueField.getValue()
            ,reply:Ext.isEmpty(replyField.getValue())?null:replyField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,hasAttachment:Ext.isNumber(hasAttachmentField.getValue())?hasAttachmentField.getValue():null
            ,nexusType:Ext.isEmpty(nexusTypeField.getValue())?null:nexusTypeField.getValue()
            ,nexusRid:Ext.isNumber(nexusRidField.getValue())?nexusRidField.getValue():null
            ,nexusVersion:Ext.isEmpty(nexusVersionField.getValue())?null:nexusVersionField.getValue()
            ,nexusPhase:Ext.isEmpty(nexusPhaseField.getValue())?null:nexusPhaseField.getValue()
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,parasCode:Ext.isEmpty(parasCodeField.getValue())?null:parasCodeField.getValue()
            ,cmodifyUcode:Ext.isEmpty(cmodifyUcodeField.getValue())?null:cmodifyUcodeField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});