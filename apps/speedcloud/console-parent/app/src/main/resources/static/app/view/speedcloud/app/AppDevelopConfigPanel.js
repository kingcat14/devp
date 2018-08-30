Ext.define('AM.view.speedcloud.app.AppDevelopConfigPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.app.AppDevelopConfigPanel'
    , title: '应用开发配置'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.app.AppDevelopConfigController'
        ,'AM.store.speedcloud.app.AppDevelopConfigStore'
        ,'AM.view.speedcloud.app.AppDevelopConfigAddWindow'
        ,'AM.view.speedcloud.app.AppDevelopConfigEditWindow'
        ,'AM.view.speedcloud.app.AppDevelopConfigSearchWindow'
        ,'AM.view.speedcloud.app.AppDevelopConfigDetailWindow'
    ]
    ,controller: 'speedcloud.app.AppDevelopConfigController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.app.AppDevelopConfigStore').load()
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:35
                            ,items: [{
                                iconCls: 'x-fa fa-eye'
                                ,tooltip: '详情'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.showDetailWindow(record, this);
                                }
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'app'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("appVO")?record.get("appVO").name:'';
                            }
                            ,text: '应用'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("codeVO")?record.get("codeVO").url:'';
                            }
                            ,text: '代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'testDatabase'
                            ,text: '测试环境DB'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'testDomainName'
                            ,text: '测试环境域名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'productionDatabase'
                            ,text: '生产环境DB'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'productionDomainName'
                            ,text: '生产环境域名'
                            ,flex:1
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'edit'
                                ,tooltip: '修改'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onEditButtonClick();
                                }
                            }]
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'delete'
                                ,tooltip: '删除'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onDeleteButtonClick();
                                }
                            }]
                        }
                    ]
                    ,viewConfig: {

                    }
                    ,dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button'
                                    ,iconCls: 'add'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: 'onAddButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'edit'
                                    ,text: '修改'
                                    ,listeners: {
                                        click: 'onEditButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'delete'
                                    ,text: '删除'
                                    ,listeners: {
                                        click: 'onDeleteButtonClick'
                                    }
                                }
                                ,'-'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '导出'
                                    ,listeners: {
                                        click: 'onExportButtonClick'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'pagingtoolbar'
                            ,dock: 'bottom'
                            ,displayInfo: true
                        }
                    ]
                    ,selModel: 'checkboxmodel'
                    ,listeners: {
                        beforeshow: {
                            fn: me.onBeforeShow
                            ,scope: me
                        }
                        ,beforehide: {
                            fn: me.onPanelBeforeHide
                            ,scope: me
                        }
                    }
                }
            ]
        });

        me.add({xtype:'speedcloud.app.AppDevelopConfigAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.app.AppDevelopConfigEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.app.AppDevelopConfigSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.app.AppDevelopConfigDetailWindow',reference:'mainDetailWindow'})

        me.callParent(arguments);
    }

    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('mainDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});