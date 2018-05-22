Ext.define('AM.view.product.product.DevpSysExtcmpController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.product_DevpSysExtcmpController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
})