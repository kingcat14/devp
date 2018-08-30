Ext.define('AM.view.icode4.systemboard.SystemBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode4.systemboard.SystemBoard'
	,requires: [
        'AM.model.icode4.system.Product'
		,'AM.model.icode4.system.System'
		,'AM.model.icode4.system.Module'
		,'AM.model.icode4.system.DomainModel'
		,'AM.model.icode4.system.DomainChildModel'
		,'AM.view.icode4.systemboard.SystemBoardController'
        ,'AM.view.icode4.systemboard.SystemBoardProductPanel'
		,'AM.view.icode4.systemboard.SystemBoardSystemPanel'
		,'AM.view.icode4.systemboard.SystemBoardModulePanel'
		,'AM.view.icode4.systemboard.SystemBoardDomainModelPanel'
		,'AM.view.icode4.systemboard.SystemBoardDomainChildModelPanel'
		,'AM.store.icode4.systemboard.SystemTreeStore'

	]
	,layout: {
		type: 'border'
	}
	,title: '领域模型面板'
	,store:null
	,bodyPadding:10
	,bodyStyle: 'background:white; padding:10px;'
	,controller: 'icode4.systemboard.SystemBoardController'

	,initComponent: function() {
		var me = this;

        var addProductAction = Ext.create('Ext.Action', {
            iconCls: 'x-fa fa-plus-circle',
            text: '新增产品',
            disabled: false,
            handler: function(widget, event) {
                var objectRecord = AM.model.icode4.system.Product.create();

                me.lookupReference('systemBoardProductPanel').setTitle("新增产品");
                me.lookupReference('systemBoardProductPanel').setModel(objectRecord);
                me.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(4);
            }
        });
        var delProductAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
            iconCls: 'x-fa fa-minus-circle red',
            text: '删除产品',
            disabled: false,
            handler: function(widget, event) {

                var treePanel = me.down('treepanel');

                var record = treePanel.getSelectionModel().getSelection()[0];
                if (!record) {
                    Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    return;
                }

                if('PRODUCT' == record.get('type')){

                    var objectRecord = Ext.create('AM.model.icode4.system.Product',{"id":record.get('objectId')});

                    objectRecord.erase({

                        success: function(objectRecord, operation) {
                            Ext.MsgUtil.show('操作成功','删除产品成功');
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


        var delSystemAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'x-fa fa-minus-circle red',
			text: '删除系统',
			disabled: false,
			handler: function(widget, event) {

				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

                if('SYSTEM' != record.get('type')){return;}

                Ext.MessageBox.confirm('Confirm', '你确定要删除根对象?', function(btn, text) {
                    console.log(btn)
                    if (btn != 'yes') {
                        return;
                    }

                    var objectRecord = Ext.create('AM.model.icode4.system.System',{"id":record.get('objectId')});

                    objectRecord.erase({

                        success: function(objectRecord, operation) {
                            Ext.MsgUtil.show('操作成功','删除系统成功');
                            treePanel.getView().getStore().load({
                                callback:function(records, operation, success){
                                    if(success) {
                                        Ext.MsgUtil.show('操作成功', '同步资源树成功');
                                    }
                                }
                            });
                        }
                    });
                })

			}
		});
		var addSystemAction = Ext.create('Ext.Action', {
			iconCls: 'x-fa fa-plus-circle',
			text: '新增系统',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
                var record = treePanel.getSelectionModel().getSelection()[0];
                if (!record) {
                    Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    return;
                }

                //默认点的是Product节点
                var productId = record.get('id');
				var objectRecord = AM.model.icode4.system.System.create({productId:productId});

				me.lookupReference('systemBoardSystemPanel').setTitle("新增系统");
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

                var systemTreePanel = me.down('treepanel');
                var record = systemTreePanel.getSelectionModel().getLastSelected();
                if (!record) {
                    Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    return;
                }

                Ext.MessageBox.confirm('Confirm', '你确定要删除该模块及其所包含的子模块、对象?', function(btn, text){
                    console.log(btn)
                    if(btn != 'yes'){
                        return;
                    }
                    var path = systemTreePanel.getSelectionModel( ).getLastSelected().getPath();

                    var objectRecord = Ext.create('AM.model.icode4.system.Module',{"id":record.get('objectId')});

                    objectRecord.erase({

                        success: function(objectRecord, operation) {
                            Ext.MsgUtil.show('操作成功','删除节点成功');
                            systemTreePanel.getView().getStore().load({
                                callback:function(records, operation, success){
                                    if(success) {
                                        Ext.MsgUtil.show('操作成功', '同步资源树成功');
                                        console.log("path:"+path)
                                        systemTreePanel.expandPath(path);
                                    }
                                }
                            });
                        }
                    });
                });
			}
		});
        var copyModuleAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
            iconCls: 'page_copy',
            text: '复制包',
            disabled: false,
            handler: function(widget, event) {
                var treePanel = me.down('treepanel');

                var record = treePanel.getSelection()[0];
                if (!record) {
                    Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    return;
                }

                var type = record.get('type');
                console.log(type)
                if('MODULE'==type){

                    var objectId = record.get('objectId');

                    Ext.Ajax.request({
                        url: "system/module/"+objectId+"/copy",
                        method: "PUT",
                        async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

                        success: function(response, opts) {
                            Ext.MsgUtil.show('操作成功','复制模块成功');

                            var node = treePanel.getView().getStore().getNodeById(record.get("id"));
                            //me.store.getNodeById("domain"+record.get("domainId")).removeAll();

                            treePanel.getView().getStore().load({node:node.parentNode})
                        }, //请求成功的回调函数
                        failure: function() { alert("复制模块失败！"); }  // 请求失败的回调函数
                    });
                }
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

				Ext.MessageBox.confirm('Confirm', '你确定要删除根对象?', function(btn, text){

					console.log(btn)
					if(btn != 'yes'){
						return;
					}
					var me = this;
					var treePanel = me.down('treepanel');


					var record = treePanel.getSelectionModel().getSelection()[0];
					if (!record) {
						Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
						return;
					}

					var type = record.get('type');
					if('DOMAIN_MODEL'!=type){
						return;
					}

					var parentNodeId = record.get("parentId");


					var objectRecord = Ext.create('AM.model.icode4.system.DomainModel', {"id":record.get('id')});

					objectRecord.erase({

						success: function(objectRecord, operation) {

							var node = treePanel.getView().getStore().getNodeById(record.get("id"))
							node.eachChild(function(child){
								node.removeChild(child);
							})
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
				console.log(type)
				if('DOMAIN_MODEL'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "system/domainModel/"+objectId+"/copy",
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
		var addRootModelAction = Ext.create('Ext.Action', {
			iconCls: 'page_add',
			text: '新增根对象',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				if("MODULE" != record.get('type') ){

					Ext.Msg.show({title: '操作失败', msg: '请选择模块', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;

				}


				var type = record.get('type');
				var moduleId = record.get('id');

				var rootModelRecord = AM.model.icode4.system.DomainModel.create({"moduleId" : moduleId, persist:true});

				me.lookupReference('systemBoardDomainModelPanel').setTitle("新增对象");
				me.lookupReference('systemBoardDomainModelPanel').setModel(rootModelRecord);
				me.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(2);

			}
		});

		var delChildModelAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_delete',
			text: '删除对象',
			disabled: false,
			handler: function(widget, event) {

				Ext.MessageBox.confirm('Confirm', '你确定要删除根对象?', function(btn, text){
					var me = this;
					var treePanel = me.down('treepanel');


					var record = treePanel.getSelectionModel().getSelection()[0];
					if (!record) {
						Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
						return;
					}

					var type = record.get('type');
					if('DOMAIN_CHILD_MODEL'!=type){
						return;
					}

					var objectRecord = Ext.create('AM.model.icode4.system.DomainChildModel', {"id":record.get('id')});

					objectRecord.erase({

						success: function(objectRecord, operation) {

							var node = treePanel.getView().getStore().getNodeById(record.get("id"))
							node.eachChild(function(child){
								node.removeChild(child);
							})
							treePanel.getView().getStore().remove(node)

						}
					});
				}, me);

			}
		});
		var addChildModelAction = Ext.create('Ext.Action', {
			iconCls: 'page_add',
			text: '新增子对象',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				if("DOMAIN_MODEL" != record.get('type') ){

					Ext.Msg.show({title: '操作失败', msg: '请选择领域对象', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;

				}


				var type = record.get('type');
				var moduleId = record.get('id');

				var rootModelRecord = AM.model.icode4.system.DomainChildModel.create({"domainModelId" : moduleId, "domainModelName":record.get('name')});

				me.lookupReference('systemBoardDomainChildModelPanel').setTitle("新增对象");
				me.lookupReference('systemBoardDomainChildModelPanel').setModel(rootModelRecord);
				me.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(3);

			}
		});


		var createViewModelAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_copy',
			text: '创建展现对象',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var type = record.get('type');

				if('DOMAIN_MODEL'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "view/viewModel/createFromDomainModel/"+objectId,
						method: "PUT",
						async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

						success: function(response, opts) {
							Ext.MsgUtil.show('操作成功','创建传输对象成功');
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
		var createTransModelAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_copy',
			text: '创建传输对象',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var type = record.get('type');

				if('DOMAIN_MODEL'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "microservice/transModel/createFromDomainModel/"+objectId,
						method: "PUT",
						async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

						success: function(response, opts) {
							Ext.MsgUtil.show('操作成功','创建展示对象成功');
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

		var createMicroServiceAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'connect',
			text: '创建微服务',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');

				var record = treePanel.getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var type = record.get('type');

				if('DOMAIN_MODEL'==type){

					var objectId = record.get('objectId');

					Ext.Ajax.request({
						url: "microservice/microService/createFromDomainModel/"+objectId,
						method: "PUT",
						async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

						success: function(response, opts) {
							Ext.MsgUtil.show('操作成功','创建微服务成功');
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

        var productContextMenu = Ext.create('Ext.menu.Menu', {
            items: [
                delProductAction
                ,'-'
                ,addSystemAction
            ]
        });
		var projectContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delSystemAction
				,'-'
				,addModuleAction
			]
		});
		var moduleContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delModuleAction
				,addModuleAction
                ,copyModuleAction
				,'-'
				,addRootModelAction
			]
		});
		var rootModelContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delRootModelAction
				,copyRootModelAction
				,'-'
				,addChildModelAction
				,'-'
				,createViewModelAction
				,'-'
				,createTransModelAction
				,'-'
				,createMicroServiceAction
			]
		});
		var childModelContextMenu = Ext.create('Ext.menu.Menu', {
			items: [

				delChildModelAction

			]
		});

		Ext.apply(me, {
            productContextMenu:productContextMenu,
			projectContextMenu:projectContextMenu,
			moduleContextMenu:moduleContextMenu,
			rootModelContextMenu:rootModelContextMenu,
			childModelContextMenu:childModelContextMenu,
			items: [{
				xtype: 'treepanel'
				, region: 'west'
				// ,frame:true
				, reference: 'systemTreePanel'
				, store: Ext.create('AM.store.icode4.systemboard.SystemTreeStore', {autoLoad:true})
				, split: true
				, collapsible: true
				, width: 250
				, title: '系统'
				, rootVisible: false
				, displayField: 'name'
				, dockedItems: [
					{
						xtype: 'toolbar'
						,dock: 'top'
						//,layout: 'fit'
						,items: [
							,addProductAction
							,delProductAction
							,{
                                xtype:'button',
                                //text:'重新加载',
                                iconCls: 'arrow_refresh',
                                handler:function(button){
                                    me.lookupReference("systemTreePanel").getStore().reload()
                                }
							}
						]
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
								//console.log(event);

								var nodeType = event.record.get('type');

								if ('PRODUCT' == nodeType) {
									if(event.button != 1 && event.button != 2) {
										//Ext.MsgUtil.show('禁止操作', "'产品'节点不允许拖动!");
									}
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

								console.log("sourceNode:"+sourceNode.get('id'))
								console.log("targetNode:"+targetNode.get('id'))
								if(sourceNode.get('id') == targetNode.get('id')){
									return Ext.dd.DropZone.prototype.dropNotAllowed;
								}

                                //属性只能放在对象下
                                if ('PROPERTY' == sourceNodeType && 'DOMAIN_MODEL' == targetNodeType) {
                                    this.valid = true;
                                    return Ext.dd.DropZone.prototype.dropAllowed;
                                }
								//模块只能放在系统或者其他模块下
								if ('MODULE' == sourceNodeType && (('SYSTEM' == targetNodeType)||'MODULE' == targetNodeType)) {
									this.valid = true;
									return Ext.dd.DropZone.prototype.dropAllowed;
								}
								//对象只能放在模块下
								if ('DOMAIN_MODEL' == sourceNodeType && 'MODULE' == targetNodeType) {
									this.valid = true;
									return Ext.dd.DropZone.prototype.dropAllowed;
								}
                                //系统只能放在产品下
                                if ('SYSTEM' == sourceNodeType && 'PRODUCT' == targetNodeType) {
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

						if(nodeId == overModel.get('id')){
							return;
						}

                        if (nodeType == 'PROPERTY'){
                            AM.model.icode4.system.DomainModelProperty.load(nodeId, {
                                success: function (loadedRecord, operation) {

                                    var newProperty = loadedRecord.copy(null);
                                    newProperty.set('domainModelId', overModel.get('objectId'))
                                    newProperty.save();
                                }
                            });
						}
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

						if (nodeType == 'DOMAIN_MODEL') {
							AM.model.icode4.system.DomainModel.load(nodeId, {
								success: function (loadedRecord, operation) {
									loadedRecord.set('moduleId', overModel.get('id'));
									loadedRecord.save();
								}
							});
						}
                        if (nodeType == 'SYSTEM') {
                            AM.model.icode4.system.System.load(nodeId, {
                                success: function (loadedRecord, operation) {
                                    loadedRecord.set('productId', overModel.get('id'));
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
				,reference: 'systemBoardContentPanel'
				,region: 'center'
				,items: [
					Ext.create('AM.view.icode4.systemboard.SystemBoardSystemPanel', {
						 reference: 'systemBoardSystemPanel'
						 ,listeners:{
							 saved:'reloadTree'
						 }
					 })
					,Ext.create('AM.view.icode4.systemboard.SystemBoardModulePanel', {
						reference: 'systemBoardModulePanel'
						,listeners:{
							saved:'reloadTree'
						}
					 })
					,Ext.create('AM.view.icode4.systemboard.SystemBoardDomainModelPanel', {
						reference: 'systemBoardDomainModelPanel'
						,listeners:{
							saved:'reloadTree'
						}
					 })
					,Ext.create('AM.view.icode4.systemboard.SystemBoardDomainChildModelPanel', {
							reference: 'systemBoardDomainChildModelPanel'
							,listeners:{
								saved:'reloadTree'
							}
						})
					,Ext.create('AM.view.icode4.systemboard.SystemBoardProductPanel', {
                            reference: 'systemBoardProductPanel'
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