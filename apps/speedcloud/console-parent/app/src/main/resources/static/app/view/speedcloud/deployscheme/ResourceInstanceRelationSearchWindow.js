Ext.define('AM.view.speedcloud.deployscheme.ResourceInstanceRelationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deployscheme.ResourceInstanceRelationSearchWindow'
    ,alias: 'widget.speedclouddeployschemeResourceInstanceRelationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '方案资源关联实例高级查询'
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
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: [
                                [true,'是']
                                ,[false,'否']
                            ]
                            ,value:true
                            ,typeAhead:false
                            ,editable:false
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'notesField'
                            ,fieldLabel: '备注'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqField'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqMaxField'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqMinField'
                            ,fieldLabel: '顺序号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidField'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMaxField'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMinField'
                            ,fieldLabel: '产品编号'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.deployScheme.SchemeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'schemeField'
                            ,fieldLabel: '部署方案编号'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.deployScheme.ResourceStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'resourceField'
                            ,fieldLabel: '关联资源编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'assetField'
                            ,fieldLabel: '关联实例编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'assetMaxField'
                            ,fieldLabel: '关联实例编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'assetMinField'
                            ,fieldLabel: '关联实例编号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetCategoryCodeField'
                            ,fieldLabel: '关联实例类别代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'assetTypeCodeField'
                            ,fieldLabel: '关联实例类型代码'
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
        var typeField = me.down("#typeField");
        var statusField = me.down("#statusField");
        var notesField = me.down("#notesField");
        var seqField = me.down("#seqField");
        var seqMaxField = me.down("#seqMaxField");
        var seqMinField = me.down("#seqMinField");
        var prdRidField = me.down("#prdRidField");
        var prdRidMaxField = me.down("#prdRidMaxField");
        var prdRidMinField = me.down("#prdRidMinField");
        var schemeField = me.down("#schemeField");
        var resourceField = me.down("#resourceField");
        var assetField = me.down("#assetField");
        var assetMaxField = me.down("#assetMaxField");
        var assetMinField = me.down("#assetMinField");
        var assetCategoryCodeField = me.down("#assetCategoryCodeField");
        var assetTypeCodeField = me.down("#assetTypeCodeField");

        var condition = {
            type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,seqMax:Ext.isNumber(seqMaxField.getValue())?seqMaxField.getValue():null
            ,seqMin:Ext.isNumber(seqMinField.getValue())?seqMinField.getValue():null
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,prdRidMax:Ext.isNumber(prdRidMaxField.getValue())?prdRidMaxField.getValue():null
            ,prdRidMin:Ext.isNumber(prdRidMinField.getValue())?prdRidMinField.getValue():null
            ,scheme:Ext.isEmpty(schemeField.getValue())?null:schemeField.getValue()
            ,resource:Ext.isEmpty(resourceField.getValue())?null:resourceField.getValue()
            ,asset:Ext.isNumber(assetField.getValue())?assetField.getValue():null
            ,assetMax:Ext.isNumber(assetMaxField.getValue())?assetMaxField.getValue():null
            ,assetMin:Ext.isNumber(assetMinField.getValue())?assetMinField.getValue():null
            ,assetCategoryCode:Ext.isEmpty(assetCategoryCodeField.getValue())?null:assetCategoryCodeField.getValue()
            ,assetTypeCode:Ext.isEmpty(assetTypeCodeField.getValue())?null:assetTypeCodeField.getValue()
        };

        return condition;
    }

});