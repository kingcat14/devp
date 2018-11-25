Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecNodePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.exec.PipelineExecNodePanel'
    , title: '运行实例节点'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecNodeController'
        ,'AM.store.speedcloud.pipeline.exec.PipelineExecNodeStore'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecNodeAddWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecNodeEditWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecNodeSearchWindow'
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecNodeDetailWindow'
    ]
    ,controller: 'speedcloud.pipeline.exec.PipelineExecNodeController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecNodeStore').load()
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
                            ,dataIndex: 'code'
                            ,text: '编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '节点名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'nodeType'
                            ,text: '节点类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'execMode'
                            ,text: '执行方式'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '运行状态'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'result'
                            ,text: '运行结果'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'exec'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("execVO")?record.get("execVO").code:'';
                            }
                            ,text: '所属实例'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'resultMessage'
                            ,text: '结果消息'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d H:i:s'
                            ,dataIndex: 'startTime'
                            ,text: '开始时间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'parentId'
                            ,text: '上级节点'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'task'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("taskVO")?record.get("taskVO").name:'';
                            }
                            ,text: '关联任务'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'autoStart'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '自动运行'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'execIndex'
                            ,format:'0,000'
                            ,text: '节点排序'
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
                                    ,iconCls: 'fas fa-search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search'
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

        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecNodeAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecNodeEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecNodeSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'speedcloud.pipeline.exec.PipelineExecNodeDetailWindow',reference:'mainDetailWindow'})

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