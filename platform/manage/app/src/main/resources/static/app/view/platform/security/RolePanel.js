
Ext.define('AM.view.platform.security.RolePanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.platformSecurityRolePanel',
	requires: [
		'AM.view.platform.security.RoleResourceTreeWindow'
		,'AM.view.platform.security.RoleEditWindow'
        ,'AM.view.platform.security.RoleWindow'
	],
	height: 250,
	width: 400,
	layout: {
		type: 'border'
	},
	title: '平台角色',
	initComponent: function() {
		var me = this;

		Ext.apply(me, {
			dockedItems: [
				{
					xtype: 'toolbar',
					dock: 'top',
					items: [
						{
							xtype: 'button',
							iconCls: 'add',
							text: '新增',
							listeners: {
								click: {
									fn: me.onAddButtonClick,
									scope: me
								}
							}
						},
						{
							xtype: 'button',
							iconCls: 'edit',
							text: '修改',
							listeners: {
								click: {
									fn: me.onEditButtonClick,
									scope: me
								}
							}
						},
						{
							xtype: 'button',
							iconCls: 'remove',
							text: '删除',
							listeners: {
								click: {
									fn: me.onDeleteButtonClick,
									scope: me
								}
							}
						}
						,'-'
                        ,{
                            xtype:'combobox'
                            ,emptyText:'点击选择租户'
                            ,store: Ext.create("AM.store.platform.platform.tenant.TenantStore", {autoLoad:true, pageSize: 1000})
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId:'simpleSearchTenantField'
							,queryMode:'local'
							,typeAhead: true
							,forceSelection: true

                        }
                        ,{
                            xtype:'combobox'
                            ,emptyText:'点击选择应用'
                            ,store: Ext.create("AM.store.platform.platform.application.AppStore", {autoLoad:true, pageSize: 1000})
                            ,displayField:'name'
                            ,valueField:'code'
                            ,itemId:'simpleSearchAppField'
                            ,queryMode:'local'
                            ,typeAhead: true
                            ,forceSelection: true

                        }
						,{
							xtype:'textfield'
							,emptyText:'请输入角色名称查询'
							,itemId:'simpleSearchField'

						}
						,{
							xtype: 'button',
							iconCls: 'search',
							text: '查询',
							listeners: {
								click: {
									//fn: me.onSearchButtonClick,
									fn:me.onSimpleSearchButtonClick,
									scope: me
								}
							}
						}
					]
				}
			],
			items: [
				{
					xtype: 'gridpanel',
					region: 'center',
					itemId: 'roleGrid',
					columnLines: true,
					columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'tenantId'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("tenantVO")?record.get("tenantVO").name:'';
                            }
                            ,text: '所属租户'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'appCode'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("appVO")?record.get("appVO").name:'';
                            }
                            ,text: '所属应用'

                        }
                        ,{
							xtype: 'gridcolumn',
							dataIndex: 'name',
							text: '角色名'
						}
						,{
							xtype: 'gridcolumn',
							dataIndex: 'description',
							flex:1,
							text: '角色描述'
						}
						,{
							xtype: 'actioncolumn',
							items: [
								{
									handler: function(view, rowIndex, colIndex, item, e, record, row) {

										if(!me.roleResourceTreeWindow){
											me.roleResourceTreeWindow = Ext.widget('platformSecurityRoleResourceTreeWindow');
										}
										view.getSelectionModel( ).select(record);
										var window = me.roleResourceTreeWindow;
										window.setRole(record);
										window.show()
									},
									iconCls: 'link',
									tooltip: '修改角色资源'
								}
							]
						}
					],
					viewConfig: {

					},
					dockedItems: [
						{
							xtype: 'pagingtoolbar',
							dock: 'bottom',
							width: 360,
							displayInfo: true
						}
					],
					selModel: Ext.create('Ext.selection.CheckboxModel', {

					}),
					listeners: {
						itemdblclick: {
							fn: me.onRoleGridItemDblClick,
							scope: me
						}
					}
				}
			],
			listeners: {
				beforehide: {
					fn: me.onPanelBeforeHide,
					scope: me
				}
			}
		});

		me.callParent(arguments);
	},

	onAddButtonClick: function(button, e, options) {

		var record = Ext.create('AM.model.platform.security.Role', {});

		options.src = button;
		var detailWindow = this.showAddWindow(record, options);
		detailWindow.setTitle('添加新角色');
	},

	onEditButtonClick: function(button, e, options) {
		var panel = options.scope;
		options.src=button;
		var grid = panel.down("#roleGrid");
		var selections = grid.getSelectionModel( ).getSelection( );
		//var record = grid.getSelectionModel( ).getLastSelected( );

		if(selections.length<=0){
			Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
			return;
		}

		var record = selections[0];
		var detailWindow = panel.showDetailWindow(record,options);
		detailWindow.setTitle('修改角色信息');
	},

	onDeleteButtonClick: function(button, e, options) {
		var panel = options.scope;
		var grid = panel.down("#roleGrid");
		var selections = grid.getSelectionModel( ).getSelection( );
		grid.getStore().remove(selections);
		grid.getStore().sync(
			{
				success:function(batch,options){

					var store = options.scope;
					var count = store.getCount();

					var targetPage = count<=0?store.currentPage-1:store.currentPage;
					targetPage = targetPage <=0 ? 1 :targetPage;

					store.loadPage(targetPage,{
						scope: this,
						callback: function(records, operation, success) {
							if(!success)
								Ext.Msg.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
							else
								Ext.MsgUtil.show('操作成功','删除信息成功!');
						}
					});
				},
				scope:grid.getStore()
			}
		);
	}

	,onSimpleSearchButtonClick: function(button, e, options) {
		var panel = options.scope;

		var toolbar = this.down('toolbar')

		var simpleSearchField = panel.down("#simpleSearchField");
        var simpleSearchTenantField = panel.down("#simpleSearchTenantField");
        var simpleSearchAppField = panel.down("#simpleSearchAppField");
		var searchCondition = {name:simpleSearchField.getValue()
            ,tenantId:simpleSearchTenantField.getValue()
            ,appCode:simpleSearchAppField.getValue()
		}

		this.store.proxy.extraParams={searchCondition:searchCondition};
		this.store.load({
			params:{
				start:0,
				page:0
			}
		});
	}

	,onRoleGridItemDblClick: function(tablepanel, record, item, index, e, options) {
		var me = options.scope;
		options.src=item;
		me.showDetailWindow(record,options);
	}


	,showDetailWindow: function(model, options) {
		var me = options.scope;

		var detailWindow = me.detailWindow;

		if(!detailWindow){

			detailWindow = Ext.create('AM.view.platform.security.RoleEditWindow', {store:me.down('gridpanel').getStore()});
			me.detailWindow = detailWindow;
		}


		detailWindow.setModel(model);

		detailWindow.show(options.src);

		detailWindow.setStore(this.store);

		return detailWindow;
	}
    ,showAddWindow: function(model, options) {
        var me = options.scope;

        var addWindow = me.addWindow;

        if(!addWindow){

            addWindow = Ext.create('AM.view.platform.security.RoleWindow', {store:me.down('gridpanel').getStore()});
            me.addWindow = addWindow;
        }


        addWindow.setModel(model);

        addWindow.show(options.src);

        addWindow.setStore(this.store);

        return addWindow;
    }

	,setStore: function(store) {
		this.down('grid').reconfigure(store);
		this.down('grid').down('pagingtoolbar').bindStore(store);

		this.store=store;
		store.load();
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
        if(me.roleResourceTreeWindow){
            me.roleResourceTreeWindow.hide();
        }

    }


});