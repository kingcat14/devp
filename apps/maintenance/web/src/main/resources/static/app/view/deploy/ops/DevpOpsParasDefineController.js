Ext.define('AM.view.deploy.ops.DevpOpsParasDefineController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsParasDefineController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsParasDefineAddWindow = me.lookupReference ('devpOpsParasDefineAddWindow');
        var devpOpsParasDefineGrid = devpOpsParasDefineAddWindow.up('gridpanel');

        devpOpsParasDefineGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsParasDefineSearchWindow = me.lookupReference ('devpOpsParasDefineSearchWindow');
        var devpOpsParasDefineGrid = devpOpsParasDefineSearchWindow.up('gridpanel');
        devpOpsParasDefineGrid.getStore().proxy.extraParams={searchCondition:devpOpsParasDefineSearchWindow.getCondition()};
        devpOpsParasDefineGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})