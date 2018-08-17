Ext.define('AM.view.maintenance.software.InfrastructuralSoftwarePanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'maintenance.software.InfrastructuralSoftwarePanel'
    ,title: '基础软件'
    ,requires: [
        'AM.view.maintenance.software.InfrastructuralSoftwareController'
        ,'AM.view.maintenance.software.InfrastructuralSoftwareAddWindow'
        ,'AM.view.maintenance.software.InfrastructuralSoftwareEditWindow'
        ,'AM.view.maintenance.software.InfrastructuralSoftwareSearchWindow'
        ,'AM.view.maintenance.software.InfrastructuralSoftwareDetailWindow'
    ]
    ,controller: 'software_InfrastructuralSoftwareController'
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [

                {
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
                    ,dataIndex: 'description'
                    ,text: '描述'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'typeCode'
                    ,text: '类型代码'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'typeName'
                    ,text: '类型名称'
                    
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
                    ,dataIndex: 'version'
                    ,text: '版本'
                    
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
                    ,dataIndex: 'assetProject'
                    ,text: '所属项目'
                    
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
                    ,dataIndex: 'acquisitionMode'
                    ,text: '获取方式'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'acquisitionDesc'
                    ,text: '获取方式说明'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'assetDept'
                    ,text: '归属部门'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'assetManager'
                    ,text: '资产负责人'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'useDept'
                    ,text: '使用部门'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'useManager'
                    ,text: '使用负责人'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'opsDept'
                    ,text: '维护部门'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'opsManager'
                    ,text: '维护负责人'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'bizLine'
                    ,text: '业务线'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'bizManager'
                    ,text: '业务代表'
                    
                }

                ,{
                    xtype: 'datecolumn'
                    ,format: 'Y-m-d'
                    ,dataIndex: 'goliveDate'
                    ,text: '启用时间'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'majorCust'
                    ,text: '主要客户'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'custManager'
                    ,text: '客户代表'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'custUsage'
                    ,text: '使用情况'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'notes'
                    ,text: '备注'
                    
                }

                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'acquisitionProvider'
                    ,text: '供应商'
                    
                }

                // ,{
                //     xtype: 'numbercolumn'
                //     ,dataIndex: 'recordState'
                //     ,format:'0,000'
                //     ,text: '记录状态'
                //     ,flex:1
                // }
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
                            ,emptyText:'请输入类型代码查询'
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
        me.add({xtype:'maintenance.software.InfrastructuralSoftwareAddWindow',reference:'infrastructuralSoftwareAddWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.software.InfrastructuralSoftwareEditWindow',reference:'infrastructuralSoftwareEditWindow',listeners:{saved:'reloadStore'}})
        me.add({xtype:'maintenance.software.InfrastructuralSoftwareSearchWindow',reference:'infrastructuralSoftwareSearchWindow',listeners:{saved:'doSearch'}})
        me.add({xtype:'maintenance.software.InfrastructuralSoftwareDetailWindow',reference:'infrastructuralSoftwareDetailWindow'})

        me.callParent(arguments);
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var panel = options.scope;

        var toolbar = this.down('toolbar')

        var simpleSearchField = panel.down("#simpleSearchField");

        var searchCondition = {typeCode:simpleSearchField.getValue()}

        this.store.proxy.extraParams = {searchCondition:searchCondition};
        this.store.load({
            params:{
                page:0
            }
        });
    }
	,onAddButtonClick: function(button, e, options) {

        var modelConfig = {}

        var record = Ext.create('AM.model.maintenance.software.InfrastructuralSoftware', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新基础软件');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改基础软件信息');
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
                        	Ext.MsgUtil.show('操作成功','删除基础软件成功!');
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
        editWindow.setTitle('修改基础软件信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('infrastructuralSoftwareAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('infrastructuralSoftwareEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showDetailWindow: function(model, targetComponent) {
        var me = this;
        var detailWindow = me.lookupReference('infrastructuralSoftwareDetailWindow');
        detailWindow.setModel(model);
        detailWindow.show(targetComponent);
        return detailWindow;
    }
    ,showSearchWindow: function(button, e, options) {
        var me = options.scope;

        var searchWindow = me.lookupReference('infrastructuralSoftwareSearchWindow');
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