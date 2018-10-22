Ext.define('AM.view.asset.asset.config.AssetTypeSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.config.AssetTypeSearchWindow'
    ,alias: 'widget.assetasset.configAssetTypeSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '资产分类高级查询'
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
                            ,itemId: 'numField'
                            ,fieldLabel: '编号'
                        }

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
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'useMonthField'
                            ,fieldLabel: '年限(月)'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'useMonthMaxField'
                            ,fieldLabel: '年限(月)'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'useMonthMinField'
                            ,fieldLabel: '年限(月)'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'viewIndexField'
                            ,fieldLabel: '展现顺序'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'parentCodeField'
                            ,fieldLabel: '上级代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '说明'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetCategoryCodeField'
                            ,fieldLabel: '所属大类'
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
        var numField = me.down("#numField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var useMonthField = me.down("#useMonthField");
        var useMonthMaxField = me.down("#useMonthMaxField");
        var useMonthMinField = me.down("#useMonthMinField");
        var viewIndexField = me.down("#viewIndexField");
        var parentCodeField = me.down("#parentCodeField");
        var descriptionField = me.down("#descriptionField");
        var assetCategoryCodeField = me.down("#assetCategoryCodeField");

        var condition = {
            num:Ext.isEmpty(numField.getValue())?null:numField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,useMonth:Ext.isNumber(useMonthField.getValue())?useMonthField.getValue():null
            ,useMonthMax:Ext.isNumber(useMonthMaxField.getValue())?useMonthMaxField.getValue():null
            ,useMonthMin:Ext.isNumber(useMonthMinField.getValue())?useMonthMinField.getValue():null
            ,viewIndex:Ext.isEmpty(viewIndexField.getValue())?null:viewIndexField.getValue()
            ,parentCode:Ext.isEmpty(parentCodeField.getValue())?null:parentCodeField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,assetCategoryCode:Ext.isEmpty(assetCategoryCodeField.getValue())?null:assetCategoryCodeField.getValue()
        };

        return condition;
    }

});