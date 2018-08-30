Ext.define('AM.view.icode4.database.DatabaseTableInfoDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '数据库表信息详细信息'
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
                                    ,defaults: {
                                        labelAlign: 'right'
                                        ,xtype: 'displayfield'
                                        ,padding: '5 0 0 5'
                                        ,blankText:'该字段为必填项'
                                        ,anchor: '100%'
                                        ,labelWidth:150
                                    }
                                    ,items: [
                                        {
                                            xtype: 'hiddenfield'
                                            ,anchor: '100%'
                                            ,itemId: 'idField'
                                            ,name: 'id'
                                            ,fieldLabel: 'Label'
                                        }

                                        ,{
                                            itemId: 'connectionIdField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'connectionId'
                                            ,fieldLabel: '链接ID'
                                        }

                                        ,{
                                            itemId: 'moduleNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'moduleName'
                                            ,fieldLabel: '模块名称'
                                        }

                                        ,{
                                            itemId: 'tableDisplayNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tableDisplayName'
                                            ,fieldLabel: '表展现名称'
                                        }

                                        ,{
                                            itemId: 'tableDescField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tableDesc'
                                            ,fieldLabel: '表描述'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults: {
                                        labelAlign: 'right'
                                        ,xtype: 'displayfield'
                                        ,padding: '5 0 0 5'
                                        ,blankText:'该字段为必填项'
                                        ,anchor: '100%'
                                        ,labelWidth:150
                                    }
                                    ,items: [
                                        {
                                            xtype: 'hiddenfield'
                                            ,anchor: '100%'
                                            ,itemId: 'versionField'
                                            ,name: 'version'
                                            ,fieldLabel: 'Label'
                                        }

                                        ,{
                                            itemId: 'connectionUrlField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'connectionUrl'
                                            ,fieldLabel: '链接url'
                                        }

                                        ,{
                                            itemId: 'tableNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tableName'
                                            ,fieldLabel: '表名称'
                                        }

                                        ,{
                                            itemId: 'entityNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'entityName'
                                            ,fieldLabel: '实体名称'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});