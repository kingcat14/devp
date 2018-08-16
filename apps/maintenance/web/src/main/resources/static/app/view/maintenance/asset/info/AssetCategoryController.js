Ext.define('AM.view.maintenance.asset.info.AssetCategoryController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.info_AssetCategoryController'

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
        var assetCategoryAddWindow = me.lookupReference ('assetCategoryAddWindow');
        var assetCategoryGrid = assetCategoryAddWindow.up('gridpanel');

        assetCategoryGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var assetCategorySearchWindow = me.lookupReference ('assetCategorySearchWindow');
        var assetCategoryGrid = assetCategorySearchWindow.up('gridpanel');
        assetCategoryGrid.getStore().proxy.extraParams={searchCondition:assetCategorySearchWindow.getCondition()};
        assetCategoryGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})