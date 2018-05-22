Ext.define('AM.view.product.product.DevpPrdProductPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'product.product.DevpPrdProductPanel'
    ,title: '产品定义'
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [

                {
                    xtype: 'numbercolumn'
                    ,dataIndex: 'tid'
                    ,format:'0,000'
                    
                    ,text: '租户编号'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'code'
                    
                    ,text: '产品代码'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'
                    
                    ,text: '产品名称'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'alias'
                    
                    ,text: '产品别名'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'description'
                    
                    ,text: '产品描述'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'type'
                    
                    ,text: '产品类型'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'stereotype'
                    
                    ,text: '构造型'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'scope'
                    
                    ,text: '范围'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'prdDept'
                    
                    ,text: '所属部门'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'prdOwner'
                    
                    ,text: '产品负责人'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'devManager'
                    
                    ,text: '开发负责人'
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
                    ,dataIndex: 'golive'
                    
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
                    
                    ,text: '客户使用情况'
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
                    ,dataIndex: 'version'
                    
                    ,text: '版本'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'phase'
                    
                    ,text: '阶段'
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
                    xtype: 'numbercolumn'
                    ,dataIndex: 'recordState'
                    ,format:'0,000'
                    
                    ,text: '记录状态'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'createUcode'
                    
                    ,text: '创建用户代码'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'cmodifyUcode'
                    ,flex:1
                    ,text: '修改用户代码'
                }
                ,{
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
                            xtype:'textfield'
                            ,emptyText:'请输入租户编号查询'
                            ,itemId:'simpleSearchField'

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
        });

        me.callParent(arguments);
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var panel = options.scope;

        var toolbar = this.down('toolbar')

        var simpleSearchField = panel.down("#simpleSearchField");

        var searchCondition = {tid:simpleSearchField.getValue()}

        this.store.proxy.extraParams = {searchCondition:searchCondition};
        this.store.load({
            params:{
                start:0,
                page:0
            }
        });
    }
	,onAddButtonClick: function(button, e, options) {

        var modelConfig = {}

        var record = Ext.create('AM.model.product.product.DevpPrdProduct', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新产品定义');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改产品定义信息');
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
                        	Ext.MsgUtil.show('操作成功','删除产品定义成功!');
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
        editWindow.setTitle('修改产品定义信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;

        var addWindow = me.addWindow;

        if(!addWindow||addWindow.isHidden()){

            addWindow = Ext.create('AM.view.product.product.DevpPrdProductAddWindow',{store:me.getStore()});
            me.addWindow = addWindow;
        }

        addWindow.setModel(model);

        addWindow.show(targetComponent);
        addWindow.setStore(this.store);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;

        var editWindow = me.editWindow;

        if(!editWindow||editWindow.isHidden()){

            editWindow = Ext.create('AM.view.product.product.DevpPrdProductEditWindow',{store:me.getStore()});
            me.editWindow = editWindow;
        }

        editWindow.setModel(model);

        editWindow.show(targetComponent);
        editWindow.setStore(this.store);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;

        var detailWindow = me.detailWindow;

        if(!detailWindow||detailWindow.isHidden()){

            detailWindow = Ext.create('AM.view.product.product.DevpPrdProductDetailWindow',{store:me.getStore()});
            me.detailWindow = detailWindow;
        }

        detailWindow.setModel(model);

        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.searchWindow;

        if(!searchWindow){

            searchWindow = Ext.create('AM.view.product.product.DevpPrdProductSearchWindow');
            me.searchWindow = searchWindow;
            searchWindow.setStore(this.store);
        }

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