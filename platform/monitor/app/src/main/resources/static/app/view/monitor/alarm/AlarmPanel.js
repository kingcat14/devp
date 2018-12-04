Ext.define('AM.view.monitor.alarm.AlarmPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'monitor.alarm.AlarmPanel'
    , alias: 'widget.monitor.alarm.AlarmPanel'
    , title: '告警'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
    , layout: 'border'
    , requires: [
        'AM.view.monitor.alarm.AlarmController'
        ,'AM.store.monitor.alarm.AlarmStore'
        ,'AM.view.monitor.alarm.AlarmAddWindow'
        ,'AM.view.monitor.alarm.AlarmEditWindow'
        ,'AM.view.monitor.alarm.AlarmSearchWindow'
        ,'AM.view.monitor.alarm.AlarmDetailWindow'
    ]
    ,controller: 'monitor.alarm.AlarmController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.monitor.alarm.AlarmStore').load()
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
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.showDetailWindow(record, this);
                                }
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '名称'
                            
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
                            xtype: 'gridcolumn'
                            ,dataIndex: 'app'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("appVO")?record.get("appVO").name:'';
                            }
                            ,text: '程序'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'counter'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("counterVO")?record.get("counterVO").name:'';
                            }
                            ,text: '指标'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'value'
                            ,text: '指标值'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d H:i:s'
                            ,dataIndex: 'occurTime'
                            ,text: '发生时间'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d H:i:s'
                            ,dataIndex: 'lastOccurTime'
                            ,text: '最后发生时间'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d H:i:s'
                            ,dataIndex: 'disappearTime'
                            ,text: '消失时间'
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'occurCount'
                            ,format:'0,000'
                            ,text: '发生次数'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'content'
                            ,text: '内容'
                            ,flex:1
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-pencil-alt'
                                ,tooltip: '修改'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
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
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
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

        me.add({xtype:'monitor.alarm.AlarmAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'monitor.alarm.AlarmEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'monitor.alarm.AlarmSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'monitor.alarm.AlarmDetailWindow',reference:'mainDetailWindow'})

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