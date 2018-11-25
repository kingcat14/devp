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
                                // handler:function(button){
                                //     me.lookupReference("systemTreePanel").getStore().reload()
                                // }
								handler:'reloadTree'
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