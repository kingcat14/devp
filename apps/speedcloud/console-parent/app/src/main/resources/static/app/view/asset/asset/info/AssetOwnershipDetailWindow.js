Ext.define('AM.view.asset.asset.info.AssetOwnershipDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.info.AssetOwnershipDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: 'IT资产归属详细信息'
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
                            itemId: 'tidField'
                            ,padding: '5 0 0 5'
                            ,name: 'tid'
                            ,fieldLabel: '租户编号'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '资产名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '资产代码'
                        }
                        ,{
                            itemId: 'categoryField'
                            ,padding: '5 0 0 5'
                            ,name: 'category'
                            ,fieldLabel: '资产大类'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('categoryVO')?record.get('categoryVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '资产分类'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('typeVO')?record.get('typeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'typeCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'typeCode'
                            ,fieldLabel: '资产分类代码'
                        }
                        ,{
                            itemId: 'assetDeptField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetDept'
                            ,fieldLabel: '资产部门'
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
                            ,fieldLabel: '操作部门'
                        }
                        ,{
                            itemId: 'opsManagerField'
                            ,padding: '5 0 0 5'
                            ,name: 'opsManager'
                            ,fieldLabel: '操作负责人'
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
                            ,fieldLabel: '业务负责人'
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
                            ,fieldLabel: '客户负责人'
                        }
                        ,{
                            itemId: 'custUsageField'
                            ,padding: '5 0 0 5'
                            ,name: 'custUsage'
                            ,fieldLabel: '客户使用情况'
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