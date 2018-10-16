Ext.define('AM.view.speedcloud.deployscheme.ResourcePropertyTreePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.ResourcePropertyTreePanel'
    , title: '资源属性'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deployscheme.ResourcePropertyController'
        ,'AM.store.speedcloud.deployscheme.ResourcePropertyStore'
        ,'AM.view.speedcloud.deployscheme.ResourcePropertyAddWindow'
        ,'AM.view.speedcloud.deployscheme.ResourcePropertyEditWindow'
        ,'AM.view.speedcloud.deployscheme.ResourcePropertySearchWindow'
        ,'AM.view.speedcloud.deployscheme.ResourcePropertyDetailWindow'
    ]
    ,controller: 'speedcloud.deployScheme.ResourcePropertyController'
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.deployscheme.ResourcePropertyStore').load()
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
                            ,dataIndex: 'resource'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("resourceVO")?record.get("resourceVO").name:'';
                            }
                            ,text: '所属资源'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '属性名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '属性代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'value'
                            ,text: '属性值'
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

        me.add({xtype:'speedcloud.deployscheme.ResourcePropertyAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourcePropertyEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourcePropertySearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.deployscheme.ResourcePropertyDetailWindow',reference:'mainDetailWindow'})

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