Ext.define('AM.view.speedcloud.pipeline.PipelineParamPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.PipelineParamPanel'
    , title: '流水线参数'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.PipelineParamController'
        ,'AM.store.speedcloud.pipeline.PipelineParamStore'
        ,'AM.view.speedcloud.pipeline.PipelineParamAddWindow'
        ,'AM.view.speedcloud.pipeline.PipelineParamEditWindow'
        ,'AM.view.speedcloud.pipeline.PipelineParamSearchWindow'
        ,'AM.view.speedcloud.pipeline.PipelineParamDetailWindow'
    ]
    ,controller: 'speedcloud.pipeline.PipelineParamController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.pipeline.PipelineParamStore').load()
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
                            ,dataIndex: 'pipeline'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("pipelineVO")?record.get("pipelineVO").name:'';
                            }
                            ,text: '所属流水线'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '参数名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '参数类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'defaultValue'
                            ,text: '默认值'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'viewOrder'
                            ,format:'0,000'
                            ,text: '展现顺序'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '参数描述'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'deletable'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '可删除'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'enumValue'
                            ,text: '可选值'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'value'
                            ,text: '参数值'
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

        me.add({xtype:'speedcloud.pipeline.PipelineParamAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.PipelineParamEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.PipelineParamSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.pipeline.PipelineParamDetailWindow',reference:'mainDetailWindow'})

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