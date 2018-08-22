Ext.define('AM.view.devp.ops.DevpOpsElementInfoPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'devp.ops.DevpOpsElementInfoPanel'
    ,title: '系统元素扩充信息'
    ,requires: [
        'AM.view.devp.ops.DevpOpsElementInfoController'
        ,'AM.view.devp.ops.DevpOpsElementInfoAddWindow'
        ,'AM.view.devp.ops.DevpOpsElementInfoEditWindow'
        ,'AM.view.devp.ops.DevpOpsElementInfoSearchWindow'
        ,'AM.view.devp.ops.DevpOpsElementInfoDetailWindow'
    ]
    ,controller: 'ops_DevpOpsElementInfoController'
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
                    ,dataIndex: 'code'
                    ,text: '扩展信息代码'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'
                    ,text: '扩展信息名称'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'alias'
                    ,text: '扩展信息别名'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'description'
                    ,text: '扩展信息描述'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'recordState'
                    ,format:'0,000'
                    ,text: '记录状态'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'elmRid'
                    ,text: '元素编号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'instRid'
                    ,text: '元素实例编号'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'seq'
                    ,format:'0,000'
                    ,text: '顺序号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoCode1'
                    ,text: '扩展信息代码1'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoValue1'
                    ,text: '扩展信息值1'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoCode2'
                    ,text: '扩展信息代码2'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoValue2'
                    ,text: '扩展信息值2'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoCode3'
                    ,text: '扩展信息代码3'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoValue3'
                    ,text: '扩展信息值3'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoCode4'
                    ,text: '扩展信息代码4'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoValue4'
                    ,text: '扩展信息值4'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoCode5'
                    ,text: '扩展信息代码5'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'infoValue5'
                    ,text: '扩展信息值5'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'notes'
                    ,text: '备注'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'parasCode'
                    ,text: '参数定义标识'
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
        me.add({xtype:'devp.ops.DevpOpsElementInfoAddWindow',reference:'devpOpsElementInfoAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsElementInfoEditWindow',reference:'devpOpsElementInfoEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsElementInfoSearchWindow',reference:'devpOpsElementInfoSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.ops.DevpOpsElementInfoDetailWindow',reference:'devpOpsElementInfoDetailWindow'})

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

        var record = Ext.create('AM.model.devp.ops.DevpOpsElementInfo', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新系统元素扩充信息');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改系统元素扩充信息信息');
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
                        	Ext.MsgUtil.show('操作成功','删除系统元素扩充信息成功!');
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
        editWindow.setTitle('修改系统元素扩充信息信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('devpOpsElementInfoAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('devpOpsElementInfoEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('devpOpsElementInfoDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('devpOpsElementInfoSearchWindow');
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