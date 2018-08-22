Ext.define('AM.view.devp.ops.DevpOpsRequirementPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'devp.ops.DevpOpsRequirementPanel'
    ,title: '需求定义'
    ,requires: [
        'AM.view.devp.ops.DevpOpsRequirementController'
        ,'AM.view.devp.ops.DevpOpsRequirementAddWindow'
        ,'AM.view.devp.ops.DevpOpsRequirementEditWindow'
        ,'AM.view.devp.ops.DevpOpsRequirementSearchWindow'
        ,'AM.view.devp.ops.DevpOpsRequirementDetailWindow'
    ]
    ,controller: 'ops_DevpOpsRequirementController'
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
                    ,text: '需求代码'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'
                    ,text: '需求名称'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'alias'
                    ,text: '需求别名'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'description'
                    ,text: '需求描述'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'nexusType'
                    ,text: '关联记录类型'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'nexusRid'
                    ,text: '关联记录编号'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'seq'
                    ,format:'0,000'
                    ,text: '顺序号'
                    
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
                    ,dataIndex: 'content'
                    ,text: '内容'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'hasAttachment'
                    ,format:'0,000'
                    ,text: '是否有附件'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'stereotype'
                    ,text: '构造型'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'scope'
                    ,text: '访问控制范围'
                    
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
        me.add({xtype:'devp.ops.DevpOpsRequirementAddWindow',reference:'devpOpsRequirementAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsRequirementEditWindow',reference:'devpOpsRequirementEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'devp.ops.DevpOpsRequirementSearchWindow',reference:'devpOpsRequirementSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'devp.ops.DevpOpsRequirementDetailWindow',reference:'devpOpsRequirementDetailWindow'})

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

        var record = Ext.create('AM.model.devp.ops.DevpOpsRequirement', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新需求定义');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改需求定义信息');
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
                        	Ext.MsgUtil.show('操作成功','删除需求定义成功!');
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
        editWindow.setTitle('修改需求定义信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('devpOpsRequirementAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('devpOpsRequirementEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('devpOpsRequirementDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('devpOpsRequirementSearchWindow');
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