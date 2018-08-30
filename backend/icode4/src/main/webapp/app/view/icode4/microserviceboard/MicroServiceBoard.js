Ext.define('AM.view.icode4.microserviceboard.MicroServiceBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode4.microserviceboard.MicroServiceBoard'
	,requires: [
		'AM.model.icode4.system.System'
		,'AM.model.icode4.system.Module'
		,'AM.model.icode4.microservice.MicroService'
		,'AM.model.icode4.microservice.MicroServiceItfc'
		,'AM.view.icode4.microserviceboard.MicroServiceBoardController'
		,'AM.store.icode4.microserviceboard.SystemTreeStore'
		,'AM.view.icode4.microserviceboard.MicroServiceBoardMicroServicePanel'

	]
	,layout: {
		type: 'border'
	}
	,title: '系统服务面板'
	,store:null
	,bodyPadding:10
	,bodyStyle: 'background:white; padding:10px;'
	,controller: 'icode4.microserviceboard.MicroServiceBoardController'

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

				me.lookupReference('microServiceBoardSystemPanel').setTitle("新增项目");
				me.lookupReference('microServiceBoardSystemPanel').setModel(objectRecord);
				me.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(0);
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
				me.lookupReference('microServiceBoardModulePanel').setTitle("新增领域");
				me.lookupReference('microServiceBoardModulePanel').setModel(codeTplRecord);
				me.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(1);

			}
		});

		var addMicroServiceAction = Ext.create('Ext.Action', {
			iconCls: 'x-fa fa-plus-circle',
			text: '新增微服务',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}
				//默认点的是MODULE节点
				var moduleId = record.get('id');
				var moduleName = record.get('name');
				var objectRecord = AM.model.icode4.microservice.MicroService.create({moduleId:moduleId, moduleName:moduleName});

				me.lookupReference('microServiceBoardMicroServicePanel').setTitle("新增微服务");
				me.lookupReference('microServiceBoardMicroServicePanel').setModel(objectRecord);
				me.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(2);
			}
		});
		var delMicroServiceAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_delete',
			text: '删除微服务',
			disabled: false,
			handler: function(widget, event) {

				Ext.MessageBox.confirm('Confirm', '你确定要删除微服务?', function(btn, text){
					var me = this;
					var treePanel = me.down('treepanel');


					var record = treePanel.getSelectionModel().getSelection()[0];
					if (!record) {
						Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
						return;
					}

					var type = record.get('type');
					if('MICROSERVICE'!=type){
						return;
					}

					var path = treePanel.getSelectionModel( ).getLastSelected().getPath();


					var objectRecord = Ext.create('AM.model.icode4.microservice.MicroService', {"id":record.get('id')});

					objectRecord.erase({

						success: function(objectRecord, operation) {

							Ext.MsgUtil.show('操作成功','删除微服务成功');
							treePanel.getView().getStore().load({
								callback:function(records, operation, success){
									if(success) {
										Ext.MsgUtil.show('操作成功', '同步资源树成功');
										console.log("path:"+path)
										treePanel.expandPath(path);
									}
								}
							});
						}
					});
				}, me);

			}
		});
		var copyMicroServiceAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_copy',
			text: '复制微服务',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var type = record.get('type');

				if('MICROSERVICE'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "microservice/microService/"+objectId+"/copy",
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

		var addMicroServiceItfcAction = Ext.create('Ext.Action', {
			iconCls: 'x-fa fa-plus-circle',
			text: '新增接口',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}
				//默认点的是MICROSERVICE节点
				var microServiceId = record.get('id');
				var microServiceName = record.get('name');
				var objectRecord = AM.model.icode4.microservice.MicroServiceItfc.create({microServiceId:microServiceId, microServiceName:microServiceName});

				me.lookupReference('microserviceboardMicroServiceItfcPanel').setTitle("新增接口");
				me.lookupReference('microserviceboardMicroServiceItfcPanel').setModel(objectRecord);
				me.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(3);
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
				,addMicroServiceAction
			]
		});
		var microServiceModelContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delMicroServiceAction
				,copyMicroServiceAction
				,'-'
				,addMicroServiceItfcAction
			]
		});

		Ext.apply(me, {
			projectContextMenu:projectContextMenu
			,moduleContextMenu:moduleContextMenu
			,microServiceModelContextMenu:microServiceModelContextMenu
			,items: [{
				xtype: 'treepanel'
				, region: 'west'
				// ,frame:true
				, reference: 'viewTreePanel'
				, store: Ext.create('AM.store.icode4.microserviceboard.SystemTreeStore', {autoLoad:true})
				, split: true
				, collapsible: true
				, width: 250
				, title: '项目'
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
								if ('MICRO_SERVICE' == sourceNodeType && 'MODULE' == targetNodeType) {
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
							AM.model.icode4.microservice.MicroService.load(nodeId, {
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
				xtype: 'panel'
				,layout:'card'
				 // ,frame:true
				,activeItem:0
				,reference: 'microServiceBoardContentPanel'

				,region: 'center'
				,items: [
					 Ext.create('AM.view.icode4.systemboard.SystemBoardSystemPanel', {
						 reference: 'microServiceBoardSystemPanel'
						 ,listeners:{
							 saved:'reloadTree'
						 }
					 })
					 ,Ext.create('AM.view.icode4.systemboard.SystemBoardModulePanel', {
						reference: 'microServiceBoardModulePanel'
						,listeners:{
							saved:'reloadTree'
						}
					 })
					,Ext.create('AM.view.icode4.microserviceboard.MicroServiceBoardMicroServicePanel', {
						reference: 'microServiceBoardMicroServicePanel'
						,listeners:{
							saved:'reloadTree'
						}
					})
					,Ext.create('AM.view.icode4.microserviceboard.MicroServiceItfcPanel', {
						reference: 'microServiceBoardMicroServiceItfcPanel'
						,listeners:{
							saved:'reloadTree'
						}
					})

				]
			}]
		});

		me.callParent(arguments);
	}

});