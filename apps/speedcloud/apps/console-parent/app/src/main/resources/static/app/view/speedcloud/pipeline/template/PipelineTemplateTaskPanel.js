Ext.define('AM.view.speedcloud.pipeline.template.PipelineTemplateTaskPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.template.PipelineTemplateTaskPanel'
    , title: '运维任务模板'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.template.PipelineTemplateTaskController'
        ,'AM.store.speedcloud.pipeline.template.PipelineTemplateTaskStore'
        ,'AM.view.speedcloud.pipeline.template.PipelineTemplateTaskSearchWindow'
    ]
    ,controller: 'speedcloud.pipeline.template.PipelineTemplateTaskController'
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.speedcloud.pipeline.template.PipelineTemplateTaskStore').load()
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '任务名称'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'taskType'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("taskTypeVO")?record.get("taskTypeVO").displayName:'';
                            }
                            ,text: '任务类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'execType'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("execTypeVO")?record.get("execTypeVO").displayName:'';
                            }
                            ,text: '执行计划'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'taskStartTime'
                            ,text: '执行开始时间'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'taskDayOfWeeks'
                            ,text: '执行日'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'description'
                            ,text: '任务描述'
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
                                    ,iconCls: 'fas fa-search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-search'
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
                        itemdblclick:'onMainPanelRowClick'
                    }
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

        me.add({xtype:'speedcloud.pipeline.template.PipelineTemplateTaskSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})

        me.callParent(arguments);
    }

    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('mainDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,onPanelBeforeHide:function () {
        console.log('onPanelBeforeHide')
    }
    ,onBeforeShow:function(abstractcomponent, options) {
        console.log('onBeforeShow')
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
});