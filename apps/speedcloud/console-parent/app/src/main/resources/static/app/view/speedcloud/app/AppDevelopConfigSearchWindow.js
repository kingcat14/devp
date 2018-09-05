Ext.define('AM.view.speedcloud.app.AppDevelopConfigSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.AppDevelopConfigSearchWindow'
    ,alias: 'widget.speedcloudappAppDevelopConfigSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '应用开发配置高级查询'
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
                            ,store: Ext.create("AM.store.speedcloud.app.AppBaseInfoStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'appField'
                            ,fieldLabel: '应用'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.app.CodeRepositoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'url'
                            ,valueField:'id'
                            ,itemId: 'codeField'
                            ,fieldLabel: '代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'testDatabaseField'
                            ,fieldLabel: '测试环境DB'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'testDomainNameField'
                            ,fieldLabel: '测试环境域名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'productionDatabaseField'
                            ,fieldLabel: '生产环境DB'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'productionDomainNameField'
                            ,fieldLabel: '生产环境域名'
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
        var appField = me.down("#appField");
        var appMaxField = me.down("#appMaxField");
        var appMinField = me.down("#appMinField");
        var codeField = me.down("#codeField");
        var codeMaxField = me.down("#codeMaxField");
        var codeMinField = me.down("#codeMinField");
        var testDatabaseField = me.down("#testDatabaseField");
        var testDomainNameField = me.down("#testDomainNameField");
        var productionDatabaseField = me.down("#productionDatabaseField");
        var productionDomainNameField = me.down("#productionDomainNameField");

        var condition = {
            app:Ext.isEmpty(appField.getValue())?null:appField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,testDatabase:Ext.isEmpty(testDatabaseField.getValue())?null:testDatabaseField.getValue()
            ,testDomainName:Ext.isEmpty(testDomainNameField.getValue())?null:testDomainNameField.getValue()
            ,productionDatabase:Ext.isEmpty(productionDatabaseField.getValue())?null:productionDatabaseField.getValue()
            ,productionDomainName:Ext.isEmpty(productionDomainNameField.getValue())?null:productionDomainNameField.getValue()
        };

        return condition;
    }

});