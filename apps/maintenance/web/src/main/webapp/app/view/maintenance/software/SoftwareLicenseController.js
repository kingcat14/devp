Ext.define('AM.view.maintenance.software.SoftwareLicenseController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.software_SoftwareLicenseController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var softwareLicenseAddWindow = me.lookupReference ('softwareLicenseAddWindow');
        var softwareLicenseGrid = softwareLicenseAddWindow.up('gridpanel');

        softwareLicenseGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var softwareLicenseSearchWindow = me.lookupReference ('softwareLicenseSearchWindow');
        var softwareLicenseGrid = softwareLicenseSearchWindow.up('gridpanel');
        softwareLicenseGrid.getStore().proxy.extraParams={searchCondition:softwareLicenseSearchWindow.getCondition()};
        softwareLicenseGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})