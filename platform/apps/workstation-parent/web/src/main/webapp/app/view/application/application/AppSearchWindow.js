Ext.define('AM.view.application.application.AppSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.applicationapplicationAppSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '应用高级查询',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
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
                                            xtype: 'textfield',
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '应用名称'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'tenantIdField',
                                            name: 'tenantId',
                                            fieldLabel: '所属租户'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.application.application.AppCategoryStore"),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            itemId: 'appCategoryIdField',
                                            name: 'appCategoryId',
                                            fieldLabel: '应用类别'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'enableField',
                                            name: 'enable',
                                            fieldLabel: '已启用'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            itemId: 'urlField',
                                            name: 'url',
                                            fieldLabel: '应用链接'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'visibleField',
                                            name: 'visible',
                                            fieldLabel: '可见'
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
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'codeField',
                                            padding: '5 0 0 5',
                                            name: 'code',
                                            fieldLabel: '应用代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'appCategoryCodeField',
                                            padding: '5 0 0 5',
                                            name: 'appCategoryCode',
                                            fieldLabel: '应用类别类别代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'labelField',
                                            padding: '5 0 0 5',
                                            name: 'label',
                                            fieldLabel: '标签',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'datefield',
                                            format: 'Y-m-d',
                                            anchor: '96%',
                                            itemId: 'onBoardTimeField',
                                            padding: '5 0 0 5',
                                            name: 'onBoardTime',
                                            fieldLabel: '上架时间',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'descriptionField',
                                            padding: '5 0 0 5',
                                            name: 'description',
                                            fieldLabel: '描述',
                                            labelAlign: 'top'
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

        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var tenantIdField = me.down("#tenantIdField");
        var appCategoryCodeField = me.down("#appCategoryCodeField");
        var appCategoryIdField = me.down("#appCategoryIdField");
        var labelField = me.down("#labelField");
        var enableField = me.down("#enableField");
        var onBoardTimeField = me.down("#onBoardTimeField");
        var urlField = me.down("#urlField");
        var descriptionField = me.down("#descriptionField");
        var visibleField = me.down("#visibleField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,tenantId:Ext.isNumber(tenantIdField.getValue())?tenantIdField.getValue():null
            ,appCategoryCode:Ext.isEmpty(appCategoryCodeField.getValue())?null:appCategoryCodeField.getValue()
            ,appCategoryId:Ext.isNumber(appCategoryIdField.getValue())?appCategoryIdField.getValue():null
            ,label:Ext.isEmpty(labelField.getValue())?null:labelField.getValue()
            ,enable:Ext.isEmpty(enableField.getValue())?null:enableField.getValue()
            ,onBoardTime:Ext.isEmpty(onBoardTimeField.getValue())?null:Ext.Date.format(onBoardTimeField.getValue(),'Y-m-d H:i:s')
            ,url:Ext.isEmpty(urlField.getValue())?null:urlField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,visible:Ext.isEmpty(visibleField.getValue())?null:visibleField.getValue()
        };

        this.store.proxy.extraParams={searchCondition:condition};
        this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }
    ,onRestButtonClick: function (button, e, options) {

        this.down('form').getForm().reset();

        this.store.proxy.extraParams={searchCondition:{}};
            this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }

    ,setStore: function (store) {
        this.store = store;
    }

});