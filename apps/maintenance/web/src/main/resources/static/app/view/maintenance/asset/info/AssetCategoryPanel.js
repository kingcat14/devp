Ext.define('AM.view.maintenance.asset.info.AssetCategoryPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'maintenance.asset.info.AssetCategoryPanel'
    ,title: '资产大类'
    ,requires: [
        'AM.view.maintenance.asset.info.AssetCategoryController'
        ,'AM.view.maintenance.asset.info.AssetCategoryAddWindow'
        ,'AM.view.maintenance.asset.info.AssetCategoryEditWindow'
        ,'AM.view.maintenance.asset.info.AssetCategorySearchWindow'
        ,'AM.view.maintenance.asset.info.AssetCategoryDetailWindow'
    ]
    ,controller: 'asset.info_AssetCategoryController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            columnLines: true
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
                            me.getSelectionModel().deselectAll()
                            me.getSelectionModel().select(record)
                            me.showDetailWindow(record, this);
                        }
                    }]
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'num'
                    ,text: '编号'
                    
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
                    ,dataIndex: 'viewIndex'
                    ,text: '展现顺序'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'description'
                    ,text: '说明'
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
                            me.getSelectionModel().deselectAll()
                            me.getSelectionModel().select(record)
                            me.showEditWindow(record, this);
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
                            me.getSelectionModel().deselectAll()
                            me.getSelectionModel().select(record)
                            me.onDeleteButtonClick();
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
                                click: {
                                    fn: me.onAddButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'edit'
                            ,text: '修改'
                            ,listeners: {
                                click: {
                                    fn: me.onEditButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'delete'
                            ,text: '删除'
                            ,listeners: {
                                click: {
                                    fn: me.onDeleteButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,'-'
                        ,{
                            xtype: 'textfield'
                            ,width:120
                            ,emptyText:'编号'
                            ,itemId: 'numField'
                        }
                        ,{
                            xtype: 'textfield'
                            ,width:120
                            ,emptyText:'名称'
                            ,itemId: 'nameField'
                        }
                        ,{
                            xtype: 'textfield'
                            ,width:120
                            ,emptyText:'代码'
                            ,itemId: 'codeField'
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {
                                    fn: me.onSimpleSearchButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,'->'
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '高级查询'
                            ,listeners: {
                                click: {
                                    fn: me.showSearchWindow
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '导出'
                            ,listeners: {
                                click: {
                                    fn: me.onExportButtonClick
                                    ,scope: me
                                }
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
                ,itemdblclick: {
                    fn: me.onItemDblClick
                    ,scope: me
                }
            }
            ,items:[]
        });
        me.add({xtype:'maintenance.asset.info.AssetCategoryAddWindow',reference:'assetCategoryAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.asset.info.AssetCategoryEditWindow',reference:'assetCategoryEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.asset.info.AssetCategorySearchWindow',reference:'assetCategorySearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'maintenance.asset.info.AssetCategoryDetailWindow',reference:'assetCategoryDetailWindow'})

        me.callParent(arguments);
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var panel = options.scope;

        var toolbar = this.down('toolbar')

        var numField = me.down("#numField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var condition = {
                    num:Ext.isEmpty(numField.getValue())?null:numField.getValue()
                    ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
                    ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
        };
        this.store.proxy.extraParams = {searchCondition:condition};
        this.store.load({
            params:{
                page:0
            }
        });
    }

    ,onExportButtonClick: function(button, e, options) {

        var condition = this.store.proxy.extraParams;
        if(!condition){
            condition = {searchCondition:{}};
        }
        if (!Ext.fly('formFly')) {
            var frm = document.createElement('form');
            frm.id = 'formFly';
            frm.className = 'x-hidden';
            document.body.appendChild(frm);
        }
        console.log(condition)
        Ext.Ajax.request({
            disableCaching: true
            ,url: "asset/info/assetCategory/export"
            ,method: "POST"
            ,async: false  //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            ,params:condition
            ,isUpload: true
            ,form: Ext.fly('formFly')
        });

    }
	,onAddButtonClick: function(button, e, options) {

        var modelConfig = {}

        var record = Ext.create('AM.model.maintenance.asset.info.AssetCategory', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新资产大类');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改资产大类信息');
    }
    ,onDeleteButtonClick: function(button, e, options) {
        var me = this;
        var selections = me.getSelectionModel( ).getSelection( );
        me.getStore().remove(selections);
        me.getStore().sync({
            success:function(batch,options){

                var store = options.scope;
		        var count = store.getCount();

		        var targetPage = count<=0 ? store.currentPage-1 : store.currentPage;
		        targetPage = targetPage <=0 ? 1 :targetPage;

                store.loadPage(targetPage,{
                    scope: this,
                    callback: function(records, operation, success) {
                        if(!success)
                        	Ext.Msg.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.show('操作成功','删除资产大类成功!');
                    }
                });
            }
            ,scope:me.getStore()
        });
    }
    ,onItemDblClick: function(tablepanel, record, item, index, e, options) {
        var me = this;
        options.src=item;
        var editWindow = me.showEditWindow(record, item);
        editWindow.setTitle('修改资产大类信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('assetCategoryAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('assetCategoryEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('assetCategoryDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('assetCategorySearchWindow');
        searchWindow.show(button);
    }
    ,setStore: function(store) {
        this.reconfigure(store);
        this.down('pagingtoolbar').bindStore(store);

        this.store=store;
    }
    ,onBeforeShow:function(abstractcomponent, options) {
	    this.store.reload({scope: this,callback: function(){}});
    }
    ,onPanelBeforeHide: function(abstractcomponent, options) {
    	var me = this;

    	if(me.searchWindow){
    		me.searchWindow.hide();
    	}
    	if(me.detailWindow){
    		me.detailWindow.hide();
    	}
    	if(me.editWindow){
    		me.editWindow.hide();
    	}
    	if(me.addWindow){
    		me.addWindow.hide();
    	}
    }

});