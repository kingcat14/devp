Ext.define('AM.view.maintenance.hardware.MachineController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.hardware_MachineController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		if(detailTabPanel) {
            detailTabPanel.expand();
        }

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var machineAddWindow = me.lookupReference ('machineAddWindow');
        var machineGrid = machineAddWindow.up('gridpanel');

        machineGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var machineSearchWindow = me.lookupReference ('machineSearchWindow');
        var machineGrid = machineSearchWindow.up('gridpanel');
        machineGrid.getStore().proxy.extraParams={searchCondition:machineSearchWindow.getCondition()};
        machineGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})