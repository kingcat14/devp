Ext.define('AM.view.speedcloud.deployscheme.ResourceTypeSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deployscheme.ResourceTypeSearchWindow'
    ,alias: 'widget.speedclouddeployschemeResourceTypeSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '部署资源类型高级查询'
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
                            ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceCategoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'categoryField'
                            ,fieldLabel: '资源类别'
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
                            xtype: 'textfield'
                            ,itemId: 'iconField'
                            ,fieldLabel: '图标'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'idxField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'idxMaxField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'idxMinField'
                            ,fieldLabel: '排序'
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
        var categoryField = me.down("#categoryField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var iconField = me.down("#iconField");
        var idxField = me.down("#idxField");
        var idxMaxField = me.down("#idxMaxField");
        var idxMinField = me.down("#idxMinField");

        var condition = {
            category:Ext.valueFrom(categoryField.getValue(), null)
            ,name:Ext.valueFrom(nameField.getValue(), null)
            ,code:Ext.valueFrom(codeField.getValue(), null)
            ,icon:Ext.valueFrom(iconField.getValue(), null)
            ,idx:Ext.isNumber(idxField.getValue())?idxField.getValue():null
            ,idxMax:Ext.isNumber(idxMaxField.getValue())?idxMaxField.getValue():null
            ,idxMin:Ext.isNumber(idxMinField.getValue())?idxMinField.getValue():null
        };

        return condition;
    }

});