Ext.define('AM.view.maintenance.asset.info.AssetTypeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.info_AssetTypeController'

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
        var assetTypeAddWindow = me.lookupReference ('assetTypeAddWindow');
        var assetTypeGrid = assetTypeAddWindow.up('gridpanel');

        assetTypeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var assetTypeSearchWindow = me.lookupReference ('assetTypeSearchWindow');
        var assetTypeGrid = assetTypeSearchWindow.up('gridpanel');
        assetTypeGrid.getStore().proxy.extraParams={searchCondition:assetTypeSearchWindow.getCondition()};
        assetTypeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})