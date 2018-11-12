Ext.define('AM.view.monitor.app.UnknownAppPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'monitor.app.UnknownAppPanel'
    , alias: 'widget.monitor.app.UnknownAppPanel'
    , title: '未知程序'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
    , layout: 'border'
    , requires: [
        'AM.view.monitor.app.UnknownAppController'
        ,'AM.store.monitor.app.UnknownAppStore'
        ,'AM.view.monitor.app.ApplicationAddWindow'
    ]
    ,controller: 'monitor.app.UnknownAppController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.monitor.app.UnknownAppStore').load()
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
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:35
                            ,items: [{
                                iconCls: 'x-fa fa-eye'
                                ,tooltip: '详情'
                                ,handler: 'addToMonitor'
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            ,flex: 1
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d H:i:s'
                            ,dataIndex: 'registerTime'
                            ,text: '发现时间'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'alive'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '当前活跃'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'aliveCount'
                            ,format:'0,000'
                            ,text: '当前数量'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'maxCount'
                            ,format:'0,000'
                            ,text: '最大数量'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'ignored'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '忽略'
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

                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'代码'
                                    ,reference: 'codeField'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,width:120
                                    ,emptyText:'是否忽略'
                                    ,store: [
                                        [true,'已忽略']
                                        ,[false,'未忽略']
                                    ]
                                    ,typeAhead:false
                                    ,editable:false
                                    ,reference: 'ignoredField'
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
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
                    ,listeners: {}
                }
            ]
            ,listeners: {
            	beforeshow: {
                    fn: me.onBeforeShow
                    ,scope: me
                }
              	,beforehide: {
                	fn: me.onPanelBeforeHide
                  	,scope: me
				}
			}
        });


        me.add({xtype:'monitor.app.ApplicationAddWindow',reference:'applicationAddWindow',listeners:{saved:'reloadStore'}})

        me.callParent(arguments);
    }



    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});