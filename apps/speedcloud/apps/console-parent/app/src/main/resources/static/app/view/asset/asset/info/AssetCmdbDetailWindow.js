Ext.define('AM.view.asset.asset.info.AssetCmdbDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.info.AssetCmdbDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '资产详细信息'
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
                            itemId: 'barcodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'barcode'
                            ,fieldLabel: '资产条码'
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
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '资产别名'
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
                            itemId: 'unitField'
                            ,padding: '5 0 0 5'
                            ,name: 'unit'
                            ,fieldLabel: '计量单位'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
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
                            itemId: 'assetAreaField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetArea'
                            ,fieldLabel: '所在区域'
                        }
                        ,{
                            itemId: 'assetLocationField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetLocation'
                            ,fieldLabel: '所在地址'
                        }
                        ,{
                            itemId: 'acquisitionModeField'
                            ,padding: '5 0 0 5'
                            ,name: 'acquisitionMode'
                            ,fieldLabel: '获取模式'
                        }
                        ,{
                            itemId: 'acquisitionDescField'
                            ,padding: '5 0 0 5'
                            ,name: 'acquisitionDesc'
                            ,fieldLabel: '获取描述'
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
                            itemId: 'notesField'
                            ,padding: '5 0 0 5'
                            ,name: 'notes'
                            ,fieldLabel: 'notes'
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