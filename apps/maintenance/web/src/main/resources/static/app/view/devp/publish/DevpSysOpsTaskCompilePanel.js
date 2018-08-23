Ext.define('AM.view.devp.publish.DevpSysOpsTaskCompilePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'devp.publish.DevpSysOpsTaskCompilePanel'
    , title: '编译设置'
    , layout: 'border'
    , requires: [
        'AM.view.devp.publish.DevpSysOpsTaskCompileController'
        ,'AM.store.devp.publish.DevpSysOpsTaskCompileStore'
        ,'AM.view.devp.publish.DevpSysOpsTaskCompileAddWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskCompileEditWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskCompileSearchWindow'
        ,'AM.view.devp.publish.DevpSysOpsTaskCompileDetailWindow'
    ]
    ,controller: 'devp.publish.DevpSysOpsTaskCompileController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.devp.publish.DevpSysOpsTaskCompileStore').load()
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
                            xtype: 'numbercolumn'
                            ,dataIndex: 'cmSameas'
                            ,format:'0,000'
                            ,text: '采用相同代码仓库'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmType'
                            ,text: '代码仓库类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmRepository'
                            ,text: '仓库地址'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmTag'
                            ,text: '分支标识'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmUser'
                            ,text: '用户名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'cmPassword'
                            ,text: '密码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'subDir'
                            ,text: '子目录'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'baseline'
                            ,text: '基线标识'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'svnCodeUrl'
                            ,text: '代码路径'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'buildTools'
                            ,text: '构建工具'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'envVersion'
                            ,text: '执行环境版本'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'toolVersion'
                            ,text: '工具版本'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'preAction'
                            ,text: '构建前操作'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'buildAction'
                            ,text: '构建操作'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'postAction'
                            ,text: '构建后操作'
                            
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

        me.add({xtype:'devp.publish.DevpSysOpsTaskCompileAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskCompileEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskCompileSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.publish.DevpSysOpsTaskCompileDetailWindow',reference:'mainDetailWindow'})

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