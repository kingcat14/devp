Ext.define('AM.view.deploy.deploy.DevpSysDpyCmpRefPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'deploy.deploy.DevpSysDpyCmpRefPanel'
    ,title: '系统元素间关系定义'
    ,requires: [
        'AM.view.deploy.deploy.DevpSysDpyCmpRefController'
        ,'AM.view.deploy.deploy.DevpSysDpyCmpRefAddWindow'
        ,'AM.view.deploy.deploy.DevpSysDpyCmpRefEditWindow'
        ,'AM.view.deploy.deploy.DevpSysDpyCmpRefSearchWindow'
        ,'AM.view.deploy.deploy.DevpSysDpyCmpRefDetailWindow'
    ]
    ,controller: 'deploy_DevpSysDpyCmpRefController'
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
                    ,dataIndex: 'code'
                    ,text: '对应关系代码'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'
                    ,text: '对应关系名称'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'alias'
                    ,text: '对应关系别名'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'description'
                    ,text: '对应关系描述'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'recordState'
                    ,format:'0,000'
                    ,text: '记录状态'
                    
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
                    ,dataIndex: 'refEtype'
                    ,text: '关联元素类型'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'refPrdRid'
                    ,text: '关联产品编号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'refElmRid'
                    ,text: '关联元素编号'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'refInstRid'
                    ,text: '关联元素实例编号'
                    
                }
                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'seq'
                    ,format:'0,000'
                    ,text: '顺序号'
                    
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
                    ,dataIndex: 'direction'
                    ,text: '方向'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'srcMulti'
                    ,text: '来源对应数量'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'srcRole'
                    ,text: '来源角色'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'srcRoleType'
                    ,text: '来源角色类型'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'destMulti'
                    ,text: '目标对应数量'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'destRole'
                    ,text: '目标角色'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'destRoleType'
                    ,text: '目标角色类型'
                    
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'attrRelation'
                    ,text: '属性对应关系'
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
        me.add({xtype:'deploy.deploy.DevpSysDpyCmpRefAddWindow',reference:'devpSysDpyCmpRefAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'deploy.deploy.DevpSysDpyCmpRefEditWindow',reference:'devpSysDpyCmpRefEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'deploy.deploy.DevpSysDpyCmpRefSearchWindow',reference:'devpSysDpyCmpRefSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'deploy.deploy.DevpSysDpyCmpRefDetailWindow',reference:'devpSysDpyCmpRefDetailWindow'})

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

        var record = Ext.create('AM.model.deploy.deploy.DevpSysDpyCmpRef', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新系统元素间关系定义');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改系统元素间关系定义信息');
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
                        	Ext.MsgUtil.show('操作成功','删除系统元素间关系定义成功!');
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
        editWindow.setTitle('修改系统元素间关系定义信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('devpSysDpyCmpRefAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('devpSysDpyCmpRefEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('devpSysDpyCmpRefDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('devpSysDpyCmpRefSearchWindow');
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