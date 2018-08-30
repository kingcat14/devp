Ext.define('AM.view.icode4.database.DatabaseTableInfoSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode4.database.DatabaseTableInfoSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '数据库表信息高级查询'
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
                                            itemId: 'connectionIdField',
                                            name: 'connectionId',
                                            fieldLabel: '链接ID'
                                        }
                                        ,{
                                            itemId: 'moduleNameField',
                                            name: 'moduleName',
                                            fieldLabel: '模块名称'
                                        }
                                        ,{
                                            itemId: 'tableDisplayNameField',
                                            name: 'tableDisplayName',
                                            fieldLabel: '表展现名称'
                                        }
                                        ,{
                                            itemId: 'tableDescField',
                                            name: 'tableDesc',
                                            fieldLabel: '表描述'
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
                                            itemId: 'connectionUrlField',
                                            padding: '5 0 0 5',
                                            name: 'connectionUrl',
                                            fieldLabel: '链接url',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'tableNameField',
                                            padding: '5 0 0 5',
                                            name: 'tableName',
                                            fieldLabel: '表名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'entityNameField',
                                            padding: '5 0 0 5',
                                            name: 'entityName',
                                            fieldLabel: '实体名称',
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

        var connectionIdField = me.down("#connectionIdField");
        var connectionUrlField = me.down("#connectionUrlField");
        var moduleNameField = me.down("#moduleNameField");
        var tableNameField = me.down("#tableNameField");
        var tableDisplayNameField = me.down("#tableDisplayNameField");
        var entityNameField = me.down("#entityNameField");
        var tableDescField = me.down("#tableDescField");

        var searchCondition = {
            connectionId:Ext.isEmpty(connectionIdField.getValue())?null:connectionIdField.getValue()
            ,connectionUrl:Ext.isEmpty(connectionUrlField.getValue())?null:connectionUrlField.getValue()
            ,moduleName:Ext.isEmpty(moduleNameField.getValue())?null:moduleNameField.getValue()
            ,tableName:Ext.isEmpty(tableNameField.getValue())?null:tableNameField.getValue()
            ,tableDisplayName:Ext.isEmpty(tableDisplayNameField.getValue())?null:tableDisplayNameField.getValue()
            ,entityName:Ext.isEmpty(entityNameField.getValue())?null:entityNameField.getValue()
            ,tableDesc:Ext.isEmpty(tableDescField.getValue())?null:tableDescField.getValue()
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