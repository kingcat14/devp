Ext.define('AM.view.asset.asset.info.AssetCmdbPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'asset.asset.info.AssetCmdbPanel'
    , alias: 'widget.asset.asset.info.AssetCmdbPanel'
    , title: 'IT资产'
    , layout: 'border'
    , requires: [
        'AM.view.asset.asset.info.AssetCmdbController'
        ,'AM.store.asset.asset.info.AssetCmdbStore'
        ,'AM.view.asset.asset.info.AssetCmdbAddWindow'
        ,'AM.view.asset.asset.info.AssetCmdbEditWindow'
        ,'AM.view.asset.asset.info.AssetCmdbSearchWindow'
        ,'AM.view.asset.asset.info.AssetCmdbDetailWindow'
        ,'AM.model.asset.asset.config.AssetTypeTreeNode'
    ]
    ,bodyPadding:10
    ,bodyCls: 'app-dashboard'
    ,controller: 'asset.asset.info.AssetCmdbController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    assetTypeStore:{type:'tree',autoLoad:false, model:'AM.model.asset.asset.config.AssetTypeTreeNode', nodeParam:'id'}
                    ,assetCmdbStore:Ext.create('AM.store.asset.asset.info.AssetCmdbStore')
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');

        Ext.apply(me, {
            items: [
                {
                    xtype: 'treepanel'
                    ,title: '资产分类'
                    ,collapsible:true
                    ,region: 'west'
                    ,width: '30%'
                    ,frame: true
                    ,split: true
                    ,reference: 'assetTypeTree'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,bind:{
                        store:'{assetTypeStore}'
                    }
                    ,columns:[
                        {
                            xtype: 'treecolumn'
                            ,dataIndex: 'name'
                            ,flex: 1
                            ,sortable: true
                            ,renderer: function(value, metaData, record) {
                                return record.get('name')+'('+record.get('code')+')'
                            }
                        }
                    ]
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'fas fa-sync',
                            handler: 'loadTypeTree'
                        }
                    ]
                    ,listeners: {
                        itemclick: 'onAssetTypeTreeItemClick'
                        ,afterrender: 'loadTypeTree'
                    }

                }
                ,{
                    xtype: 'grid'
                    ,region:'center'
                    ,title:'资产'
                    // ,store: Ext.create('AM.store.asset.asset.info.AssetCmdbStore').load()
                    ,bind:{
                        store:'{assetCmdbStore}'
                    }
                    ,columnLines: true
                    ,frame:true
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
                            ,dataIndex: 'barcode'
                            ,text: '资产条码'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '资产名称'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '资产代码'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '资产别名'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'category'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("categoryVO")?record.get("categoryVO").name:'';
                            }
                            ,text: '资产大类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '资产分类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'unit'
                            ,text: '计量单位'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '描述'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
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
                            ,dataIndex: 'assetArea'
                            ,text: '所在区域'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetLocation'
                            ,text: '所在地址'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'acquisitionMode'
                            ,text: '获取模式'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'acquisitionDesc'
                            ,text: '获取描述'

                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'goliveDate'
                            ,text: '启用时间'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'notes'
                            ,text: 'notes'
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
                                    ,iconCls: 'fas fa-search-plus'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-download'
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

        me.add({xtype:'asset.asset.info.AssetCmdbAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'asset.asset.info.AssetCmdbEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'asset.asset.info.AssetCmdbSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'asset.asset.info.AssetCmdbDetailWindow',reference:'mainDetailWindow'})

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