Ext.define('AM.view.devp.deploy.DevpSysDpyResourcesPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'devp.deploy.DevpSysDpyResourcesPanel'
    ,title: '部署关联资源定义'
    ,requires: [
        'AM.view.devp.deploy.DevpSysDpyResourcesController'
        ,'AM.view.devp.deploy.DevpSysDpyResourcesAddWindow'
        ,'AM.view.devp.deploy.DevpSysDpyResourcesEditWindow'
        ,'AM.view.devp.deploy.DevpSysDpyResourcesSearchWindow'
        ,'AM.view.devp.deploy.DevpSysDpyResourcesDetailWindow'
    ]
    ,controller: 'deploy_DevpSysDpyResourcesController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [
                {
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
                    xtype: 'gridcolumn'
                    ,dataIndex: 'prdRid'
                    ,text: '产品编号'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'seq'
                    ,format:'0,000'
                    ,text: '顺序号'
                    ,flex:1
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
            ,items:[]
        });
        me.add({xtype:'devp.deploy.DevpSysDpyResourcesAddWindow',reference:'devpSysDpyResourcesAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.deploy.DevpSysDpyResourcesEditWindow',reference:'devpSysDpyResourcesEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.deploy.DevpSysDpyResourcesSearchWindow',reference:'devpSysDpyResourcesSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.deploy.DevpSysDpyResourcesDetailWindow',reference:'devpSysDpyResourcesDetailWindow'})

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
                page:0
            }
        });
    }
	,onAddButtonClick: function(button, e, options) {

        var modelConfig = {}

        var record = Ext.create('AM.model.devp.deploy.DevpSysDpyResources', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新部署关联资源定义');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改部署关联资源定义信息');
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
                        	Ext.MsgUtil.show('操作成功','删除部署关联资源定义成功!');
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
        editWindow.setTitle('修改部署关联资源定义信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('devpSysDpyResourcesAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('devpSysDpyResourcesEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('devpSysDpyResourcesDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('devpSysDpyResourcesSearchWindow');
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