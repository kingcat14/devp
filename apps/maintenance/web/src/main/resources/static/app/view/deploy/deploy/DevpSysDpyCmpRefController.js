Ext.define('AM.view.deploy.deploy.DevpSysDpyCmpRefController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.deploy_DevpSysDpyCmpRefController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysDpyCmpRefAddWindow = me.lookupReference ('devpSysDpyCmpRefAddWindow');
        var devpSysDpyCmpRefGrid = devpSysDpyCmpRefAddWindow.up('gridpanel');

        devpSysDpyCmpRefGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysDpyCmpRefSearchWindow = me.lookupReference ('devpSysDpyCmpRefSearchWindow');
        var devpSysDpyCmpRefGrid = devpSysDpyCmpRefSearchWindow.up('gridpanel');
        devpSysDpyCmpRefGrid.getStore().proxy.extraParams={searchCondition:devpSysDpyCmpRefSearchWindow.getCondition()};
        devpSysDpyCmpRefGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})