Ext.define('AM.view.asset.asset.info.AssetCmdbSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.info.AssetCmdbSearchWindow'
    ,alias: 'widget.assetasset.infoAssetCmdbSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: 'IT资产高级查询'
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
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.asset.asset.config.AssetCategoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'code'
                            ,itemId: 'categoryCodeField'
                            ,fieldLabel: '资产大类'
                            ,listeners:{
                                change:function( field, newValue, oldValue){

                                    me.down('#typeCodeField').getStore().applyCondition({assetCategoryCode:newValue}).load()
                                }
                            }
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.asset.asset.config.AssetTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'typeCodeField'
                            ,fieldLabel: '资产分类'
                        }
                        ,{
                            xtype: 'textfield'
                            ,itemId: 'barcodeField'
                            ,fieldLabel: '资产条码'
                        }

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
                            xtype: 'textfield'
                            ,itemId: 'aliasField'
                            ,fieldLabel: '资产别名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
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
                            ,itemId: 'assetAreaField'
                            ,fieldLabel: '所在区域'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetLocationField'
                            ,fieldLabel: '所在地址'
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
                            ,iconCls: 'fas fa-search'
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
        var barcodeField = me.down("#barcodeField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var categoryCodeField = me.down("#categoryCodeField");
        var typeCodeField = me.down("#typeCodeField");
        var statusField = me.down("#statusField");
        var expireDateStartField = me.down("#expireDateStartField");
        var expireDateEndField = me.down("#expireDateEndField");
        var expireDateField = me.down("#expireDateField");
        var assetAreaField = me.down("#assetAreaField");
        var assetLocationField = me.down("#assetLocationField");
        var goliveDateStartField = me.down("#goliveDateStartField");
        var goliveDateEndField = me.down("#goliveDateEndField");
        var goliveDateField = me.down("#goliveDateField");



        var condition = {
            barcode: Ext.valueFrom(barcodeField.getValue(), null)
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,categoryCode:Ext.isEmpty(categoryCodeField.getValue())?null:categoryCodeField.getValue()
            ,typeCode:Ext.isEmpty(typeCodeField.getValue())?null:typeCodeField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,expireDate:     Ext.valueFrom(Ext.Date.format(expireDateField.getValue(),'Y-m-d'), null)
            ,expireDateStart:Ext.isEmpty(expireDateStartField.getValue())?null:Ext.Date.format(expireDateStartField.getValue(),'Y-m-d')
            ,expireDateEnd:Ext.isEmpty(expireDateEndField.getValue())?null:Ext.Date.format(expireDateEndField.getValue(),'Y-m-d')
            ,assetArea:Ext.isEmpty(assetAreaField.getValue())?null:assetAreaField.getValue()
            ,assetLocation:Ext.isEmpty(assetLocationField.getValue())?null:assetLocationField.getValue()
            ,goliveDate:Ext.isEmpty(goliveDateField.getValue())?null:Ext.Date.format(goliveDateField.getValue(),'Y-m-d')
            ,goliveDateStart:Ext.isEmpty(goliveDateStartField.getValue())?null:Ext.Date.format(goliveDateStartField.getValue(),'Y-m-d')
            ,goliveDateEnd:Ext.isEmpty(goliveDateEndField.getValue())?null:Ext.Date.format(goliveDateEndField.getValue(),'Y-m-d')

        };

        return condition;
    }

});