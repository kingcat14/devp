Ext.define('AM.view.icode.domain.PropertyTypeSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.domain.PropertyTypeSearchWindow'
    ,alias: 'widget.icodedomainPropertyTypeSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '属性类型高级查询'
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
                            ,itemId: 'codeField'
                            ,fieldLabel: '代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '名称'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewIndexField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewIndexMaxField'
                            ,fieldLabel: '排序'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'viewIndexMinField'
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
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var viewIndexField = me.down("#viewIndexField");
        var viewIndexMaxField = me.down("#viewIndexMaxField");
        var viewIndexMinField = me.down("#viewIndexMinField");

        var condition = {
            code:Ext.valueFrom(codeField.getValue(), null)
            ,name:Ext.valueFrom(nameField.getValue(), null)
            ,viewIndex:Ext.isNumber(viewIndexField.getValue())?viewIndexField.getValue():null
            ,viewIndexMax:Ext.isNumber(viewIndexMaxField.getValue())?viewIndexMaxField.getValue():null
            ,viewIndexMin:Ext.isNumber(viewIndexMinField.getValue())?viewIndexMinField.getValue():null
        };

        return condition;
    }

});