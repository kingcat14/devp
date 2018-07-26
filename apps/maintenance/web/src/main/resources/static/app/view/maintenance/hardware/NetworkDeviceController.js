Ext.define('AM.view.maintenance.hardware.NetworkDeviceController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.hardware_NetworkDeviceController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var networkDeviceAddWindow = me.lookupReference ('networkDeviceAddWindow');
        var networkDeviceGrid = networkDeviceAddWindow.up('gridpanel');

        networkDeviceGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var networkDeviceSearchWindow = me.lookupReference ('networkDeviceSearchWindow');
        var networkDeviceGrid = networkDeviceSearchWindow.up('gridpanel');
        networkDeviceGrid.getStore().proxy.extraParams={searchCondition:networkDeviceSearchWindow.getCondition()};
        networkDeviceGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})