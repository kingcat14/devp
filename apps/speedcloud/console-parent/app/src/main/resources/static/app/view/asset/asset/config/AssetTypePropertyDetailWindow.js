Ext.define('AM.view.asset.asset.config.AssetTypePropertyDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.config.AssetTypePropertyDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '资产分类属性详细信息'
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
                            itemId: 'assetTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetType'
                            ,fieldLabel: '资产分类'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('assetTypeVO')?record.get('assetTypeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '属性名称'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '属性类型'
                        }
                        ,{
                            itemId: 'requiredField'
                            ,padding: '5 0 0 5'
                            ,name: 'required'
                            ,fieldLabel: '必填'
                        }
                        ,{
                            itemId: 'optionValuesField'
                            ,padding: '5 0 0 5'
                            ,name: 'optionValues'
                            ,fieldLabel: '备选值'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序'
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