Ext.define('AM.view.icode4.microserviceboard.MicroServiceBoardController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.icode4.microserviceboard.MicroServiceBoardController'

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
		console.log(type);
		if("SYSTEM"==type) {
			//点击了模板集合
			this.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(0);

			nodeId = record.get('objectId');

			AM.model.icode4.system.System.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('microServiceBoardSystemPanel').setModel(loadedRecord);
				}
			});
		}

		if("MODULE"==type) {
			//点击了模板集合
			this.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(1);
			nodeId = record.get('objectId');

			AM.model.icode4.system.Module.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('microServiceBoardModulePanel').setModel(loadedRecord);
				}
			});
		}
		if("MICROSERVICE"==type) {
			//点击了模板集合
			this.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(2);
			nodeId = record.get('objectId');

			AM.model.icode4.microservice.MicroService.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('microServiceBoardMicroServicePanel').setModel(loadedRecord);
				}
			});
		}
		if("MICROSERVICEITFC"==type) {
			//点击了模板集合
			this.lookupReference('microServiceBoardContentPanel').getLayout().setActiveItem(3);
			nodeId = record.get('objectId');

			var  parentId = record.get('parentId');
			console.log(record);


			var node  = record;
			var nodeType = node.get('type');
			while(nodeType != 'MODULE'){
				node = treeView.getStore().getById(node.get('parentId'))
				nodeType = node.get('type');
			}
			console.log("nodeType:"+nodeType);
			var moduleId = node.get('id')

			AM.model.icode4.microservice.MicroServiceItfc.load(nodeId, {
				success: function (loadedRecord, operation) {
					loadedRecord.set("moduleId", moduleId);
					me.lookupReference('microServiceBoardMicroServiceItfcPanel').setModel(loadedRecord);
				}
			});
		}



	}

	,treeItemcontextmenu: function(view, record, node, index, e) {
		var me = this;

		e.stopEvent();
		var type = record.get('type');
		if('SYSTEM'==type){
			me.getView().projectContextMenu.showAt(e.getXY());
		}else if('MODULE'==type){
			me.getView().moduleContextMenu.showAt(e.getXY());
		}else if('MICROSERVICE'==type){
			me.getView().microServiceModelContextMenu.showAt(e.getXY());
		}

		return true;
	}

	,reloadTree:function(){
		var me = this;
		var viewTreePanel = this.lookupReference('viewTreePanel')

		var path = viewTreePanel.getSelectionModel( ).getLastSelected().getPath();
		console.log("path:"+path)


		viewTreePanel.getView().getStore().load({
			callback:function(records, operation, success){
				if(success) {
					console.log("path:"+path)
					viewTreePanel.selectPath(path);
				}
			}
		});
	}
})