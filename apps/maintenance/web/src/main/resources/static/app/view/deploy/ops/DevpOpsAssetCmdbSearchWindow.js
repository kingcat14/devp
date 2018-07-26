Ext.define('AM.view.deploy.ops.DevpOpsAssetCmdbSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsAssetCmdbSearchWindow'
    ,alias: 'widget.deployopsDevpOpsAssetCmdbSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '资产定义高级查询'
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
                                            fieldLabel: '名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'aliasField',
                                            name: 'alias',
                                            fieldLabel: '别名'
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
                                            itemId: 'typeNameField',
                                            name: 'typeName',
                                            fieldLabel: '类型名称'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'scopeField',
                                            name: 'scope',
                                            fieldLabel: '范围'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'softwareModelField',
                                            name: 'softwareModel',
                                            fieldLabel: '软件型号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'statusField',
                                            name: 'status',
                                            fieldLabel: '状态'
                                        }
                                        ,{
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            itemId: 'expireDateField',
                                            name: 'expireDate',
                                            fieldLabel: '到期时间'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetAreaField',
                                            name: 'assetArea',
                                            fieldLabel: '所属区域'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'intAccessAddrField',
                                            name: 'intAccessAddr',
                                            fieldLabel: '内部访问地址'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'acquisitionModeField',
                                            name: 'acquisitionMode',
                                            fieldLabel: '获取方式'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetDeptField',
                                            name: 'assetDept',
                                            fieldLabel: '归属部门'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'useDeptField',
                                            name: 'useDept',
                                            fieldLabel: '使用部门'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'opsDeptField',
                                            name: 'opsDept',
                                            fieldLabel: '维护部门'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'bizLineField',
                                            name: 'bizLine',
                                            fieldLabel: '业务线'
                                        }
                                        ,{
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            itemId: 'goliveDateField',
                                            name: 'goliveDate',
                                            fieldLabel: '启用时间'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'custManagerField',
                                            name: 'custManager',
                                            fieldLabel: '客户代表'
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
                                            itemId: 'prdRidField',
                                            name: 'prdRid',
                                            fieldLabel: '关联产品记录编号'
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
                                            fieldLabel: '代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '描述',
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
                                            itemId: 'stereotypeField',
                                            padding: '5 0 0 5',
                                            name: 'stereotype',
                                            fieldLabel: '构造型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'hardwareModelField',
                                            padding: '5 0 0 5',
                                            name: 'hardwareModel',
                                            fieldLabel: '硬件型号',
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
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            anchor: '96%',
                                            itemId: 'createDateField',
                                            padding: '5 0 0 5',
                                            name: 'createDate',
                                            fieldLabel: '创建时间',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetProjectField',
                                            padding: '5 0 0 5',
                                            name: 'assetProject',
                                            fieldLabel: '所属项目',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetLocationField',
                                            padding: '5 0 0 5',
                                            name: 'assetLocation',
                                            fieldLabel: '资产位置',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'extAccessAddrField',
                                            padding: '5 0 0 5',
                                            name: 'extAccessAddr',
                                            fieldLabel: '外部访问地址',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'acquisitionDescField',
                                            padding: '5 0 0 5',
                                            name: 'acquisitionDesc',
                                            fieldLabel: '获取方式说明',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetManagerField',
                                            padding: '5 0 0 5',
                                            name: 'assetManager',
                                            fieldLabel: '资产负责人',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'useManagerField',
                                            padding: '5 0 0 5',
                                            name: 'useManager',
                                            fieldLabel: '使用负责人',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'opsManagerField',
                                            padding: '5 0 0 5',
                                            name: 'opsManager',
                                            fieldLabel: '维护负责人',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'bizManagerField',
                                            padding: '5 0 0 5',
                                            name: 'bizManager',
                                            fieldLabel: '业务代表',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'majorCustField',
                                            padding: '5 0 0 5',
                                            name: 'majorCust',
                                            fieldLabel: '主要客户',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'custUsageField',
                                            padding: '5 0 0 5',
                                            name: 'custUsage',
                                            fieldLabel: '使用情况',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'prdTidField',
                                            padding: '5 0 0 5',
                                            name: 'prdTid',
                                            fieldLabel: '关联产品租户编号',
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var recordStateField = me.down("#recordStateField");
        var typeCodeField = me.down("#typeCodeField");
        var typeNameField = me.down("#typeNameField");
        var stereotypeField = me.down("#stereotypeField");
        var scopeField = me.down("#scopeField");
        var hardwareModelField = me.down("#hardwareModelField");
        var softwareModelField = me.down("#softwareModelField");
        var versionField = me.down("#versionField");
        var statusField = me.down("#statusField");
        var createDateField = me.down("#createDateField");
        var expireDateField = me.down("#expireDateField");
        var assetProjectField = me.down("#assetProjectField");
        var assetAreaField = me.down("#assetAreaField");
        var assetLocationField = me.down("#assetLocationField");
        var intAccessAddrField = me.down("#intAccessAddrField");
        var extAccessAddrField = me.down("#extAccessAddrField");
        var acquisitionModeField = me.down("#acquisitionModeField");
        var acquisitionDescField = me.down("#acquisitionDescField");
        var assetDeptField = me.down("#assetDeptField");
        var assetManagerField = me.down("#assetManagerField");
        var useDeptField = me.down("#useDeptField");
        var useManagerField = me.down("#useManagerField");
        var opsDeptField = me.down("#opsDeptField");
        var opsManagerField = me.down("#opsManagerField");
        var bizLineField = me.down("#bizLineField");
        var bizManagerField = me.down("#bizManagerField");
        var goliveDateField = me.down("#goliveDateField");
        var majorCustField = me.down("#majorCustField");
        var custManagerField = me.down("#custManagerField");
        var custUsageField = me.down("#custUsageField");
        var notesField = me.down("#notesField");
        var prdTidField = me.down("#prdTidField");
        var prdRidField = me.down("#prdRidField");
        var parasCodeField = me.down("#parasCodeField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,typeName:Ext.isEmpty(typeNameField.getValue())?null:typeNameField.getValue()
            ,stereotype:Ext.isEmpty(stereotypeField.getValue())?null:stereotypeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,hardwareModel:Ext.isEmpty(hardwareModelField.getValue())?null:hardwareModelField.getValue()
            ,softwareModel:Ext.isEmpty(softwareModelField.getValue())?null:softwareModelField.getValue()
            ,version:Ext.isEmpty(versionField.getValue())?null:versionField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,createDate:Ext.isEmpty(createDateField.getValue())?null:Ext.Date.format(createDateField.getValue(),'Y-m-d')
            ,expireDate:Ext.isEmpty(expireDateField.getValue())?null:Ext.Date.format(expireDateField.getValue(),'Y-m-d')
            ,assetProject:Ext.isEmpty(assetProjectField.getValue())?null:assetProjectField.getValue()
            ,assetArea:Ext.isEmpty(assetAreaField.getValue())?null:assetAreaField.getValue()
            ,assetLocation:Ext.isEmpty(assetLocationField.getValue())?null:assetLocationField.getValue()
            ,intAccessAddr:Ext.isEmpty(intAccessAddrField.getValue())?null:intAccessAddrField.getValue()
            ,extAccessAddr:Ext.isEmpty(extAccessAddrField.getValue())?null:extAccessAddrField.getValue()
            ,acquisitionMode:Ext.isEmpty(acquisitionModeField.getValue())?null:acquisitionModeField.getValue()
            ,acquisitionDesc:Ext.isEmpty(acquisitionDescField.getValue())?null:acquisitionDescField.getValue()
            ,assetDept:Ext.isEmpty(assetDeptField.getValue())?null:assetDeptField.getValue()
            ,assetManager:Ext.isEmpty(assetManagerField.getValue())?null:assetManagerField.getValue()
            ,useDept:Ext.isEmpty(useDeptField.getValue())?null:useDeptField.getValue()
            ,useManager:Ext.isEmpty(useManagerField.getValue())?null:useManagerField.getValue()
            ,opsDept:Ext.isEmpty(opsDeptField.getValue())?null:opsDeptField.getValue()
            ,opsManager:Ext.isEmpty(opsManagerField.getValue())?null:opsManagerField.getValue()
            ,bizLine:Ext.isEmpty(bizLineField.getValue())?null:bizLineField.getValue()
            ,bizManager:Ext.isEmpty(bizManagerField.getValue())?null:bizManagerField.getValue()
            ,goliveDate:Ext.isEmpty(goliveDateField.getValue())?null:Ext.Date.format(goliveDateField.getValue(),'Y-m-d')
            ,majorCust:Ext.isEmpty(majorCustField.getValue())?null:majorCustField.getValue()
            ,custManager:Ext.isEmpty(custManagerField.getValue())?null:custManagerField.getValue()
            ,custUsage:Ext.isEmpty(custUsageField.getValue())?null:custUsageField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,prdTid:Ext.isNumber(prdTidField.getValue())?prdTidField.getValue():null
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,parasCode:Ext.isEmpty(parasCodeField.getValue())?null:parasCodeField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});