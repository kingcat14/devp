Ext.define('AM.view.icode4.system.DomainModelViewPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.system.DomainModelViewPropertyPanel'
    ,title: '属性展现配置'
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [

                {
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addViewable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '新增页可见'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '新增页可填写'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addRequired'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '新增必填'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editViewable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '修改页可见'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '修改页可修改'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editRequired'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '修改页必填'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'searchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '查询可见'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'girdColumn'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '列表可见'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'simpleSearchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,flex:1
                    ,text: '快速查询条件'
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
                            ,emptyText:'请输入主键查询'
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

        var searchCondition = {id:simpleSearchField.getValue()}

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

        var record = Ext.create('AM.model.icode4.system.DomainModelViewProperty', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新属性展现配置');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改属性展现配置信息');
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
                        	Ext.MsgUtil.show('操作成功','删除属性展现配置成功!');
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
        editWindow.setTitle('修改属性展现配置信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;

        var addWindow = me.addWindow;

        if(!addWindow||addWindow.isHidden()){

            addWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertyAddWindow',{store:me.getStore()});
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

            editWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertyEditWindow',{store:me.getStore()});
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

            detailWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertyDetailWindow',{store:me.getStore()});
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

            searchWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertySearchWindow');
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