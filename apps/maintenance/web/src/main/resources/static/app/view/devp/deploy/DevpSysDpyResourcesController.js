Ext.define('AM.view.devp.deploy.DevpSysDpyResourcesController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.deploy_DevpSysDpyResourcesController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysDpyResourcesAddWindow = me.lookupReference ('devpSysDpyResourcesAddWindow');
        var devpSysDpyResourcesGrid = devpSysDpyResourcesAddWindow.up('gridpanel');

        devpSysDpyResourcesGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysDpyResourcesSearchWindow = me.lookupReference ('devpSysDpyResourcesSearchWindow');
        var devpSysDpyResourcesGrid = devpSysDpyResourcesSearchWindow.up('gridpanel');
        devpSysDpyResourcesGrid.getStore().proxy.extraParams={searchCondition:devpSysDpyResourcesSearchWindow.getCondition()};
        devpSysDpyResourcesGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})