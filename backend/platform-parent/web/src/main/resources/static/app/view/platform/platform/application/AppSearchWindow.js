Ext.define('AM.view.platform.platform.application.AppSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.application.AppSearchWindow'
    ,alias: 'widget.platformplatform.applicationAppSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '应用高级查询'
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
                                            ,itemId: 'nameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'name'
                                            ,fieldLabel: '应用名称'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.platform.platform.application.ConfigAppCategoryStore")
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,anchor: '96%'
                                            ,itemId: 'appCategoryIdField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'appCategoryId'
                                            ,fieldLabel: '应用类别'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'datefield'
                                            ,format: 'Y-m-d'
                                            ,anchor: '96%'
                                            ,itemId: 'onBoardTimeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'onBoardTime'
                                            ,fieldLabel: '上架时间'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '描述'
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
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'codeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'code'
                                            ,fieldLabel: '应用代码'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'labelField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'label'
                                            ,fieldLabel: '标签'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,anchor: '96%'
                                            ,itemId: 'urlField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'url'
                                            ,fieldLabel: '应用链接'
                                            ,labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: [
                                                [true,'是']
                                                ,[false,'否']
                                            ]

                                            ,anchor: '96%'
                                            ,itemId: 'visibleField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'visible'
                                            ,fieldLabel: '可见'
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var appCategoryIdField = me.down("#appCategoryIdField");
        var labelField = me.down("#labelField");
        var onBoardTimeField = me.down("#onBoardTimeField");
        var urlField = me.down("#urlField");
        var descriptionField = me.down("#descriptionField");
        var visibleField = me.down("#visibleField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,appCategoryId:Ext.isNumber(appCategoryIdField.getValue())?appCategoryIdField.getValue():null
            ,label:Ext.isEmpty(labelField.getValue())?null:labelField.getValue()
            ,onBoardTime:Ext.isEmpty(onBoardTimeField.getValue())?null:Ext.Date.format(onBoardTimeField.getValue(),'Y-m-d H:i:s')
            ,url:Ext.isEmpty(urlField.getValue())?null:urlField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,visible:Ext.isEmpty(visibleField.getValue())?null:visibleField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});