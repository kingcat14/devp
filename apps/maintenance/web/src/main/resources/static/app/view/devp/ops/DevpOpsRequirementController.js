Ext.define('AM.view.devp.ops.DevpOpsRequirementController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsRequirementController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsRequirementAddWindow = me.lookupReference ('devpOpsRequirementAddWindow');
        var devpOpsRequirementGrid = devpOpsRequirementAddWindow.up('gridpanel');

        devpOpsRequirementGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsRequirementSearchWindow = me.lookupReference ('devpOpsRequirementSearchWindow');
        var devpOpsRequirementGrid = devpOpsRequirementSearchWindow.up('gridpanel');
        devpOpsRequirementGrid.getStore().proxy.extraParams={searchCondition:devpOpsRequirementSearchWindow.getCondition()};
        devpOpsRequirementGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})