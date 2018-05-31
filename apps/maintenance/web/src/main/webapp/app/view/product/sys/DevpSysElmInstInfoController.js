Ext.define('AM.view.product.sys.DevpSysElmInstInfoController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.sys_DevpSysElmInstInfoController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
})