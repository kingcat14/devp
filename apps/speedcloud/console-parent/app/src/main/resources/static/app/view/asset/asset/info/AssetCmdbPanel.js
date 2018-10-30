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
                data:{
                    record:null
                }
                ,stores:{
                    assetTypeStore:{type:'tree',autoLoad:false, model:'AM.model.asset.asset.config.AssetTypeTreeNode', nodeParam:'id'}
                    ,assetCmdbStore:Ext.create('AM.store.asset.asset.info.AssetCmdbStore').load()
                    ,assetPropertyStore:Ext.create('AM.store.asset.asset.info.AssetPropertyStore', {pageSize:1000}).applyCondition({asset:-999}).load()
                }
                ,formulas: {
                    propertyTitle: function (get) {
                        if(get('record')) {

                            return '属性列表:[' + get('record.name') + ']';
                        }
                        return '属性列表';
                    }
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
                            ,locked: true
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
                            ,text: '资产名称'
                            ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '资产代码'
                            ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '资产别名'
                            ,locked: true
                            ,hidden: true
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
                            ,dataIndex: 'barcode'
                            ,text: '资产条码'
                            ,hidden: true

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
                            ,hidden:true

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
                            ,flex: 1
                            ,text: '所在区域'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetLocation'
                            ,flex: 1
                            ,text: '所在地址'

                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'goliveDate'
                            ,text: '启用时间'
                            ,hidden: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'notes'
                            ,text: 'notes'
                            ,flex:1
                            ,hidden:true
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
                    ,listeners: {
                        rowdblclick:'assetItemDbClick'
                    }
                }
                ,{
                    xtype:'grid'
                    ,region: 'east'
                    ,title:'属性'
                    ,bind:{title:'{propertyTitle}', store:'{assetPropertyStore}'}
                    ,width: '20%'
                    ,frame: true
                    ,split: true
                    ,collapsible:true
                    ,collapsed:true
                    ,reference:'assetPropertyGridPanel'
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '属性名称'
                            ,flex:1
                            ,editor:{
                                xtype: 'textfield', blankText:'必填字段', emptyText:'必填字段'
                                ,allowBlank:false
                            }

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'value'
                            ,text: '属性值'
                            ,flex:1
                            ,editor:'textfield'

                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-minus-circle red'
                                ,tooltip: '删除'
                                ,handler: function(table, rowIndex, colIndex, item, event, record) {
                                    record.erase({
                                        failure: function(record, operation) {
                                            Ext.MsgUtil.notification('操作成功','删除属性成功!');
                                        }
                                        ,success: function(record, operation) {
                                            Ext.MessageBox.show({title: '操作失败', msg: '删除属性失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                                        }
                                    })
                                }
                            }]
                        }
                    ]
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
                                        click: 'onPropertyAddButtonClick'
                                    }
                                }
                            ]
                        }
                    ]
                    ,plugins: [
                        {
                            ptype: 'rowediting'
                            ,id: 'assetPropertyRowEditing'
                            ,clicksToEdit: 2
                            ,saveBtnText:'保存'
                            ,cancelBtnText: '取消'
                            ,dirtyText: "你要确认或取消更改"
                        }
                    ]
                    ,listeners: {
                        edit:function (editor, e) {
                            e.record.save();
                        }
                        ,canceledit: function(editor, e) {
                            if(e.record.phantom){
                                e.record.drop()
                            }
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