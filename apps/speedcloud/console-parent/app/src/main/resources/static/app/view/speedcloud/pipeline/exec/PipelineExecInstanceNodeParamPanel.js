Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamPanel'
    , title: '运行实例节点参数'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamController'
        ,'AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamStore'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamAddWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamEditWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamSearchWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamDetailWindow'
    ]
    ,controller: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamStore').load()
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
                            ,dataIndex: 'node'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("nodeVO")?record.get("nodeVO").name:'';
                            }
                            ,text: '所属节点'
                            
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
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'viewOrder'
                            ,format:'0,000'
                            ,text: '排序'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '类型'
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

        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamDetailWindow',reference:'mainDetailWindow'})

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