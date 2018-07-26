Ext.define('AM.view.deploy.deploy.DevpSysDpyResInstController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.deploy_DevpSysDpyResInstController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysDpyResInstAddWindow = me.lookupReference ('devpSysDpyResInstAddWindow');
        var devpSysDpyResInstGrid = devpSysDpyResInstAddWindow.up('gridpanel');

        devpSysDpyResInstGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysDpyResInstSearchWindow = me.lookupReference ('devpSysDpyResInstSearchWindow');
        var devpSysDpyResInstGrid = devpSysDpyResInstSearchWindow.up('gridpanel');
        devpSysDpyResInstGrid.getStore().proxy.extraParams={searchCondition:devpSysDpyResInstSearchWindow.getCondition()};
        devpSysDpyResInstGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})