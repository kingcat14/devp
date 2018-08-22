Ext.define('AM.view.devp.deploy.DevpSysDpyInstSchemeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.deploy_DevpSysDpyInstSchemeController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysDpyInstSchemeAddWindow = me.lookupReference ('devpSysDpyInstSchemeAddWindow');
        var devpSysDpyInstSchemeGrid = devpSysDpyInstSchemeAddWindow.up('gridpanel');

        devpSysDpyInstSchemeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysDpyInstSchemeSearchWindow = me.lookupReference ('devpSysDpyInstSchemeSearchWindow');
        var devpSysDpyInstSchemeGrid = devpSysDpyInstSchemeSearchWindow.up('gridpanel');
        devpSysDpyInstSchemeGrid.getStore().proxy.extraParams={searchCondition:devpSysDpyInstSchemeSearchWindow.getCondition()};
        devpSysDpyInstSchemeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})