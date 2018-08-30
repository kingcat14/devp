Ext.define('AM.view.icode4.database.DatabaseProjectSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode4.database.DatabaseProjectSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '数据库项目高级查询'
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
                    ,items: [
                        {
                            xtype: 'container'
                            ,layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
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
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '项目名称'
                                        }
                                        ,{
                                            itemId: 'usernameField',
                                            name: 'username',
                                            fieldLabel: '用户名'
                                        }
                                        ,{
                                            itemId: 'driverNameField',
                                            name: 'driverName',
                                            fieldLabel: '驱动名词'
                                        }

                                    ]
                                }
                                ,{
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
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
                                            itemId: 'urlField',
                                            padding: '5 0 0 5',
                                            name: 'url',
                                            fieldLabel: '数据库链接',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'passwordField',
                                            padding: '5 0 0 5',
                                            name: 'password',
                                            fieldLabel: '密码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'editableField',
                                            padding: '5 0 0 5',
                                            name: 'editable',
                                            fieldLabel: '可修改',
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
                                click: {
                                    fn: me.onRestButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {
                                    fn: me.onSearchButtonClick
                                    ,scope: me
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
        var urlField = me.down("#urlField");
        var usernameField = me.down("#usernameField");
        var passwordField = me.down("#passwordField");
        var driverNameField = me.down("#driverNameField");
        var editableField = me.down("#editableField");

        var searchCondition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,url:Ext.isEmpty(urlField.getValue())?null:urlField.getValue()
            ,username:Ext.isEmpty(usernameField.getValue())?null:usernameField.getValue()
            ,password:Ext.isEmpty(passwordField.getValue())?null:passwordField.getValue()
            ,driverName:Ext.isEmpty(driverNameField.getValue())?null:driverNameField.getValue()
            ,editable:Ext.isEmpty(editableField.getValue())?null:editableField.getValue()
        };

        this.store.proxy.extraParams = {searchCondition:searchCondition};
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
                start:0
                ,page:0
            }
        });
        this.hide();
    }
    ,setStore: function (store) {
        this.store = store;
    }

});