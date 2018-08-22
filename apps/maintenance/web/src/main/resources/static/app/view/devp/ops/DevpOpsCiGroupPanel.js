Ext.define('AM.view.devp.ops.DevpOpsCiGroupPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'devp.ops.DevpOpsCiGroupPanel'
    ,title: '资产项目分组映射'
    ,requires: [
        'AM.view.devp.ops.DevpOpsCiGroupController'
        ,'AM.view.devp.ops.DevpOpsCiGroupAddWindow'
        ,'AM.view.devp.ops.DevpOpsCiGroupEditWindow'
        ,'AM.view.devp.ops.DevpOpsCiGroupSearchWindow'
        ,'AM.view.devp.ops.DevpOpsCiGroupDetailWindow'
    ]
    ,controller: 'ops_DevpOpsCiGroupController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [
                {
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
                    xtype: 'numbercolumn'
                    ,dataIndex: 'recordState'
                    ,format:'0,000'
                    ,text: '记录状态'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'typeCode'
                    ,text: '类型代码'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'typeName'
                    ,text: '类型名称'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'groupRid'
                    ,text: '分组记录编号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'ciRid'
                    ,text: '资产记录编号'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'seq'
                    ,format:'0,000'
                    ,text: '顺序号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'parasCode'
                    ,text: '参数定义标识'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'cmodifyUcode'
                    ,text: '修改用户代码'
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
        me.add({xtype:'devp.ops.DevpOpsCiGroupAddWindow',reference:'devpOpsCiGroupAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsCiGroupEditWindow',reference:'devpOpsCiGroupEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsCiGroupSearchWindow',reference:'devpOpsCiGroupSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.ops.DevpOpsCiGroupDetailWindow',reference:'devpOpsCiGroupDetailWindow'})

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

        var record = Ext.create('AM.model.devp.ops.DevpOpsCiGroup', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新资产项目分组映射');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改资产项目分组映射信息');
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
                        	Ext.MsgUtil.show('操作成功','删除资产项目分组映射成功!');
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
        editWindow.setTitle('修改资产项目分组映射信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('devpOpsCiGroupAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('devpOpsCiGroupEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('devpOpsCiGroupDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('devpOpsCiGroupSearchWindow');
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