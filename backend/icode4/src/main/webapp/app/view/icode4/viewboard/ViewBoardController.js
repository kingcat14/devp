Ext.define('AM.view.icode4.viewboard.ViewBoardController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.icode4.viewboard.ViewBoardController'

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
		if("VIEW_MODEL"==type) {
			//点击了模板集合
			this.lookupReference('viewBoardContentPanel').getLayout().setActiveItem(2);
			nodeId = record.get('objectId');

			AM.model.icode4.view.ViewModel.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('viewBoardViewModelPanel').setModel(loadedRecord);
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
		}else if('VIEW_MODEL'==type){
			me.getView().viewModelContextMenu.showAt(e.getXY());
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