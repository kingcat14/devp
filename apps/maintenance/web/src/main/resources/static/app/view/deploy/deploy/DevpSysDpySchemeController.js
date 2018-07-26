Ext.define('AM.view.deploy.deploy.DevpSysDpySchemeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.deploy_DevpSysDpySchemeController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysDpySchemeAddWindow = me.lookupReference ('devpSysDpySchemeAddWindow');
        var devpSysDpySchemeGrid = devpSysDpySchemeAddWindow.up('gridpanel');

        devpSysDpySchemeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysDpySchemeSearchWindow = me.lookupReference ('devpSysDpySchemeSearchWindow');
        var devpSysDpySchemeGrid = devpSysDpySchemeSearchWindow.up('gridpanel');
        devpSysDpySchemeGrid.getStore().proxy.extraParams={searchCondition:devpSysDpySchemeSearchWindow.getCondition()};
        devpSysDpySchemeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})