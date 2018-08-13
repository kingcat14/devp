Ext.define('AM.view.platform.platform.application.ApplicationPasswordController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.platform.application_ApplicationPasswordController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var applicationPasswordAddWindow = me.lookupReference ('applicationPasswordAddWindow');
        var applicationPasswordGrid = applicationPasswordAddWindow.up('gridpanel');

        applicationPasswordGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var applicationPasswordSearchWindow = me.lookupReference ('applicationPasswordSearchWindow');
        var applicationPasswordGrid = applicationPasswordSearchWindow.up('gridpanel');
        applicationPasswordGrid.getStore().proxy.extraParams={searchCondition:applicationPasswordSearchWindow.getCondition()};
        applicationPasswordGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})