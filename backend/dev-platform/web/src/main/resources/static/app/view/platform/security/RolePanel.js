
Ext.define('AM.view.platform.security.RolePanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.platformSecurityRolePanel',
	requires: [
		'AM.view.platform.security.RoleResourceTreeWindow'
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
							xtype:'textfield'
							,blankText:'请输入角色名称查询'
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

										if(!me.window){
											me.window = Ext.widget('platformSecurityRoleResourceTreeWindow');
										}
										view.getSelectionModel( ).select(record);
										var window = me.window;
										window.setRole(record);
										//window.show()
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
		var detailWindow = this.showDetailWindow(record, options);
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

		var searchCondition = {name:simpleSearchField.getValue()}

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

	,onPanelBeforeHide: function(abstractcomponent, options) {

	}

	,showDetailWindow: function(model, options) {
		var me = options.scope;

		var detailWindow = me.detailWindow;

		if(!detailWindow){

			detailWindow = Ext.create('AM.view.platform.security.RoleWindow', {store:me.down('gridpanel').getStore()});
			me.detailWindow = detailWindow;
		}


		detailWindow.setModel(model);

		detailWindow.show(options.src);

		detailWindow.setStore(this.store);

		return detailWindow;
	}

	,setStore: function(store) {
		this.down('grid').reconfigure(store);
		this.down('grid').down('pagingtoolbar').bindStore(store);

		this.store=store;
		store.load();
	}

});