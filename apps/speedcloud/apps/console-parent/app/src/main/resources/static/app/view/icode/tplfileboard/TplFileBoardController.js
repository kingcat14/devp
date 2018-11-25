Ext.define('AM.view.icode.tplfileboard.TplFileBoardController', {

	extend: 'Ext.app.ViewController'
	,requires: [

	]
	,alias: 'controller.icode.tplfileboard.TplFileBoardController'

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

		if("TPL_SET"==type) {
			//点击了模板集合
			this.lookupReference('tplFileBoardContentPanel').getLayout().setActiveItem(0);

			nodeId = record.get('objectId');

			AM.model.icode.tplfile.TplSet.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('tplFileBoardTplSetPanel').setModel(loadedRecord);
				}
			});
		}

		if("TPL_CODE"==type) {
			//点击了模板集合
			this.lookupReference('tplFileBoardContentPanel').getLayout().setActiveItem(1);
			nodeId = record.get('objectId');

			AM.model.icode.tplfile.TplCode.load(nodeId, {
				success: function (loadedRecord, operation) {
					me.lookupReference('tplFileBoardTplCodePanel').setModel(loadedRecord);
				}
			});
		}

	}

	,treeItemcontextmenu: function(view, record, node, index, e) {
		var me = this;

		e.stopEvent();
		var type = record.get('type');
		if('TPL_SET'==type){
			me.getView().tplSetContextMenu.showAt(e.getXY());
		}else if('TPL_TYPE'==type){
			me.getView().tplTypeContextMenu.showAt(e.getXY());
		}
		else if('TPL_CODE'==type){
			me.getView().tplCodeContextMenu.showAt(e.getXY());
		}

		return true;
	}

	,reloadTree:function(){
		var me = this;
		var treePanel = this.lookupReference('tplFileTreePanel')

		var path = treePanel.getSelectionModel( ).getLastSelected().getPath();
		console.log("path:"+path)
		//console.log(systemTreePanel.getSelectionModel( ).getLastSelected());

		//systemTreePanel.selectPath(path);

		treePanel.getView().getStore().load({
			callback:function(records, operation, success){
				if(success) {
					console.log("path:"+path)
					//systemTreePanel.expandPath(path);
					treePanel.selectPath(path);
					treePanel.expandPath(path);
				}
			}
		});
	}
})