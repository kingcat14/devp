Ext.define('AM.view.icode.project.ComponentLocalLocationPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'icode.project.ComponentLocalLocationPanel'
    , alias: 'widget.icode.project.ComponentLocalLocationPanel'
    , title: '组件本地路径'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
    , layout: 'border'
    , requires: [
        'AM.view.icode.project.ComponentLocalLocationController'
        ,'AM.store.icode.project.ComponentLocalLocationStore'
        ,'AM.view.icode.project.ComponentLocalLocationAddWindow'
        ,'AM.view.icode.project.ComponentLocalLocationEditWindow'
        ,'AM.view.icode.project.ComponentLocalLocationSearchWindow'
        ,'AM.view.icode.project.ComponentLocalLocationDetailWindow'
    ]
    ,controller: 'icode.project.ComponentLocalLocationController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.icode.project.ComponentLocalLocationStore').load()
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
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'product'
                            // ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                            //     return record.get("productVO")?record.get("productVO").productName:'';
                            // }
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(!record.get("componentVO") || !record.get("componentVO").productVO){
                                    return '';
                                }
                                return record.get("componentVO").productVO.productName;
                            }
                            ,text: '所属产品'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            // ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                            //     return record.get("typeVO")?record.get("typeVO").name:'';
                            // }
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(!record.get("componentVO") || !record.get("componentVO").typeVO){
                                    return '';
                                }
                                return record.get("componentVO").typeVO.name;
                            }
                            ,text: '类型'
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'number'
                            ,format:'0,000'
                            ,text: '组件编号'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("componentVO")?record.get("componentVO").number:'';
                            }

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '组件名称'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("componentVO")?record.get("componentVO").name:'';
                            }

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '组件代码'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("componentVO")?record.get("componentVO").code:'';
                            }

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'basePackage'
                            ,text: '基础包'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("componentVO")?record.get("componentVO").basePackage:'';
                            }

                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'runnable'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '可运行组件'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("componentVO")?(record.get("componentVO").runnable?'是':'否'):'';
                            }
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'location'
                            ,text: '本地路径'
                            ,flex:1
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
                                    ,iconCls: 'fas fa-pencil-alt'
                                    ,text: '设置'
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
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-download'
                                    ,text: '导出'
                                    ,listeners: {
                                        click: 'onExportButtonClick'
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


                }
            ]
            ,listeners: {
            	beforeshow: {
                    fn: me.onBeforeShow
                    ,scope: me
                }
			}
        });

        me.add({xtype:'icode.project.ComponentLocalLocationAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'icode.project.ComponentLocalLocationEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'icode.project.ComponentLocalLocationSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'icode.project.ComponentLocalLocationDetailWindow',reference:'mainDetailWindow'})

        me.callParent(arguments);
    }

    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('mainDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});