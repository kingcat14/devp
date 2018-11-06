Ext.define('AM.view.platform.platform.application.AppController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.platform.application_AppController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var appAddWindow = me.lookupReference ('appAddWindow');
        var appGrid = appAddWindow.up('gridpanel');

        appGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var appSearchWindow = me.lookupReference ('appSearchWindow');
        var appGrid = appSearchWindow.up('gridpanel');
        appGrid.getStore().proxy.extraParams={searchCondition:appSearchWindow.getCondition()};
        appGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})