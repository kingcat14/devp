Ext.define('AM.view.maintenance.software.BusinessSoftwareDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'maintenance.software.BusinessSoftwareDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '应用软件详细信息'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,layout: {
                        type: 'anchor'
                    }
                    ,defaults: {
                        labelAlign: 'right'
                        ,xtype: 'displayfield'
                        ,padding: '5 0 0 5'
                        ,anchor: '100%'
                        ,labelWidth:150
                    }
                    ,items: [
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '代码'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
                        }
                        ,{
                            itemId: 'typeCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'typeCode'
                            ,fieldLabel: '类型代码'
                        }
                        ,{
                            itemId: 'hardwareModelField'
                            ,padding: '5 0 0 5'
                            ,name: 'hardwareModel'
                            ,fieldLabel: '硬件型号'
                        }
                        ,{
                            itemId: 'softwareModelField'
                            ,padding: '5 0 0 5'
                            ,name: 'softwareModel'
                            ,fieldLabel: '软件型号'
                        }
                        ,{
                            itemId: 'versionField'
                            ,padding: '5 0 0 5'
                            ,name: 'version'
                            ,fieldLabel: '版本'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '状态'
                        }
                        ,{
                            itemId: 'createDateField'
                            ,padding: '5 0 0 5'
                            ,name: 'createDate'
                            ,fieldLabel: '创建时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d')
                            }
                        }
                        ,{
                            itemId: 'expireDateField'
                            ,padding: '5 0 0 5'
                            ,name: 'expireDate'
                            ,fieldLabel: '到期时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d')
                            }
                        }
                        ,{
                            itemId: 'assetProjectField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetProject'
                            ,fieldLabel: '所属项目'
                        }
                        ,{
                            itemId: 'assetAreaField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetArea'
                            ,fieldLabel: '所属区域'
                        }
                        ,{
                            itemId: 'assetLocationField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetLocation'
                            ,fieldLabel: '资产位置'
                        }
                        ,{
                            itemId: 'intAccessAddrField'
                            ,padding: '5 0 0 5'
                            ,name: 'intAccessAddr'
                            ,fieldLabel: '内部访问地址'
                        }
                        ,{
                            itemId: 'extAccessAddrField'
                            ,padding: '5 0 0 5'
                            ,name: 'extAccessAddr'
                            ,fieldLabel: '外部访问地址'
                        }
                        ,{
                            itemId: 'acquisitionModeField'
                            ,padding: '5 0 0 5'
                            ,name: 'acquisitionMode'
                            ,fieldLabel: '获取方式'
                        }
                        ,{
                            itemId: 'acquisitionDescField'
                            ,padding: '5 0 0 5'
                            ,name: 'acquisitionDesc'
                            ,fieldLabel: '获取方式说明'
                        }
                        ,{
                            itemId: 'assetDeptField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetDept'
                            ,fieldLabel: '归属部门'
                        }
                        ,{
                            itemId: 'assetManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetManager'
                            ,fieldLabel: '资产负责人'
                        }
                        ,{
                            itemId: 'useDeptField'
                            ,padding: '5 0 0 5'
                            ,name: 'useDept'
                            ,fieldLabel: '使用部门'
                        }
                        ,{
                            itemId: 'useManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'useManager'
                            ,fieldLabel: '使用负责人'
                        }
                        ,{
                            itemId: 'opsDeptField'
                            ,padding: '5 0 0 5'
                            ,name: 'opsDept'
                            ,fieldLabel: '维护部门'
                        }
                        ,{
                            itemId: 'opsManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'opsManager'
                            ,fieldLabel: '维护负责人'
                        }
                        ,{
                            itemId: 'bizLineField'
                            ,padding: '5 0 0 5'
                            ,name: 'bizLine'
                            ,fieldLabel: '业务线'
                        }
                        ,{
                            itemId: 'bizManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'bizManager'
                            ,fieldLabel: '业务代表'
                        }
                        ,{
                            itemId: 'goliveDateField'
                            ,padding: '5 0 0 5'
                            ,name: 'goliveDate'
                            ,fieldLabel: '启用时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(value, 'Y-m-d')
                            }
                        }
                        ,{
                            itemId: 'majorCustField'
                            ,padding: '5 0 0 5'
                            ,name: 'majorCust'
                            ,fieldLabel: '主要客户'
                        }
                        ,{
                            itemId: 'custManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'custManager'
                            ,fieldLabel: '客户代表'
                        }
                        ,{
                            itemId: 'custUsageField'
                            ,padding: '5 0 0 5'
                            ,name: 'custUsage'
                            ,fieldLabel: '使用情况'
                        }
                        ,{
                            itemId: 'notesField'
                            ,padding: '5 0 0 5'
                            ,name: 'notes'
                            ,fieldLabel: '备注'
                        }
                        ,{
                            itemId: 'acquisitionProviderField'
                            ,padding: '5 0 0 5'
                            ,name: 'acquisitionProvider'
                            ,fieldLabel: '供应商'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});