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
                            ,itemId: 'unitField'
                            ,fieldLabel: '计量单位'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '描述'
                        }

                        ,{
                            xtype: 'textfield'
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
                            ,itemId: 'assetAreaField'
                            ,fieldLabel: '所在区域'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetLocationField'
                            ,fieldLabel: '所在地址'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionModeField'
                            ,fieldLabel: '获取模式'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'acquisitionDescField'
                            ,fieldLabel: '获取描述'
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
                            ,itemId: 'notesField'
                            ,fieldLabel: 'notes'
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
        var barcodeField = me.down("#barcodeField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var categoryField = me.down("#categoryField");
        var typeField = me.down("#typeField");
        var unitField = me.down("#unitField");
        var descriptionField = me.down("#descriptionField");
        var statusField = me.down("#statusField");
        var createDateStartField = me.down("#createDateStartField");
        var createDateEndField = me.down("#createDateEndField");
        var createDateField = me.down("#createDateField");
        var expireDateStartField = me.down("#expireDateStartField");
        var expireDateEndField = me.down("#expireDateEndField");
        var expireDateField = me.down("#expireDateField");
        var assetAreaField = me.down("#assetAreaField");
        var assetLocationField = me.down("#assetLocationField");
        var acquisitionModeField = me.down("#acquisitionModeField");
        var acquisitionDescField = me.down("#acquisitionDescField");
        var goliveDateStartField = me.down("#goliveDateStartField");
        var goliveDateEndField = me.down("#goliveDateEndField");
        var goliveDateField = me.down("#goliveDateField");
        var notesField = me.down("#notesField");

        var condition = {
            barcode:Ext.isEmpty(barcodeField.getValue())?null:barcodeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,category:Ext.isEmpty(categoryField.getValue())?null:categoryField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,unit:Ext.isEmpty(unitField.getValue())?null:unitField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,createDate:Ext.isEmpty(createDateField.getValue())?null:Ext.Date.format(createDateField.getValue(),'Y-m-d')
            ,createDateStart:Ext.isEmpty(createDateStartField.getValue())?null:Ext.Date.format(createDateStartField.getValue(),'Y-m-d')
            ,createDateEnd:Ext.isEmpty(createDateEndField.getValue())?null:Ext.Date.format(createDateEndField.getValue(),'Y-m-d')
            ,expireDate:Ext.isEmpty(expireDateField.getValue())?null:Ext.Date.format(expireDateField.getValue(),'Y-m-d')
            ,expireDateStart:Ext.isEmpty(expireDateStartField.getValue())?null:Ext.Date.format(expireDateStartField.getValue(),'Y-m-d')
            ,expireDateEnd:Ext.isEmpty(expireDateEndField.getValue())?null:Ext.Date.format(expireDateEndField.getValue(),'Y-m-d')
            ,assetArea:Ext.isEmpty(assetAreaField.getValue())?null:assetAreaField.getValue()
            ,assetLocation:Ext.isEmpty(assetLocationField.getValue())?null:assetLocationField.getValue()
            ,acquisitionMode:Ext.isEmpty(acquisitionModeField.getValue())?null:acquisitionModeField.getValue()
            ,acquisitionDesc:Ext.isEmpty(acquisitionDescField.getValue())?null:acquisitionDescField.getValue()
            ,goliveDate:Ext.isEmpty(goliveDateField.getValue())?null:Ext.Date.format(goliveDateField.getValue(),'Y-m-d')
            ,goliveDateStart:Ext.isEmpty(goliveDateStartField.getValue())?null:Ext.Date.format(goliveDateStartField.getValue(),'Y-m-d')
            ,goliveDateEnd:Ext.isEmpty(goliveDateEndField.getValue())?null:Ext.Date.format(goliveDateEndField.getValue(),'Y-m-d')
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
        };

        return condition;
    }

});