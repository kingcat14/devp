Ext.define('AM.view.icode4.database.DatabaseTableInfoController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.database_DatabaseTableInfoController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
})