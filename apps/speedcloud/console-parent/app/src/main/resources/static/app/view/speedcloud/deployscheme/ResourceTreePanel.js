Ext.define('AM.view.speedcloud.deployscheme.ResourceTreePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.ResourceTreePanel'
    , title: '方案资源'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deployscheme.ResourceController'
        ,'AM.store.speedcloud.deployscheme.ResourceStore'
        ,'AM.view.speedcloud.deployscheme.ResourceAddWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceEditWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceSearchWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceDetailWindow'
    ]
    ,controller: 'speedcloud.deployScheme.ResourceController'
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.deployscheme.ResourceStore').load()
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
                            ,text: '资源名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '资源代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '资源别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'category'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("categoryVO")?record.get("categoryVO").name:'';
                            }
                            ,text: '资源类别'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '资源类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'version'
                            ,text: '版本'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'seq'
                            ,format:'0,000'
                            ,text: '顺序号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'evn'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("evnVO")?record.get("evnVO").name:'';
                            }
                            ,text: '所属环境'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'project'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("projectVO")?record.get("projectVO").name:'';
                            }
                            ,text: '产品编号'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'outerResource'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '外部资源'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'scheme'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("schemeVO")?record.get("schemeVO").name:'';
                            }
                            ,text: '所属方案'
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
                                    xtype: 'combobox'
                                    ,emptyText:'所属方案'
                                    ,store: Ext.create("AM.store.speedcloud.deployScheme.SchemeStore")
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,reference: 'schemeField'
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

        me.add({xtype:'speedcloud.deployscheme.ResourceAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceDetailWindow',reference:'mainDetailWindow'})

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