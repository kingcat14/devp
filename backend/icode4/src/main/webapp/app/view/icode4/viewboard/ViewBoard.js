Ext.define('AM.view.icode4.viewboard.ViewBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode4.viewboard.ViewBoard'
	,requires: [
		'AM.model.icode4.system.System'
		,'AM.model.icode4.system.Module'
		,'AM.model.icode4.view.ViewModel'
		,'AM.view.icode4.viewboard.ViewBoardController'
		,'AM.view.icode4.viewboard.ViewBoardViewModelPanel'
		,'AM.store.icode4.viewboard.SystemTreeStore'

	]
	,layout: {
		type: 'border'
	}
	,title: '页面面板'
	,store:null
	,bodyPadding:10
	,bodyStyle: 'background:white; padding:10px;'
	,controller: 'icode4.viewboard.ViewBoardController'

	,initComponent: function() {
		var me = this;

		var delProjectAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'x-fa fa-minus-circle red',
			text: '删除项目',
			disabled: false,
			handler: function(widget, event) {

				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				if('SYSTEM' == record.get('type')){

					var objectRecord = Ext.create('AM.model.icode4.system.System',{"id":record.get('objectId')});

					objectRecord.erase({

						success: function(objectRecord, operation) {
							Ext.MsgUtil.show('操作成功','删除项目成功');
							treePanel.getView().getStore().load({
								callback:function(records, operation, success){
									if(success) {
										Ext.MsgUtil.show('操作成功', '同步资源树成功');
									}
								}
							});
						}
					});
				}

			}
		});
		var addProjectAction = Ext.create('Ext.Action', {
			iconCls: 'x-fa fa-plus-circle',
			text: '新增项目',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var objectRecord = AM.model.icode4.system.System.create();

				me.lookupReference('systemBoardSystemPanel').setTitle("新增项目");
				me.lookupReference('systemBoardSystemPanel').setModel(objectRecord);
				me.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(0);
			}
		});
		var delModuleAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_delete',
			text: '删除模块',
			disabled: false,
			handler: function(widget, event) {
				var viewTreePanel = me.down('treepanel');

				var record = viewTreePanel.getSelectionModel().getLastSelected();
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}


				var path = viewTreePanel.getSelectionModel( ).getLastSelected().getPath();



				var objectRecord = Ext.create('AM.model.icode4.system.Module',{"id":record.get('objectId')});

				objectRecord.erase({

					success: function(objectRecord, operation) {
						Ext.MsgUtil.show('操作成功','删除节点成功');
						viewTreePanel.getView().getStore().load({
							callback:function(records, operation, success){
								if(success) {
									Ext.MsgUtil.show('操作成功', '同步资源树成功');
									console.log("path:"+path)
									viewTreePanel.expandPath(path);
								}
							}
						});
					}
				});


			}
		});
		var addModuleAction = Ext.create('Ext.Action', {
			iconCls: 'page_add',
			text: '新增模块',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}
				//默认点的是SYSTEM节点
				var systemId = record.get('id');
				var parentModuleId = null;
				if('MODULE' == record.get('type')){
					systemId = null;
					parentModuleId = record.get('id');
				}

				var codeTplRecord = AM.model.icode4.system.Module.create({systemId:systemId, parentModuleId: parentModuleId});
				me.lookupReference('systemBoardModulePanel').setTitle("新增领域");
				me.lookupReference('systemBoardModulePanel').setModel(codeTplRecord);
				me.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(1);

			}
		});

		var delRootModelAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_delete',
			text: '删除对象',
			disabled: false,
			handler: function(widget, event) {

				Ext.MessageBox.confirm('Confirm', '你确定要删除展现对象?', function(btn, text){
					var me = this;
					var treePanel = me.down('treepanel');


					var record = treePanel.getSelectionModel().getSelection()[0];
					if (!record) {
						Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
						return;
					}

					var type = record.get('type');
					if('VIEW_MODEL'!=type){
						return;
					}

					var parentNodeId = record.get("parentId");


					var objectRecord = Ext.create('AM.model.icode4.view.ViewModel', {"id":record.get('id')});

					objectRecord.erase({

						success: function(objectRecord, operation) {

							var node = treePanel.getView().getStore().getNodeById(record.get("id"))
							treePanel.getView().getStore().remove(node)

						}
					});
				}, me);

			}
		});
		var copyRootModelAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_copy',
			text: '复制对象',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var type = record.get('type');

				if('VIEW_MODEL'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "project/rootModel/"+objectId+"/copy",
						method: "PUT",
						async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

						success: function(response, opts) {
							Ext.MsgUtil.show('操作成功','复制节点成功');
							// treePanel.getView().getStore().load({
							//     callback:function(records, operation, success){
							//         if(success) {
							//             Ext.MsgUtil.show('操作成功', '同步资源树成功');
							//         }
							//     }
							// });

							var node = treePanel.getView().getStore().getNodeById(record.get("id"));
							//me.store.getNodeById("domain"+record.get("domainId")).removeAll();

							treePanel.getView().getStore().load({node:node.parentNode})
						}, //请求成功的回调函数
						failure: function() { alert("复制节点失败！"); }  // 请求失败的回调函数
					});
				}
			}
		});

		var projectContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delProjectAction
				,'-'
				,addModuleAction
			]
		});
		var moduleContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delModuleAction
				,addModuleAction
				,'-'
			]
		});
		var viewModelContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delRootModelAction
				,copyRootModelAction
			]
		});

		Ext.apply(me, {
			projectContextMenu:projectContextMenu
			,moduleContextMenu:moduleContextMenu
			,viewModelContextMenu:viewModelContextMenu
			,items: [
				{
				xtype: 'treepanel'
				, region: 'west'
				// ,frame:true
				, reference: 'viewTreePanel'
				, store: Ext.create('AM.store.icode4.viewboard.SystemTreeStore', {autoLoad:true})
				, split: true
				, collapsible: true
				, width: 250
				, title: '页面对象'
				, rootVisible: false
				, displayField: 'name'
				, dockedItems: [
					{
						xtype: 'toolbar'
						,dock: 'top'
						//,layout: 'fit'
						,items: [
							addProjectAction
							,delProjectAction]
					}
				]
				,viewConfig: {
					plugins: {
						ptype: 'treeviewdragdrop',
						appendOnly: true,
						allowCopy: false,
						copy: false,
						sortOnDrop: true,
						containerScroll: true,
						ddGroup:'systemTree-to-other'
						,dragZone:{
							displayField:'name'
							,onBeforeDrag:function (data, event) {
								var nodeType = event.record.get('type');
								if ('SYSTEM' == nodeType) {
									// Ext.MsgUtil.show('禁止操作', "'系统'节点不允许拖动!");
									return false;
								}
								if ('MODULE' == nodeType) {
									// Ext.MsgUtil.show('禁止操作', "'模块'节点不允许拖动!");
									return false;
								}
								return true;

							}
						}
						,dropZone:{
							onNodeOver : function(target, source, event, data){
								var sourceNode = this.view.getRecord(source.getDragData(event).records[0]);
								var sourceNodeType = sourceNode.get('type');
								var targetNode = this.view.getRecord(target);
								this.overRecord = targetNode;
								var targetNodeType = targetNode.get('type');
								this.valid = false;
								//模块只能放在系统或者其他模块下
								if ('MODULE' == sourceNodeType && (('SYSTEM' == targetNodeType)||'MODULE' == targetNodeType)) {
									this.valid = true;
									return Ext.dd.DropZone.prototype.dropAllowed;
								}
								//对象只能放在模块下
								if ('VIEW_MODEL' == sourceNodeType && 'MODULE' == targetNodeType) {
									this.valid = true;
									return Ext.dd.DropZone.prototype.dropAllowed;
								}
								return Ext.dd.DropZone.prototype.dropNotAllowed;
							}
							,getTargetFromEvent: function(e) {
								return e.getTarget(me.down('treepanel').getView().rowSelector);
							}
						}
					}


				}
				,listeners: {
					itemclick: 'treeItemClick'
					, itemcontextmenu: 'treeItemcontextmenu'

					,drop: function (node, data, overModel, dropPosition, eOpts) {


						var targetNodeType = overModel.get('type');
						var nodeType = data.records[0].get('type');
						var nodeId = data.records[0].get('id')

						if (nodeType == 'MODULE') {
							AM.model.icode4.system.Module.load(nodeId, {
								success: function (loadedRecord, operation) {
									if ('SYSTEM' == targetNodeType) {
										loadedRecord.set('systemId', overModel.get('id'))
										loadedRecord.set('parentModuleId', null);
										loadedRecord.save();
									}

									if ('MODULE' == targetNodeType) {
										loadedRecord.set('parentModuleId', overModel.get('id'));
										loadedRecord.save();
									}
								}
							});
						}

						if (nodeType == 'VIEW_MODEL') {
							AM.model.icode4.view.ViewModel.load(nodeId, {
								success: function (loadedRecord, operation) {
									loadedRecord.set('moduleId', overModel.get('id'));
									loadedRecord.save();
								}
							});
						}
					}

				}
			}
				,{
					xtype: 'treepanel'
					, region: 'east'
					// ,frame:true
					, reference: 'viewTreePanel'
					, store: Ext.create('AM.store.icode4.systemboard.SystemTreeStore', {autoLoad:true})
					, split: true
					, collapsible: true
					, width: 250
					, title: '领域对象'
					, rootVisible: false
					, displayField: 'name'
					,viewConfig: {
						plugins: {
						ptype: 'treeviewdragdrop',
						appendOnly: true,
						allowCopy: false,
						copy: false,
						sortOnDrop: true,
						containerScroll: true,
						ddGroup:'systemTree-to-other'
						,dragZone:{
							displayField:'name'
							,onBeforeDrag:function (data, event) {
								var nodeType = event.record.get('type');
								if ('SYSTEM' == nodeType) {
									// Ext.MsgUtil.show('禁止操作', "'系统'节点不允许拖动!");
									return false;
								}
								if ('MODULE' == nodeType) {
									// Ext.MsgUtil.show('禁止操作', "'模块'节点不允许拖动!");
									return false;
								}
								return true;

							}
						}
						,dropZone:{
							onNodeOver : function(target, source, event, data){
								return Ext.dd.DropZone.prototype.dropNotAllowed;
							}
							,getTargetFromEvent: function(e) {
								return e.getTarget(me.down('treepanel').getView().rowSelector);
							}
						}
					}
					}
				}
				,{
				xtype: 'panel'
				,layout:'card'
				 // ,frame:true
				,activeItem:0
				,reference: 'viewBoardContentPanel'
				,region: 'center'
				,items: [
					 Ext.create('AM.view.icode4.systemboard.SystemBoardSystemPanel', {
						 reference: 'viewBoardSystemPanel'
						 ,listeners:{
							 saved:'reloadTree'
						 }
					 })
					 ,Ext.create('AM.view.icode4.systemboard.SystemBoardModulePanel', {
						reference: 'viewBoardModulePanel'
						,listeners:{
							saved:'reloadTree'
						}
					 })
					 ,Ext.create('AM.view.icode4.viewboard.ViewBoardViewModelPanel', {
						reference: 'viewBoardViewModelPanel'
						,listeners:{
							saved:'reloadTree'
						}
					 })
				]
			}

			]
		});

		me.callParent(arguments);
	}

});