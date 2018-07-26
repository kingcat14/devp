Ext.define('AM.view.deploy.deploy.DevpSysDpyCmpRefSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.deploy.DevpSysDpyCmpRefSearchWindow'
    ,alias: 'widget.deploydeployDevpSysDpyCmpRefSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '系统元素间关系定义高级查询'
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
                                            fieldLabel: '对应关系代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '对应关系别名'
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
                                            itemId: 'schemeRidField',
                                            name: 'schemeRid',
                                            fieldLabel: '部署方案编号'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'refEtypeField',
                                            name: 'refEtype',
                                            fieldLabel: '关联元素类型'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'refElmRidField',
                                            name: 'refElmRid',
                                            fieldLabel: '关联元素编号'
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
                                            itemId: 'srcMultiField',
                                            name: 'srcMulti',
                                            fieldLabel: '来源对应数量'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'srcRoleTypeField',
                                            name: 'srcRoleType',
                                            fieldLabel: '来源角色类型'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'destRoleField',
                                            name: 'destRole',
                                            fieldLabel: '目标角色'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'attrRelationField',
                                            name: 'attrRelation',
                                            fieldLabel: '属性对应关系'
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
                                            fieldLabel: '对应关系名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '对应关系描述',
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
                                            itemId: 'cmpRidField',
                                            padding: '5 0 0 5',
                                            name: 'cmpRid',
                                            fieldLabel: '组件编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'refPrdRidField',
                                            padding: '5 0 0 5',
                                            name: 'refPrdRid',
                                            fieldLabel: '关联产品编号',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'refInstRidField',
                                            padding: '5 0 0 5',
                                            name: 'refInstRid',
                                            fieldLabel: '关联元素实例编号',
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
                                            itemId: 'directionField',
                                            padding: '5 0 0 5',
                                            name: 'direction',
                                            fieldLabel: '方向',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'srcRoleField',
                                            padding: '5 0 0 5',
                                            name: 'srcRole',
                                            fieldLabel: '来源角色',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'destMultiField',
                                            padding: '5 0 0 5',
                                            name: 'destMulti',
                                            fieldLabel: '目标对应数量',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'destRoleTypeField',
                                            padding: '5 0 0 5',
                                            name: 'destRoleType',
                                            fieldLabel: '目标角色类型',
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
        var prdRidField = me.down("#prdRidField");
        var schemeRidField = me.down("#schemeRidField");
        var cmpRidField = me.down("#cmpRidField");
        var refEtypeField = me.down("#refEtypeField");
        var refPrdRidField = me.down("#refPrdRidField");
        var refElmRidField = me.down("#refElmRidField");
        var refInstRidField = me.down("#refInstRidField");
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

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,schemeRid:Ext.isNumber(schemeRidField.getValue())?schemeRidField.getValue():null
            ,cmpRid:Ext.isNumber(cmpRidField.getValue())?cmpRidField.getValue():null
            ,refEtype:Ext.isNumber(refEtypeField.getValue())?refEtypeField.getValue():null
            ,refPrdRid:Ext.isNumber(refPrdRidField.getValue())?refPrdRidField.getValue():null
            ,refElmRid:Ext.isNumber(refElmRidField.getValue())?refElmRidField.getValue():null
            ,refInstRid:Ext.isNumber(refInstRidField.getValue())?refInstRidField.getValue():null
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
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});