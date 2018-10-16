Ext.define('AM.view.speedcloud.deployscheme.SchemeTreePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.SchemeTreePanel'
    , title: '部署方案'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deployscheme.SchemeController'
        ,'AM.store.speedcloud.deployscheme.SchemeStore'
        ,'AM.view.speedcloud.deployscheme.SchemeAddWindow'
        ,'AM.view.speedcloud.deployscheme.SchemeEditWindow'
        ,'AM.view.speedcloud.deployscheme.SchemeSearchWindow'
        ,'AM.view.speedcloud.deployscheme.SchemeDetailWindow'
    ]
    ,controller: 'speedcloud.deployScheme.SchemeController'
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.deployscheme.SchemeStore').load()
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
                            ,dataIndex: 'name'
                            ,text: '方案名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '方案代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '方案别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '方案类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'version'
                            ,text: '版本'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'verPostfix'
                            ,text: '版本标识后缀'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'status'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '已生效'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'project'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("projectVO")?record.get("projectVO").name:'';
                            }
                            ,text: '所属项目（产品）'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'env'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("envVO")?record.get("envVO").name:'';
                            }
                            ,text: '所属环境'
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
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'方案名称'
                                    ,reference: 'nameField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'方案代码'
                                    ,reference: 'codeField'
                                }
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

        me.add({xtype:'speedcloud.deployscheme.SchemeAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.SchemeEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.SchemeSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.deployscheme.SchemeDetailWindow',reference:'mainDetailWindow'})

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