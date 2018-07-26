Ext.define('AM.view.deploy.ops.DevpOpsElementInfoController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsElementInfoController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsElementInfoAddWindow = me.lookupReference ('devpOpsElementInfoAddWindow');
        var devpOpsElementInfoGrid = devpOpsElementInfoAddWindow.up('gridpanel');

        devpOpsElementInfoGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsElementInfoSearchWindow = me.lookupReference ('devpOpsElementInfoSearchWindow');
        var devpOpsElementInfoGrid = devpOpsElementInfoSearchWindow.up('gridpanel');
        devpOpsElementInfoGrid.getStore().proxy.extraParams={searchCondition:devpOpsElementInfoSearchWindow.getCondition()};
        devpOpsElementInfoGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})