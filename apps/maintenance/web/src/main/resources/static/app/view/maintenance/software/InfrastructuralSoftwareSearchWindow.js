Ext.define('AM.view.maintenance.software.InfrastructuralSoftwareSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'maintenance.software.InfrastructuralSoftwareSearchWindow'
    ,alias: 'widget.maintenancesoftwareInfrastructuralSoftwareSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '基础软件高级查询'
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
                                            xtype: 'textfield',
                                            itemId: 'typeCodeField',
                                            name: 'typeCode',
                                            fieldLabel: '类型代码'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'hardwareModelField',
                                            name: 'hardwareModel',
                                            fieldLabel: '硬件型号'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'versionField',
                                            name: 'version',
                                            fieldLabel: '版本'
                                        }
                                        ,{
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            itemId: 'createDateField',
                                            name: 'createDate',
                                            fieldLabel: '创建时间'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetProjectField',
                                            name: 'assetProject',
                                            fieldLabel: '所属项目'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetLocationField',
                                            name: 'assetLocation',
                                            fieldLabel: '资产位置'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'extAccessAddrField',
                                            name: 'extAccessAddr',
                                            fieldLabel: '外部访问地址'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'acquisitionDescField',
                                            name: 'acquisitionDesc',
                                            fieldLabel: '获取方式说明'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'assetManagerField',
                                            name: 'assetManager',
                                            fieldLabel: '资产负责人'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'useManagerField',
                                            name: 'useManager',
                                            fieldLabel: '使用负责人'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'opsManagerField',
                                            name: 'opsManager',
                                            fieldLabel: '维护负责人'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'bizManagerField',
                                            name: 'bizManager',
                                            fieldLabel: '业务代表'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'majorCustField',
                                            name: 'majorCust',
                                            fieldLabel: '主要客户'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'custUsageField',
                                            name: 'custUsage',
                                            fieldLabel: '使用情况'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'acquisitionProviderField',
                                            name: 'acquisitionProvider',
                                            fieldLabel: '供应商'
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
                                        // ,{
                                        //     xtype: 'textfield',
                                        //     anchor: '96%',
                                        //     itemId: 'etypeField',
                                        //     padding: '5 0 0 5',
                                        //     name: 'etype',
                                        //     fieldLabel: '元素类型',
                                        //     labelAlign: 'top'
                                        // }
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
                                            itemId: 'typeNameField',
                                            padding: '5 0 0 5',
                                            name: 'typeName',
                                            fieldLabel: '类型名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'softwareModelField',
                                            padding: '5 0 0 5',
                                            name: 'softwareModel',
                                            fieldLabel: '软件型号',
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
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            anchor: '96%',
                                            itemId: 'expireDateField',
                                            padding: '5 0 0 5',
                                            name: 'expireDate',
                                            fieldLabel: '到期时间',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetAreaField',
                                            padding: '5 0 0 5',
                                            name: 'assetArea',
                                            fieldLabel: '所属区域',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'intAccessAddrField',
                                            padding: '5 0 0 5',
                                            name: 'intAccessAddr',
                                            fieldLabel: '内部访问地址',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'acquisitionModeField',
                                            padding: '5 0 0 5',
                                            name: 'acquisitionMode',
                                            fieldLabel: '获取方式',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'assetDeptField',
                                            padding: '5 0 0 5',
                                            name: 'assetDept',
                                            fieldLabel: '归属部门',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'useDeptField',
                                            padding: '5 0 0 5',
                                            name: 'useDept',
                                            fieldLabel: '使用部门',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'opsDeptField',
                                            padding: '5 0 0 5',
                                            name: 'opsDept',
                                            fieldLabel: '维护部门',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'bizLineField',
                                            padding: '5 0 0 5',
                                            name: 'bizLine',
                                            fieldLabel: '业务线',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            anchor: '96%',
                                            itemId: 'goliveDateField',
                                            padding: '5 0 0 5',
                                            name: 'goliveDate',
                                            fieldLabel: '启用时间',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'custManagerField',
                                            padding: '5 0 0 5',
                                            name: 'custManager',
                                            fieldLabel: '客户代表',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'notesField',
                                            padding: '5 0 0 5',
                                            name: 'notes',
                                            fieldLabel: '备注',
                                            labelAlign: 'top'
                                        }
                                        // ,{
                                        //     xtype: 'numberfield',
                                        //     allowDecimals:false,
                                        //     anchor: '96%',
                                        //     itemId: 'recordStateField',
                                        //     padding: '5 0 0 5',
                                        //     name: 'recordState',
                                        //     fieldLabel: '记录状态',
                                        //     labelAlign: 'top'
                                        // }
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
        // var tidField = me.down("#tidField");
        // var etypeField = me.down("#etypeField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var typeCodeField = me.down("#typeCodeField");
        var typeNameField = me.down("#typeNameField");
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
        var acquisitionProviderField = me.down("#acquisitionProviderField");
        var recordStateField = me.down("#recordStateField");

        var condition = {
            // tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            // ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,typeName:Ext.isEmpty(typeNameField.getValue())?null:typeNameField.getValue()
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
            ,acquisitionProvider:Ext.isEmpty(acquisitionProviderField.getValue())?null:acquisitionProviderField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
        };

        return condition;
    }

});