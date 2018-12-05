Ext.define('AM.view.icode.project.ComponentPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'icode.project.ComponentPanel'
    , alias: 'widget.icode.project.ComponentPanel'
    , title: '组件'
    , bodyCls: 'app-dashboard'
    , bodyPadding: '5'
    , layout: 'border'
    , requires: [
        'AM.view.icode.project.ComponentController'
        ,'AM.store.icode.project.ComponentStore'
        ,'AM.view.icode.project.ComponentAddWindow'
        ,'AM.view.icode.project.ComponentEditWindow'
        ,'AM.view.icode.project.ComponentSearchWindow'
        ,'AM.view.icode.project.ComponentDetailWindow'
        ,'AM.model.icode.tplfile.TplCode'
        ,'AM.store.icode.tplfile.TplFileTreeStore'
        ,'AM.view.icode.tplfile.TplCodeDetailWindow'
    ]
    ,controller: 'icode.project.ComponentController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.icode.project.ComponentStore').load()
                    ,tplFileTreeStore: Ext.create('AM.store.icode.tplfile.TplFileTreeStore', {autoLoad:true, sorters: 'name'})
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
                    xtype: 'grid'
                    ,region:'center'
                    ,frame: true
                    ,title: '组件列表'
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:35
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
                            ,dataIndex: 'product'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("productVO")?record.get("productVO").productName:'';
                            }
                            ,text: '所属产品'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '类型'
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'number'
                            ,format:'0,000'
                            ,text: '组件编号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '组件名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '组件代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'basePackage'
                            ,text: '基础包'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'runnable'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '可运行组件'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'tplSet'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("tplSetVO")?record.get("tplSetVO").name:'';
                            }
                            ,text: '代码模板'
                            ,tooltip: '点击查看'
                            ,flex:0.3
                            ,listeners:{
                                click:function (view, td, rowIndex, colIndex, event, record) {

                                    var tplFileTreePanel = me.lookupReference('tplFileTreePanel').expand();
                                    tplFileTreePanel.setTitle("【"+record.get('name')+"】代码模板");

                                    var tplSet = Ext.valueFrom(record.get('tplSet'), -999);

                                    var tplFileTreeStore = me.getViewModel().getStore('tplFileTreeStore');
                                    tplFileTreeStore.getRoot().set('id', tplSet);
                                    tplFileTreeStore.getRoot().set('type', 'TPL_SET');
                                    tplFileTreeStore.reload();

                                }
                            }

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '描述'
                            ,flex:1
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-code'
                                ,tooltip: '代码模板'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {

                                    var tplFileTreePanel = me.lookupReference('tplFileTreePanel').expand();
                                    tplFileTreePanel.setTitle("【"+record.get('name')+"】代码模板");
                                    if(!record.get('tplSet')){
                                        Ext.toast({width:200, title:'未设置代码模板',html:'请点击修改按钮设置代码模板'})

                                        tplFileTreePanel.setTitle("可选代码模板");
                                        return
                                    }
                                    var tplSet = Ext.valueFrom(record.get('tplSet'), -999);


                                    var tplFileTreeStore = me.getViewModel().getStore('tplFileTreeStore');
                                    tplFileTreeStore.getRoot().set('id', tplSet);
                                    tplFileTreeStore.getRoot().set('type', 'TPL_SET');
                                    tplFileTreeStore.reload();
                                }
                            }]
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
                                    xtype: 'combobox'
                                    ,emptyText:'所属产品'
                                    ,store: Ext.create("AM.store.icode.project.ProductStore")
                                    ,typeAhead: false
                                    ,editable: false
                                    ,displayField:'productName'
                                    ,valueField:'id'
                                    ,reference: 'productField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'组件名称'
                                    ,reference: 'nameField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'组件代码'
                                    ,reference: 'codeField'
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fab fa-searchengin'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search-plus'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
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
                        rowdbclick:function(view, record){
                            var tplFileTreePanel = me.lookupReference('tplFileTreePanel').expand();
                            tplFileTreePanel.setTitle("【"+record.get('name')+"】代码模板");

                            var tplSet = Ext.valueFrom(record.get('tplSet'), -999);

                            var tplFileTreeStore = me.getViewModel().getStore('tplFileTreeStore');
                            tplFileTreeStore.getRoot().set('id', tplSet);
                            tplFileTreeStore.getRoot().set('type', 'TPL_SET');
                            tplFileTreeStore.reload();
                        }
                    }
                }
                ,{
                    xtype: 'treepanel'
                    , region: 'east'
                    , frame:true
                    , border: false
                    , reference: 'tplFileTreePanel'
                    , bind:{store:'{tplFileTreeStore}'}
                    // , store: Ext.create('AM.store.icode.tplfile.TplFileTreeStore', {autoLoad:true, sorters: 'name'})
                    , split: true
                    , collapsible: true
                    , collapsed: true
                    , width: 350
                    , title: '组件代码模板'
                    , rootVisible: false
                    , displayField: 'name'
                    , listeners: {
                        itemclick: function(view, record, node, index, e){

                            if('TPL_CODE' == record.get('type')){
                                AM.model.icode.tplfile.TplCode.load(record.get('objectId'), {
                                    success: function (loadedRecord, operation) {
                                        me.lookupReference('tplCodeDetailWindow').setModel(loadedRecord);
                                        me.lookupReference('tplCodeDetailWindow').show(node);
                                    }
                                });
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
			}
        });

        me.add({xtype:'icode.project.ComponentAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'icode.project.ComponentEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'icode.project.ComponentSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'icode.project.ComponentDetailWindow',reference:'mainDetailWindow'})

        me.add({xtype:'icode.tplfile.TplCodeDetailWindow',reference:'tplCodeDetailWindow',listeners:{saved:'reloadStore'}})
        me.callParent(arguments);
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});