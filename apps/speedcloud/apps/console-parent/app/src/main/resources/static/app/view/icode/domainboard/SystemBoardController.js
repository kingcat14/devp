Ext.define('AM.view.icode4.systemboard.SystemBoardController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.icode4.systemboard.SystemBoardController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,treeItemClick:function(treeView, record, item, index, e, options) {
		var me = this;
		var type = record.get('type');
		var nodeId = record.get('id');

        if("PRODUCT"==type) {
            //点击了模板集合
            this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(4);

            nodeId = record.get('objectId');

            AM.model.icode4.system.Product.load(nodeId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('systemBoardProductPanel').setModel(loadedRecord);
                }
            });
        }
		if("SYSTEM"==type) {
			//点击了模板集合
			this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(0);

			nodeId = record.get('objectId');

			AM.model.icode4.system.System.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('systemBoardSystemPanel').setModel(loadedRecord);
				}
			});
		}

		if("MODULE"==type) {
			//点击了模板集合
			this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(1);
			nodeId = record.get('objectId');

			AM.model.icode4.system.Module.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('systemBoardModulePanel').setModel(loadedRecord);
				}
			});
		}

		if("DOMAIN_MODEL"==type) {
			//点击了模板集合
			this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(2);
			nodeId = record.get('objectId');

			AM.model.icode4.system.DomainModel.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('systemBoardDomainModelPanel').setModel(loadedRecord);
				}
			});
		}

		if("DOMAIN_CHILD_MODEL"==type) {
			//点击了模板集合
			this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(3);
			nodeId = record.get('objectId');

			AM.model.icode4.system.DomainChildModel.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('systemBoardDomainChildModelPanel').setModel(loadedRecord);
				}
			});
		}

		if("PROPERTY"==type) {
			//点击了模板集合
			this.lookupReference('systemBoardContentPanel').getLayout().setActiveItem(2);
			nodeId = record.get('parentId');

			AM.model.icode4.system.DomainModelProperty.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('systemBoardDomainModelPanel').setModel(loadedRecord);
				}
			});
		}
	}

	,treeItemcontextmenu: function(view, record, node, index, e) {
		var me = this;

		e.stopEvent();
		var type = record.get('type');
        if('PRODUCT'==type){
            me.getView().productContextMenu.showAt(e.getXY());
        }else if('SYSTEM'==type){
			me.getView().projectContextMenu.showAt(e.getXY());
		}else if('MODULE'==type){
			me.getView().moduleContextMenu.showAt(e.getXY());
		}else if('DOMAIN_MODEL'==type){
			me.getView().rootModelContextMenu.showAt(e.getXY());
		}
		else if('DOMAIN_CHILD_MODEL'==type){
			me.getView().childModelContextMenu.showAt(e.getXY());
		}

		return true;
	}

	,reloadTree:function(){
		var me = this;
		var systemTreePanel = this.lookupReference('systemTreePanel')

		var selectedPath;
		var isExpanded = false;
		if(systemTreePanel.getSelectionModel( ).getLastSelected()) {
			selectedPath = systemTreePanel.getSelectionModel().getLastSelected().getPath();
			isExpanded = systemTreePanel.getSelectionModel().getLastSelected().isExpanded();
			console.log("selectedPath:" + selectedPath)
		}

		systemTreePanel.getView().getStore().load({
			callback:function(records, operation, success){
				if(success) {
					//systemTreePanel.expandPath(path);
					if(selectedPath){
						//console.log("selectedPath:" + selectedPath)
						systemTreePanel.selectPath(selectedPath);
						if(isExpanded) {
							systemTreePanel.expandPath(selectedPath);
						}
					}

				}
			}
		});
	}

	,addModule:function(){
		alert('add module')
	}
})