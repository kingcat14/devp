Ext.define('AM.view.asset.asset.info.AssetOwnershipSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.info.AssetOwnershipSearchWindow'
    ,alias: 'widget.assetasset.infoAssetOwnershipSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: 'IT资产归属高级查询'
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
                            ,fieldLabel: '资产名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '资产代码'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.asset.asset.config.AssetCategoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'categoryField'
                            ,fieldLabel: '资产大类'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.asset.asset.config.AssetTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'typeField'
                            ,fieldLabel: '资产分类'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeCodeField'
                            ,fieldLabel: '资产分类代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeNameField'
                            ,fieldLabel: '资产分类名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetDeptField'
                            ,fieldLabel: '资产部门'
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
                            ,fieldLabel: '操作部门'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'opsManagerField'
                            ,fieldLabel: '操作负责人'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'bizLineField'
                            ,fieldLabel: '业务线'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'bizManagerField'
                            ,fieldLabel: '业务负责人'
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
                            ,fieldLabel: '客户负责人'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'custUsageField'
                            ,fieldLabel: '客户使用情况'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionProviderField'
                            ,fieldLabel: '供应商'
                        }

                            ]
                }
            ]
            ,dockedItems: [
                {
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button'
                            ,iconCls: 'page_white'
                            ,text: '重置'
                            ,listeners: {
                                click: {fn: me.onRestButtonClick,scope: me}
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {fn: me.onSearchButtonClick,scope: me}
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
        var categoryField = me.down("#categoryField");
        var typeField = me.down("#typeField");
        var typeCodeField = me.down("#typeCodeField");
        var typeNameField = me.down("#typeNameField");
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
        var acquisitionProviderField = me.down("#acquisitionProviderField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,category:Ext.isEmpty(categoryField.getValue())?null:categoryField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,typeName:Ext.isEmpty(typeNameField.getValue())?null:typeNameField.getValue()
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
            ,acquisitionProvider:Ext.isEmpty(acquisitionProviderField.getValue())?null:acquisitionProviderField.getValue()
        };

        return condition;
    }

});