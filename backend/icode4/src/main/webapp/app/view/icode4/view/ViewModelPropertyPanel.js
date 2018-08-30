Ext.define('AM.view.icode4.view.ViewModelPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.view.ViewModelPropertyPanel'
    ,title: '展现对象属性'
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [

                {
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'
                    
                    ,text: '属性名'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'code'
                    
                    ,text: '属性代码'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'type'
                    
                    ,text: '属性类型'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'viewModelId'
                    ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get("viewModelName")?record.get("viewModelName"):'';
                    }
                    
                    ,text: '所属展现对象'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '可修改'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'nullable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '可为空'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'memo'
                    
                    ,text: '备注'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'coreRelation'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '核心关联'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'domainModelId'
                    ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get("domainModelName")?record.get("domainModelName"):'';
                    }
                    
                    ,text: '关联对象'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'domainModelPropertyId'
                    ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get("domainModelPropertyName")?record.get("domainModelPropertyName"):'';
                    }
                    
                    ,text: '关联对象属性'
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'propertyGroup'
                    
                    ,text: '属性组'
                }

                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'gridIndex'
                    ,format:'0,000'
                    
                    ,text: '列表排序'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'gridHidden'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '列表隐藏'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'quickSearchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '是否快速查询条件'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'searchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '是否查询条件'
                }

                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'formIndex'
                    ,format:'0,000'
                    
                    ,text: '表单排序'
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'formHidden'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    
                    ,text: '表单隐藏'
                }

                ,{
                    xtype: 'numbercolumn'
                    ,dataIndex: 'viewIndex'
                    ,format:'0,000'
                    ,flex:1
                    ,text: '展现排序'
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
                            ,emptyText:'请输入属性名查询'
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

        var searchCondition = {name:simpleSearchField.getValue()}

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

        var record = Ext.create('AM.model.icode4.view.ViewModelProperty', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新展现对象属性');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改展现对象属性信息');
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
                        	Ext.MsgUtil.show('操作成功','删除展现对象属性成功!');
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
        editWindow.setTitle('修改展现对象属性信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;

        var addWindow = me.addWindow;

        if(!addWindow||addWindow.isHidden()){

            addWindow = Ext.create('AM.view.icode4.view.ViewModelPropertyAddWindow',{store:me.getStore()});
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

            editWindow = Ext.create('AM.view.icode4.view.ViewModelPropertyEditWindow',{store:me.getStore()});
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

            detailWindow = Ext.create('AM.view.icode4.view.ViewModelPropertyDetailWindow',{store:me.getStore()});
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

            searchWindow = Ext.create('AM.view.icode4.view.ViewModelPropertySearchWindow');
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