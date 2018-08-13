Ext.define('AM.view.platform.platform.application.ApplicationPasswordSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.application.ApplicationPasswordSearchWindow'
    ,alias: 'widget.platformplatform.applicationApplicationPasswordSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '应用密码高级查询'
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
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.platform.platform.application.AppStore")
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,anchor: '96%'
                                            ,itemId: 'appIdField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'appId'
                                            ,fieldLabel: '应用Id'
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
                                            ,itemId: 'accessIdField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'accessId'
                                            ,fieldLabel: '访问ID'
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
        var appIdField = me.down("#appIdField");
        var accessIdField = me.down("#accessIdField");

        var condition = {
            appId:Ext.isNumber(appIdField.getValue())?appIdField.getValue():null
            ,accessId:Ext.isEmpty(accessIdField.getValue())?null:accessIdField.getValue()
        };

        return condition;
    }
    ,setStore: function (store) {
        this.store = store;
    }

});