Ext.define('AM.view.deploy.ops.DevpOpsAssetCmdbController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsAssetCmdbController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsAssetCmdbAddWindow = me.lookupReference ('devpOpsAssetCmdbAddWindow');
        var devpOpsAssetCmdbGrid = devpOpsAssetCmdbAddWindow.up('gridpanel');

        devpOpsAssetCmdbGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsAssetCmdbSearchWindow = me.lookupReference ('devpOpsAssetCmdbSearchWindow');
        var devpOpsAssetCmdbGrid = devpOpsAssetCmdbSearchWindow.up('gridpanel');
        devpOpsAssetCmdbGrid.getStore().proxy.extraParams={searchCondition:devpOpsAssetCmdbSearchWindow.getCondition()};
        devpOpsAssetCmdbGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})