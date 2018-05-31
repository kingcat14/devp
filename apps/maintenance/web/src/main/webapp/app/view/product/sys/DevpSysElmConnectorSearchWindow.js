Ext.define('AM.view.product.系统.DevpSysElmConnectorSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.product系统DevpSysElmConnectorSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '系统元素间关系高级查询',
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
                                            fieldLabel: '对应关系名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '对应关系描述'
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
                                            fieldLabel: '对应关系类型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'stereotypeField',
                                            name: 'stereotype',
                                            fieldLabel: '构造型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'directionField',
                                            name: 'direction',
                                            fieldLabel: '方向'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'srcRoleField',
                                            name: 'srcRole',
                                            fieldLabel: '来源角色'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'destMultiField',
                                            name: 'destMulti',
                                            fieldLabel: '目标对应数量'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'destRoleTypeField',
                                            name: 'destRoleType',
                                            fieldLabel: '目标角色类型'
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
                                            itemId: 'codeField',
                                            padding: '5 0 0 5',
                                            name: 'code',
                                            fieldLabel: '对应关系代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'aliasField',
                                            padding: '5 0 0 5',
                                            name: 'alias',
                                            fieldLabel: '对应关系别名',
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
                                            itemId: 'subTypeField',
                                            padding: '5 0 0 5',
                                            name: 'subType',
                                            fieldLabel: '对应关系子类型',
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
                                            itemId: 'srcMultiField',
                                            padding: '5 0 0 5',
                                            name: 'srcMulti',
                                            fieldLabel: '来源对应数量',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'srcRoleTypeField',
                                            padding: '5 0 0 5',
                                            name: 'srcRoleType',
                                            fieldLabel: '来源角色类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'destRoleField',
                                            padding: '5 0 0 5',
                                            name: 'destRole',
                                            fieldLabel: '目标角色',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'attrRelationField',
                                            padding: '5 0 0 5',
                                            name: 'attrRelation',
                                            fieldLabel: '属性对应关系',
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
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var sprdRidField = me.down("#sprdRidField");
        var selmRidField = me.down("#selmRidField");
        var dprdRidField = me.down("#dprdRidField");
        var delmRidField = me.down("#delmRidField");
        var sinstRidField = me.down("#sinstRidField");
        var dinstRidField = me.down("#dinstRidField");
        var seqField = me.down("#seqField");
        var typeField = me.down("#typeField");
        var subTypeField = me.down("#subTypeField");
        var stereotypeField = me.down("#stereotypeField");
        var scopeField = me.down("#scopeField");
        var directionField = me.down("#directionField");
        var srcMultiField = me.down("#srcMultiField");
        var srcRoleField = me.down("#srcRoleField");
        var srcRoleTypeField = me.down("#srcRoleTypeField");
        var destMultiField = me.down("#destMultiField");
        var destRoleField = me.down("#destRoleField");
        var destRoleTypeField = me.down("#destRoleTypeField");
        var attrRelationField = me.down("#attrRelationField");
        var recordStateField = me.down("#recordStateField");
        var createUcodeField = me.down("#createUcodeField");
        var modifyUcodeField = me.down("#modifyUcodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,sprdRid:Ext.isNumber(sprdRidField.getValue())?sprdRidField.getValue():null
            ,selmRid:Ext.isNumber(selmRidField.getValue())?selmRidField.getValue():null
            ,dprdRid:Ext.isNumber(dprdRidField.getValue())?dprdRidField.getValue():null
            ,delmRid:Ext.isNumber(delmRidField.getValue())?delmRidField.getValue():null
            ,sinstRid:Ext.isNumber(sinstRidField.getValue())?sinstRidField.getValue():null
            ,dinstRid:Ext.isNumber(dinstRidField.getValue())?dinstRidField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,subType:Ext.isEmpty(subTypeField.getValue())?null:subTypeField.getValue()
            ,stereotype:Ext.isEmpty(stereotypeField.getValue())?null:stereotypeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,direction:Ext.isEmpty(directionField.getValue())?null:directionField.getValue()
            ,srcMulti:Ext.isEmpty(srcMultiField.getValue())?null:srcMultiField.getValue()
            ,srcRole:Ext.isEmpty(srcRoleField.getValue())?null:srcRoleField.getValue()
            ,srcRoleType:Ext.isEmpty(srcRoleTypeField.getValue())?null:srcRoleTypeField.getValue()
            ,destMulti:Ext.isEmpty(destMultiField.getValue())?null:destMultiField.getValue()
            ,destRole:Ext.isEmpty(destRoleField.getValue())?null:destRoleField.getValue()
            ,destRoleType:Ext.isEmpty(destRoleTypeField.getValue())?null:destRoleTypeField.getValue()
            ,attrRelation:Ext.isEmpty(attrRelationField.getValue())?null:attrRelationField.getValue()
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