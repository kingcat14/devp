Ext.define('AM.view.maintenance.hardware.MachinePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'maintenance.hardware.MachinePanel'
    , title: '服务器'
    , layout: 'border'
    , requires: [
        'AM.view.maintenance.hardware.MachineController'
        ,'AM.view.maintenance.hardware.MachineAddWindow'
        ,'AM.view.maintenance.hardware.MachineEditWindow'
        ,'AM.view.maintenance.hardware.MachineSearchWindow'
        ,'AM.view.maintenance.hardware.MachineDetailWindow'
    ]
    ,controller: 'maintenance.hardware.MachineController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.maintenance.hardware.MachineStore')
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
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
                            ,text: '名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '描述'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'typeCode'
                            ,text: '类型代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'hardwareModel'
                            ,text: '硬件型号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'softwareModel'
                            ,text: '软件型号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("statusVO")?record.get("statusVO").displayName:'';
                            }
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'createDate'
                            ,text: '创建时间'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'expireDate'
                            ,text: '到期时间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetProject'
                            ,text: '所属项目'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetArea'
                            ,text: '所属区域'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetLocation'
                            ,text: '资产位置'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'intAccessAddr'
                            ,text: '内部访问地址'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'extAccessAddr'
                            ,text: '外部访问地址'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'acquisitionMode'
                            ,text: '获取方式'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'acquisitionDesc'
                            ,text: '获取方式说明'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetDept'
                            ,text: '归属部门'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetManager'
                            ,text: '资产负责人'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'useDept'
                            ,text: '使用部门'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'useManager'
                            ,text: '使用负责人'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'opsDept'
                            ,text: '维护部门'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'opsManager'
                            ,text: '维护负责人'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'bizLine'
                            ,text: '业务线'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'bizManager'
                            ,text: '业务代表'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'goliveDate'
                            ,text: '启用时间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'majorCust'
                            ,text: '主要客户'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'custManager'
                            ,text: '客户代表'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'custUsage'
                            ,text: '使用情况'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'notes'
                            ,text: '备注'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'prdRid'
                            ,text: '关联产品记录编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'parasCode'
                            ,text: '参数定义标识'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'acquisitionProvider'
                            ,text: '供应商'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'attachment'
                            ,text: '附件'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'recordState'
                            ,format:'0,000'
                            ,text: '记录状态'
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
                                    ,emptyText:'名称'
                                    ,reference: 'nameField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'代码'
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

        me.add({xtype:'maintenance.hardware.MachineAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.hardware.MachineEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.hardware.MachineSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'maintenance.hardware.MachineDetailWindow',reference:'mainDetailWindow'})

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