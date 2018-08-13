Ext.define('AM.view.platform.platform.application.ConfigAppCategoryController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.platform.application_ConfigAppCategoryController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var configAppCategoryAddWindow = me.lookupReference ('configAppCategoryAddWindow');
        var configAppCategoryGrid = configAppCategoryAddWindow.up('gridpanel');

        configAppCategoryGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var configAppCategorySearchWindow = me.lookupReference ('configAppCategorySearchWindow');
        var configAppCategoryGrid = configAppCategorySearchWindow.up('gridpanel');
        configAppCategoryGrid.getStore().proxy.extraParams={searchCondition:configAppCategorySearchWindow.getCondition()};
        configAppCategoryGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})