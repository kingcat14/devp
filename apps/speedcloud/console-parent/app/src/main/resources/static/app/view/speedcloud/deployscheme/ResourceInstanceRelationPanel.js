Ext.define('AM.view.speedcloud.deployscheme.ResourceInstanceRelationPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.ResourceInstanceRelationPanel'
    , title: '方案资源关联实例'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deployscheme.ResourceInstanceRelationController'
        ,'AM.store.speedcloud.deployscheme.ResourceInstanceRelationStore'
        ,'AM.view.speedcloud.deployscheme.ResourceInstanceRelationAddWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceInstanceRelationEditWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceInstanceRelationSearchWindow'
        ,'AM.view.speedcloud.deployscheme.ResourceInstanceRelationDetailWindow'
    ]
    ,controller: 'speedcloud.deployScheme.ResourceInstanceRelationController'
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.deployscheme.ResourceInstanceRelationStore').load()
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
                            ,dataIndex: 'type'
                            ,text: '类型'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'status'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'notes'
                            ,text: '备注'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'seq'
                            ,format:'0,000'
                            ,text: '顺序号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'prdRid'
                            ,text: '产品编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'scheme'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("schemeVO")?record.get("schemeVO").name:'';
                            }
                            ,text: '部署方案编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'resource'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("resourceVO")?record.get("resourceVO").name:'';
                            }
                            ,text: '关联资源编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'asset'
                            ,text: '关联实例编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetCategoryCode'
                            ,text: '关联实例类别代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetTypeCode'
                            ,text: '关联实例类型代码'
                            ,flex:1
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-pencil-alt'
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
                                iconCls: 'fas fa-minus-circle red'
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

        me.add({xtype:'speedcloud.deployscheme.ResourceInstanceRelationAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceInstanceRelationEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceInstanceRelationSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.deployscheme.ResourceInstanceRelationDetailWindow',reference:'mainDetailWindow'})

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