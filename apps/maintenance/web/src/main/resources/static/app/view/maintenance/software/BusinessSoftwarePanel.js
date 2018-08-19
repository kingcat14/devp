Ext.define('AM.view.maintenance.software.BusinessSoftwarePanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'maintenance.software.BusinessSoftwarePanel'
    , title: '应用软件'
    , layout: 'border'
    , requires: [
        'AM.view.maintenance.software.BusinessSoftwareController'
        ,'AM.view.maintenance.software.BusinessSoftwareAddWindow'
        ,'AM.view.maintenance.software.BusinessSoftwareEditWindow'
        ,'AM.view.maintenance.software.BusinessSoftwareSearchWindow'
        ,'AM.view.maintenance.software.BusinessSoftwareDetailWindow'
    ]
    ,controller: 'maintenance.software.BusinessSoftwareController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,store: Ext.create('AM.store.maintenance.software.BusinessSoftwareStore')
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
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
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'typeCode'
                            ,text: '类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'hardwareModel'
                            ,text: '硬件型号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'softwareModel'
                            ,text: '软件型号'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("statusVO")?record.get("statusVO").displayName:'';
                            }
                            ,text: '状态'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'createDate'
                            ,text: '创建时间'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'expireDate'
                            ,text: '到期时间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetArea'
                            ,text: '所属区域'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetLocation'
                            ,text: '资产位置'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'intAccessAddr'
                            ,text: '内部访问地址'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'extAccessAddr'
                            ,text: '外部访问地址'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetManager'
                            ,text: '资产负责人'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'useManager'
                            ,text: '使用负责人'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'opsManager'
                            ,text: '维护负责人'
                            
                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'goliveDate'
                            ,text: '启用时间'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'custUsage'
                            ,text: '使用情况'
                            ,flex:1
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
                                iconCls: 'delete'
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
                                    ,iconCls: 'add'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: 'onAddButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'edit'
                                    ,text: '修改'
                                    ,listeners: {
                                        click: 'onEditButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'delete'
                                    ,text: '删除'
                                    ,listeners: {
                                        click: 'onDeleteButtonClick'
                                    }
                                }
                                ,'-'
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'名称'
                                    ,reference: 'nameField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'代码'
                                    ,reference: 'codeField'
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,'->'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '高级查询'
                                    ,listeners: {
                                        click: 'showSearchWindow'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '导出'
                                    ,listeners: {
                                        click: {
                                            fn: me.onExportButtonClick
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
                    }
                }
            ]
        });

        me.add({xtype:'maintenance.software.BusinessSoftwareAddWindow',reference:'mainAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.software.BusinessSoftwareEditWindow',reference:'mainEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.software.BusinessSoftwareSearchWindow',reference:'mainSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'maintenance.software.BusinessSoftwareDetailWindow',reference:'mainDetailWindow'})

        me.callParent(arguments);
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var panel = options.scope;

        var toolbar = this.down('toolbar')

        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var condition = {
                    name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
                    ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
        };
        this.store.proxy.extraParams = {searchCondition:condition};
        this.store.load({
            params:{
                page:0
            }
        });
    }

    ,onExportButtonClick: function(button, e, options) {

        var condition = this.store.proxy.extraParams;
        if(!condition){
            condition = {searchCondition:{}};
        }
        if (!Ext.fly('formFly')) {
            var frm = document.createElement('form');
            frm.id = 'formFly';
            frm.className = 'x-hidden';
            document.body.appendChild(frm);
        }
        console.log(condition)
        Ext.Ajax.request({
            disableCaching: true
            ,url: "software/businessSoftware/export"
            ,method: "POST"
            ,async: false  //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            ,params:condition
            ,isUpload: true
            ,form: Ext.fly('formFly')
        });

    }


    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('mainDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
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