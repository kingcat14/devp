Ext.define('AM.view.maintenance.hardware.NetworkDeviceSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'maintenance.hardware.NetworkDeviceSearchWindow'
    ,alias: 'widget.maintenancehardwareNetworkDeviceSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '网络设备高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var networkDeviceStatusStore = Ext.create("AM.store.application.common.SimpleConfigStore")
        networkDeviceStatusStore.proxy.isSynchronous = true;
        networkDeviceStatusStore.proxy.extraParams={searchCondition:{configType:'NETWORKDEVICE-STATUS'}};
        networkDeviceStatusStore.load();

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'aliasField'
                            ,fieldLabel: '别名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '描述'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeCodeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'hardwareModelField'
                            ,fieldLabel: '硬件型号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'softwareModelField'
                            ,fieldLabel: '软件型号'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: networkDeviceStatusStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'createDateField'
                            ,fieldLabel: '创建时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'createDateStartField'
                            ,fieldLabel: '起始创建时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'createDateEndField'
                            ,fieldLabel: '结束创建时间'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'expireDateField'
                            ,fieldLabel: '到期时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'expireDateStartField'
                            ,fieldLabel: '起始到期时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'expireDateEndField'
                            ,fieldLabel: '结束到期时间'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetProjectField'
                            ,fieldLabel: '所属项目'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetAreaField'
                            ,fieldLabel: '所属区域'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetLocationField'
                            ,fieldLabel: '资产位置'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'intAccessAddrField'
                            ,fieldLabel: '内部访问地址'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'extAccessAddrField'
                            ,fieldLabel: '外部访问地址'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionModeField'
                            ,fieldLabel: '获取方式'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionDescField'
                            ,fieldLabel: '获取方式说明'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetDeptField'
                            ,fieldLabel: '归属部门'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetManagerField'
                            ,fieldLabel: '资产负责人'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'useDeptField'
                            ,fieldLabel: '使用部门'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'useManagerField'
                            ,fieldLabel: '使用负责人'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'opsDeptField'
                            ,fieldLabel: '维护部门'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'opsManagerField'
                            ,fieldLabel: '维护负责人'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'bizLineField'
                            ,fieldLabel: '业务线'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'bizManagerField'
                            ,fieldLabel: '业务代表'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'goliveDateField'
                            ,fieldLabel: '启用时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'goliveDateStartField'
                            ,fieldLabel: '起始启用时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'goliveDateEndField'
                            ,fieldLabel: '结束启用时间'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'majorCustField'
                            ,fieldLabel: '主要客户'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'custManagerField'
                            ,fieldLabel: '客户代表'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'custUsageField'
                            ,fieldLabel: '使用情况'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'notesField'
                            ,fieldLabel: '备注'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidField'
                            ,fieldLabel: '关联产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMaxField'
                            ,fieldLabel: '关联产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMinField'
                            ,fieldLabel: '关联产品编号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'parasCodeField'
                            ,fieldLabel: '参数定义标识'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionProviderField'
                            ,fieldLabel: '供应商'
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var typeCodeField = me.down("#typeCodeField");
        var hardwareModelField = me.down("#hardwareModelField");
        var softwareModelField = me.down("#softwareModelField");
        var statusField = me.down("#statusField");
        var createDateStartField = me.down("#createDateStartField");
        var createDateEndField = me.down("#createDateEndField");
        var createDateField = me.down("#createDateField");
        var expireDateStartField = me.down("#expireDateStartField");
        var expireDateEndField = me.down("#expireDateEndField");
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
        var goliveDateStartField = me.down("#goliveDateStartField");
        var goliveDateEndField = me.down("#goliveDateEndField");
        var goliveDateField = me.down("#goliveDateField");
        var majorCustField = me.down("#majorCustField");
        var custManagerField = me.down("#custManagerField");
        var custUsageField = me.down("#custUsageField");
        var notesField = me.down("#notesField");
        var prdRidField = me.down("#prdRidField");
        var prdRidMaxField = me.down("#prdRidMaxField");
        var prdRidMinField = me.down("#prdRidMinField");
        var parasCodeField = me.down("#parasCodeField");
        var acquisitionProviderField = me.down("#acquisitionProviderField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,hardwareModel:Ext.isEmpty(hardwareModelField.getValue())?null:hardwareModelField.getValue()
            ,softwareModel:Ext.isEmpty(softwareModelField.getValue())?null:softwareModelField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,createDate:Ext.isEmpty(createDateField.getValue())?null:Ext.Date.format(createDateField.getValue(),'Y-m-d')
            ,createDateStart:Ext.isEmpty(createDateStartField.getValue())?null:Ext.Date.format(createDateStartField.getValue(),'Y-m-d')
            ,createDateEnd:Ext.isEmpty(createDateEndField.getValue())?null:Ext.Date.format(createDateEndField.getValue(),'Y-m-d')
            ,expireDate:Ext.isEmpty(expireDateField.getValue())?null:Ext.Date.format(expireDateField.getValue(),'Y-m-d')
            ,expireDateStart:Ext.isEmpty(expireDateStartField.getValue())?null:Ext.Date.format(expireDateStartField.getValue(),'Y-m-d')
            ,expireDateEnd:Ext.isEmpty(expireDateEndField.getValue())?null:Ext.Date.format(expireDateEndField.getValue(),'Y-m-d')
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
            ,goliveDateStart:Ext.isEmpty(goliveDateStartField.getValue())?null:Ext.Date.format(goliveDateStartField.getValue(),'Y-m-d')
            ,goliveDateEnd:Ext.isEmpty(goliveDateEndField.getValue())?null:Ext.Date.format(goliveDateEndField.getValue(),'Y-m-d')
            ,majorCust:Ext.isEmpty(majorCustField.getValue())?null:majorCustField.getValue()
            ,custManager:Ext.isEmpty(custManagerField.getValue())?null:custManagerField.getValue()
            ,custUsage:Ext.isEmpty(custUsageField.getValue())?null:custUsageField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,prdRidMax:Ext.isNumber(prdRidMaxField.getValue())?prdRidMaxField.getValue():null
            ,prdRidMin:Ext.isNumber(prdRidMinField.getValue())?prdRidMinField.getValue():null
            ,parasCode:Ext.isEmpty(parasCodeField.getValue())?null:parasCodeField.getValue()
            ,acquisitionProvider:Ext.isEmpty(acquisitionProviderField.getValue())?null:acquisitionProviderField.getValue()
        };

        return condition;
    }

});