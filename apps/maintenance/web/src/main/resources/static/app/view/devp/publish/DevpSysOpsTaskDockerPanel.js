Ext.define('AM.view.devp.publish.DevpSysOpsTaskDockerPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'devp.publish.DevpSysOpsTaskDockerPanel'
    , title: '部署容器'
    , layout: 'border'
    , requires: [
        'AM.view.devp.publish.DevpSysOpsTaskDockerController'
        ,'AM.store.devp.publish.DevpSysOpsTaskDockerStore'
        ,'AM.view.devp.publish.DevpSysOpsTaskDockerAddWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskDockerEditWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskDockerSearchWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskDockerDetailWindow'
    ]
    ,controller: 'devp.publish.DevpSysOpsTaskDockerController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.devp.publish.DevpSysOpsTaskDockerStore').load()
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
                            ,dataIndex: 'tid'
                            ,text: '租户编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'etype'
                            ,text: '元素类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '系统元素名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '系统元素代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '系统元素别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '系统元素描述'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'recordState'
                            ,format:'0,000'
                            ,text: '记录状态'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'subType'
                            ,text: '子类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'group'
                            ,text: '所在集群'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'namespace'
                            ,text: '命名空间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'publishGroup'
                            ,text: '发布物分组'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'publishArtifact'
                            ,text: '发布物'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'publishVersion'
                            ,text: '版本标识'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'publishFile'
                            ,text: '发布文件名'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'instancesNum'
                            ,format:'0,000'
                            ,text: '实例个数'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'cpu'
                            ,format:'0,000'
                            ,text: 'CPU'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'memory'
                            ,format:'0,000.00'
                            ,text: '内存(G)'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'serviceable'
                            ,format:'0,000'
                            ,text: '发布为服务'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'lbStrategy'
                            ,text: '负载策略'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'accessType'
                            ,text: '访问类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'notes'
                            ,text: '备注'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'prdRid'
                            ,text: '产品编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'schemeRid'
                            ,text: '部署方案编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmpRid'
                            ,text: '组件编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'taskRid'
                            ,text: '任务编号'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'seq'
                            ,format:'0,000'
                            ,text: '顺序号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'createUcode'
                            ,text: '创建用户代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'createUname'
                            ,text: '创建用户姓名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'modifyUcode'
                            ,text: '修改用户代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'modifyUname'
                            ,text: '修改用户姓名'
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

        me.add({xtype:'devp.publish.DevpSysOpsTaskDockerAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskDockerEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskDockerSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskDockerDetailWindow',reference:'mainDetailWindow'})

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