
Ext.define('AM.view.platform.security.ResourcePanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.platformSecurityResourcePanel',

	height: 250,
	layout: {
		type: 'border'
	},
	title: '系统资源',

	initComponent: function() {
		var me = this;

		Ext.apply(me, {
			items: [
				{
					xtype: 'treepanel'
					,region: 'west'
					,split: true
					,width: 250
					,title: '资源树'
                    ,rootVisible: false
                    ,store: Ext.create('AM.store.platform.security.AllResourceTreeStore')
					,displayField: 'name'
					,viewConfig: {

					}
					,listeners: {
						itemclick: {
							fn: me.onTreepanelItemClick
							,scope: me
						}
					}
				},
				{
					xtype: 'gridpanel',
					region: 'center',
					title: '子节点',
					dockedItems: [
						{
							xtype: 'toolbar',
							dock: 'top',
							items: [
								{
									xtype: 'button'
									,iconCls: 'add'
									,text: '新增'
									,handler: function(button, event) {

										var grid = this.up('grid');

										var rowEditing = grid.getPlugin('rowediting');
										rowEditing.cancelEdit();

										var tree = grid.previousSibling('treepanel');
										var parentResource = tree.getSelectionModel( ).getLastSelected();
										var parentCode = parentResource?parentResource.get('code'):-1;
                                        var appCode = parentResource?parentResource.get('appCode'):-1;

                                        var code = null;
                                        var orderIndex = 1;
                                        for(var i = 1; i < 999;i++){
                                        	code = Ext.Number.from(parentCode, 0) * 1000 + i

                                            var record = grid.getStore().findRecord('code', code);
                                        	if(!record){
                                                orderIndex = i;
                                        		break;
											}
										}

										// Create a model instance
										var r = Ext.create('AM.model.platform.security.Resource', {
											name: '<resource_name>'
                                            ,parentCode: parentCode
                                            ,appCode: appCode
											,orderIndex: 1
											,type:'function'
											,code:code
											,orderIndex:orderIndex
											// ,hidden:false

										});

										r.save()

										grid.getStore().insert(grid.getStore().getCount(), r);
										rowEditing.startEdit(r, 0);
                                        //grid.getStore().sync();
									}
								},
								{
									xtype: 'button',
									handler: function(button, event) {
										var grid = this.up('grid');

										var selected = grid.getSelectionModel( ).getSelection( );
										grid.getStore().remove(selected);
										grid.getStore().sync({
											success:function(){
												Ext.MsgUtil.show('操作成功','删除资源成功!');

												var tree = grid.previousSibling('treepanel');

												var path = tree.getSelectionModel( ).getLastSelected().getPath();
												tree.getStore().load({
													callback:function(records, operation, success){
														if(success){
															Ext.MsgUtil.show('操作成功','同步资源树成功');
															tree.selectPath(path);
														}

													}
												});
											}
										});

									},
									iconCls: 'remove',
									text: '删除'
								}
							]
						}
					],
					columns: [
						{
							xtype: 'gridcolumn'
							,dataIndex: 'code'
							,text: 'CODE'
							,width:150
							,editor: {
								xtype: 'numberfield'
							}
						},
						{
							xtype: 'gridcolumn'
							,dataIndex: 'name'
							,text: '资源名'
                            ,width:150
							,editor: {
								xtype: 'textfield'
							}
						},
						{
							xtype: 'gridcolumn',
							dataIndex: 'url',
							flex: 1,
							text: '资源链接',
							editor: {
								xtype: 'textfield'
							}
						},
						{
							xtype: 'numbercolumn',
							dataIndex: 'orderIndex',
							header: '排序',
							format: '0',
							editor: {
								xtype: 'numberfield'
							}
						}
						,{
							header: '资源类型',
							dataIndex: 'type',
							editor: new Ext.form.field.ComboBox({
								typeAhead: true,
								triggerAction: 'all',
								store: [
									['function','功能'],
									['module','模块']
								]
								,value : '1'
							})
							,renderer: function(value){
								if (value == 'function') {
									return '功能';
								}else if (value == 'module') {
									return '模块';
								}else{
									return '';
								}
							}
						}
                        ,{
                            xtype: 'booleancolumn'
                            ,text: '隐藏'
                            ,dataIndex: 'hidden'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :''
                            ,editor: new Ext.form.field.ComboBox({
                                typeAhead: false,
                                triggerAction: 'all',
                                store: [
                                    [true,'是'],
                                    [false,'否']
                                ]
                                ,value : '1'
                            })
                        }
						,{
							xtype: 'gridcolumn',
							dataIndex: 'parentCode',
							text: '父节点'
						}
					],
					viewConfig: {

					},
					selModel: Ext.create('Ext.selection.CheckboxModel', {

					}),
					plugins: [
						Ext.create('Ext.grid.plugin.RowEditing', {
							ptype: 'rowediting',
							pluginId: 'rowediting'
						})
					],
					listeners: {
						edit: {
							fn: me.onGridpanelEdit,
							scope: me
						},
						canceledit: {
							fn: me.onGridpanelCanceledit,
							scope: me
						}
					}
				}
			]
		});

		me.callParent(arguments);
	},

	onTreepanelItemClick: function(tablepanel, record, item, index, e, options) {
		if('function'==record.get('type')){
			Ext.MessageBox.alert('','该资源没有子节点');
			return;
		}

		var parentCode = record.get('code');
        var appCode = record.get('appCode');
		var condition = Ext.JSON.encode({parentCode:parentCode, appCode:appCode});
		//this.resourceStore.proxy.setUrl ("platform/security/resource/rest/"+parentId);
		// this.resourceStore.proxy.extraParams={condition:condition};
		//this.resourceStore.proxy.extraParams={searchCondition:{parentCode:parentCode, appCode:appCode}};
		this.resourceStore.load({
            params:{
                searchCondition:{parentCode:parentCode, appCode:appCode}
			}

		});
		this.down('grid').setTitle(record.get('name')+'的直接子节点');
	},

	onGridpanelEdit: function(editor, e, options) {
        var me = this;

        me.resourceStore.sync({
            success:function(){
                Ext.MsgUtil.show('操作成功','保存资源信息成功');

                var tree = me.down('treepanel');

                var path = tree.getSelectionModel( ).getLastSelected().getPath();

                tree.getStore().load({
                    callback:function(records, operation, success){
                        if(success){
                            Ext.MsgUtil.show('操作成功','同步资源树成功');

                            tree.selectPath(path);
                        }

                    }
                });
            }
        });



	},

	onGridpanelCanceledit: function(editor, e, options) {

		if(e.record.phantom){
			this.resourceStore.remove(e.record);
		}
	},

	setResourceStore: function(store) {
		this.resourceStore = store;
        store.getSorters().add('orderIndex')
		this.down('grid').reconfigure(store);
	}



});