Ext.define('AM.view.speedcloud.app.AppDevelopConfigPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.app.AppDevelopConfigPanel'
    , alias: 'widget.speedcloud.app.AppDevelopConfigPanel'
    , title: '应用开发配置'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
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
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.speedcloud.app.AppDevelopConfigStore').load()
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,bind:{store: '{store}'}
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
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
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
                            ,dataIndex: 'developDatabase'
                            ,text: '开发环境DB'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'developDomainName'
                            ,text: '开发环境域名'
                            
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
                                iconCls: 'fas fa-pencil-alt'
                                ,tooltip: '修改'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
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
                                iconCls: 'fas fa-minus-circle red'
                                ,tooltip: '删除'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
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
                                    ,iconCls: 'fas fa-plus-circle'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: 'onAddButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-pencil-alt'
                                    ,text: '修改'
                                    ,listeners: {
                                        click: 'onEditButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-minus-circle red'
                                    ,text: '删除'
                                    ,listeners: {
                                        click: 'onDeleteButtonClick'
                                    }
                                }
                                ,'-'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search-plus'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-download'
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
                    ,listeners: {}
                }
            ]
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