Ext.define('AM.view.monitor.app.ApplicationPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'monitor.app.ApplicationPanel'
    , alias: 'widget.monitor.app.ApplicationPanel'
    , title: '程序'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
    , layout: 'border'
    , requires: [
        'AM.view.monitor.app.ApplicationController'
        ,'AM.store.monitor.app.ApplicationStore'
        ,'AM.view.monitor.app.ApplicationAddWindow'
        ,'AM.view.monitor.app.ApplicationEditWindow'
        ,'AM.view.monitor.app.ApplicationSearchWindow'
        ,'AM.view.monitor.app.ApplicationDetailWindow'
    ]
    ,controller: 'monitor.app.ApplicationController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.monitor.app.ApplicationStore', {pageSize:200}).load()
                    ,cacheStore:Ext.create('AM.store.monitor.app.ApplicationStore', {pageSize:200})
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
                    ,bind:{store: '{cacheStore}'}
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
                            ,dataIndex: 'name'
                            ,text: '名称'
                            ,flex:0.7
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(record.get('aliveCount') <= record.get('thresholdValue')){
                                    return '<span style="color:red">'+value+'</span>'
                                }
                                return '<span style="color:green">'+value+'</span>'
                            }
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            ,flex:1
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(record.get('aliveCount') <= record.get('thresholdValue')){
                                    return '<span style="color:red">'+value+'</span>'
                                }
                                return '<span style="color:green">'+value+'</span>'
                            }
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'totalCount'
                            ,format:'0,000'
                            ,text: '配置实例数量'
                            ,hidden:true
                            
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'aliveCount'
                            ,format:'0,000'
                            ,text: '当前实例数量'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(record.get('aliveCount') <= record.get('thresholdValue')){
                                    return '<span style="color:red">'+value+'</span>'
                                }
                                return '<span style="color:green">'+value+'</span>'
                            }
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'alarm'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '低实例告警'
                            ,hidden:true
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'enable'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '启动监控'
                            ,hidden:true
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'thresholdValue'
                            ,format:'0,000'
                            ,text: '告警数量'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                if(record.get('aliveCount') <= record.get('thresholdValue')){
                                    return '<span style="color:red">'+value+'</span>'
                                }
                                return '<span style="color:green">'+value+'</span>'
                            }
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
                                    ,text: 'Spring Boot Admin'
                                    ,iconCls: 'fas fa-leaf green'
                                    ,handler: function() {
                                        window.open("/admin")
                                    }
                                }
                                // ,{
                                //     xtype: 'button'
                                //     ,iconCls: 'fas fa-plus-circle'
                                //     ,text: '新增'
                                //     ,listeners: {
                                //         click: 'onAddButtonClick'
                                //     }
                                // }
                                // ,{
                                //     xtype: 'button'
                                //     ,iconCls: 'fas fa-pencil-alt'
                                //     ,text: '修改'
                                //     ,listeners: {
                                //         click: 'onEditButtonClick'
                                //     }
                                // }
                                // ,{
                                //     xtype: 'button'
                                //     ,iconCls: 'fas fa-minus-circle red'
                                //     ,text: '删除'
                                //     ,listeners: {
                                //         click: 'onDeleteButtonClick'
                                //     }
                                // }
                                ,'-'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-sync-alt'
                                    ,text: '刷新'
                                    ,listeners: {
                                        click: 'refresh'
                                    }
                                }
                                // ,{
                                //     xtype: 'textfield'
                                //     ,width:120
                                //     ,emptyText:'名称'
                                //     ,reference: 'nameField'
                                // }
                                // ,{
                                //     xtype: 'textfield'
                                //     ,width:300
                                //     ,emptyText:'代码'
                                //     ,reference: 'codeField'
                                // }
                                // ,{
                                //     xtype: 'button'
                                //     ,iconCls: 'fab fa-search'
                                //     ,text: '查询'
                                //     ,listeners: {
                                //         click: 'onSimpleSearchButtonClick'
                                //     }
                                // }
                            ]
                        }

                    ]
                    ,selModel: 'checkboxmodel'
                    ,listeners:{
                        afterrender: 'refreshData'
                    }
                }
            ]
            ,listeners: {

			}
        });

        me.add({xtype:'monitor.app.ApplicationAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'monitor.app.ApplicationEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'monitor.app.ApplicationSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'monitor.app.ApplicationDetailWindow',reference:'mainDetailWindow'})

        me.callParent(arguments);
    }

    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('mainDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
});