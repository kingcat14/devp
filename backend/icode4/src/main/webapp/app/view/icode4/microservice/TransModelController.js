Ext.define('AM.view.icode4.microservice.TransModelController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.microservice_TransModelController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
})