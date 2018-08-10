Ext.define('AM.view.platform.platform.tenant.TenantSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.tenant.TenantSearchWindow'
    ,alias: 'widget.platformplatform.tenantTenantSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '租户高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10,
                    items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [

                                        ,{
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'tenantCodeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tenantCode'
                                            ,fieldLabel: '租户代号'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'nameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'name'
                                            ,fieldLabel: '租户名称'
                                            ,labelAlign: 'top'
                                        }

                                    ]
                                },
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [
                                        ,{
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.platform.platform.tenant.TenantTypeStore")
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,anchor: '96%'
                                            ,itemId: 'tenantTypeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tenantType'
                                            ,fieldLabel: '租户类型'
                                            ,labelAlign: 'top'
                                        }
                                    ]
                                }
                            ]
                        }

                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
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
        var tenantCodeField = me.down("#tenantCodeField");
        var tenantTypeField = me.down("#tenantTypeField");
        var nameField = me.down("#nameField");

        var condition = {
            tenantCode:Ext.isEmpty(tenantCodeField.getValue())?null:tenantCodeField.getValue()
            ,tenantType:Ext.isNumber(tenantTypeField.getValue())?tenantTypeField.getValue():null
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});