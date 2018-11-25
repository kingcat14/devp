Ext.define('AM.view.icode.tplfileboard.TplFileBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode.tplfileboard.TplFileBoard'
	,requires: [
		'AM.view.icode.tplfileboard.TplFileBoardController'
		,'AM.view.icode.tplfileboard.TplFileBoardTplSetPanel'
		,'AM.view.icode.tplfileboard.TplFileBoardTplCodePanel'
		,'AM.store.icode.tplfile.TplFileTreeStore'

	]
	,layout: {
		type: 'border'
	}
	,title: '模板文件面板'
	,store:null
    , bodyCls: 'app-dashboard'
    , bodyPadding: '5'
	// ,bodyStyle: 'background:white; padding:10px;'
	,controller: 'icode.tplfileboard.TplFileBoardController'

	,initComponent: function() {
		var me = this;

		var delTplSetAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'x-fa fa-minus-circle red',
			text: '删除模板集合',
			disabled: false,
			handler: function(widget, event) {

				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				if('TPL_SET' == record.get('type')){

					var objectRecord = Ext.create('AM.model.icode.tplfile.TplSet',{"id":record.get('objectId')});

					objectRecord.erase({

						success: function(objectRecord, operation) {
							Ext.MsgUtil.show('操作成功','删除模板集合成功');
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
		var addTplSetAction = Ext.create('Ext.Action', {
			iconCls: 'x-fa fa-plus-circle',
			text: '新增模板集合',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var objectRecord = AM.model.icode.tplfile.TplSet.create();

				me.lookupReference('tplFileBoardTplSetPanel').setTitle("新增模板集合");
				me.lookupReference('tplFileBoardTplSetPanel').setModel(objectRecord);
				me.lookupReference('tplFileBoardContentPanel').getLayout().setActiveItem(0);
			}
		});
		var copyTplSetAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'folder_lightbulb',
			text: '复制模板集',
			disabled: false,
			handler: function(widget, event) {

				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				if('TPL_SET'==record.get('type')){
					var tplSetRecord = Ext.create('AM.model.icode.tplfile.TplSet',{"id":record.get('objectId')});
					var objectId = tplSetRecord.get('id');
					Ext.Ajax.request({
						url: "/icode/tplfile/tplset/"+objectId+"/copy",
						method: "PUT",
						async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

						success: function(response, opts) {
							Ext.MsgUtil.show('操作成功','复制模板集合成功');
							treePanel.getView().getStore().load({
								callback:function(records, operation, success){
									if(success) {
										Ext.MsgUtil.show('操作成功', '同步资源树成功');
									}
								}
							});
						}, //请求成功的回调函数
						failure: function() { alert("复制模板集合失败！"); }  // 请求失败的回调函数
					});
				}

			}
		});

		var delTplCodeAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
			iconCls: 'page_delete',
			text: '删除模板',
			disabled: false,
			handler: function(widget, event) {

				var treePanel = me.down('treepanel');

				var record = treePanel.getSelectionModel().getLastSelected();
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}

				var path = treePanel.getSelectionModel( ).getLastSelected().getPath();

				var objectRecord = Ext.create('AM.model.icode.tplfile.TplCode',{"id":record.get('objectId')});

				objectRecord.erase({

					success: function(objectRecord, operation) {
						Ext.MsgUtil.show('操作成功','删除模板成功');
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

			}
		});
		var addTplCodeAction = Ext.create('Ext.Action', {
			iconCls: 'page_add',
			text: '新增模板',
			disabled: false,
			handler: function(widget, event) {
				var treePanel = me.down('treepanel');
				var record = treePanel.getSelectionModel().getSelection()[0];
				if (!record) {
					Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					return;
				}
				//默认点的是TPL_SET节点
				var tplSetId = record.get('objectId');
				var type = null;
				if('TPL_TYPE' == record.get('filePath')){
					type = record.get('tpl')
				}

				var codeTplRecord = AM.model.icode.tplfile.TplCode.create({tplSet:tplSetId, type: type, name:'asdf'});
				me.lookupReference('tplFileBoardTplCodePanel').setTitle("新增模板");
				me.lookupReference('tplFileBoardTplCodePanel').setModel(codeTplRecord);
				me.lookupReference('tplFileBoardContentPanel').getLayout().setActiveItem(1);

			}
		});
        var copyTplCodeAction = Ext.create('Ext.Action', {
//            icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
            iconCls: 'folder_lightbulb',
            text: '复制模板',
            disabled: false,
            handler: function(widget, event) {

                var treePanel = me.down('treepanel');

                var record = treePanel.getSelectionModel().getSelection()[0];
                if (!record) {
                    Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                    return;
                }

                if('TPL_CODE'==record.get('type')){
                    var tplSetRecord = Ext.create('AM.model.icode.tplfile.TplCode',{"id":record.get('objectId')});
                    var objectId = tplSetRecord.get('id');
                    Ext.Ajax.request({
                        url: "/icode/tplfile/tplcode/"+objectId+"/copy",
                        method: "PUT",
                        async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)

                        success: function(response, opts) {
                            Ext.MsgUtil.show('操作成功','复制模板成功');

                            me.getController().reloadTree();
                        } //请求成功的回调函数
                        ,failure: function() { alert("复制模板集合失败！"); }  // 请求失败的回调函数
                    });
                }

            }
        });


		var tplSetContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delTplSetAction
				,'-'
				,addTplCodeAction
				,copyTplSetAction
			]
		});
		var tplTypeContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				addTplCodeAction
			]
		});
		var tplCodeContextMenu = Ext.create('Ext.menu.Menu', {
			items: [
				delTplCodeAction
				,'-'
				,copyTplCodeAction
			]
		});



        Ext.apply(me, {
			tplSetContextMenu:tplSetContextMenu
			,tplCodeContextMenu:tplCodeContextMenu
			,tplTypeContextMenu:tplTypeContextMenu
			,items: [
				{
				xtype: 'treepanel'
				, region: 'west'
				,frame:true
				,border: false
				, reference: 'tplFileTreePanel'
				, store: Ext.create('AM.store.icode.tplfile.TplFileTreeStore', {autoLoad:true, sorters: 'name'})
				, split: true
				, collapsible: true
				, width: 250
				, title: '模板'
				, rootVisible: false
				, displayField: 'name'
				, dockedItems: [
					{
						xtype: 'toolbar'
						,dock: 'top'
						//,layout: 'fit'
						,items: [
							addTplSetAction
							,delTplSetAction]
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
						ddGroup:'tplFileTree-to-other'
						,dragZone:{
							displayField:'name'
							,onBeforeDrag:function (data, event) {
								var nodeType = event.record.get('type');
								if ('TPL_SET' == nodeType) {
									//Ext.MsgUtil.show('禁止操作', "'系统'节点不允许拖动!");
									return false;
								}
								if ('TPL_TYPE' == nodeType) {
									//Ext.MsgUtil.show('禁止操作', "该节点不允许拖动!");
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
								if ('TPL_CODE' == sourceNodeType && 'TPL_TYPE' == targetNodeType) {
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
						var nodeId = data.records[0].get('id');

						console.log(overModel)

						if (nodeType == 'TPL_CODE' && targetNodeType == 'TPL_TYPE') {
							AM.model.icode.tplfile.TplCode.load(nodeId, {
								success: function (loadedRecord, operation) {

									var id = overModel.get("id");
									// var type = id.split("_")[1];
									var tplSetId = id.split("_")[0];
									var filePath = overModel.get('objectId')

									loadedRecord.set('tplSet', tplSetId);
                                    loadedRecord.set('filePath', filePath);
									// loadedRecord.set('type', type);
									loadedRecord.save({
										success:function(){
                                            me.getController().reloadTree();
										}
									});
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
				,reference: 'tplFileBoardContentPanel'
				,region: 'center'
				,items: [
					 Ext.create('AM.view.icode.tplfileboard.TplFileBoardTplSetPanel', {
						 reference: 'tplFileBoardTplSetPanel'
						 ,listeners:{
							 saved:'reloadTree'
						 }
					 })
					 ,Ext.create('AM.view.icode.tplfileboard.TplFileBoardTplCodePanel', {
						reference: 'tplFileBoardTplCodePanel'
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