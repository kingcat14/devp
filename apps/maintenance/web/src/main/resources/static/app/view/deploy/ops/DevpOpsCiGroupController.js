Ext.define('AM.view.deploy.ops.DevpOpsCiGroupController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsCiGroupController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsCiGroupAddWindow = me.lookupReference ('devpOpsCiGroupAddWindow');
        var devpOpsCiGroupGrid = devpOpsCiGroupAddWindow.up('gridpanel');

        devpOpsCiGroupGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsCiGroupSearchWindow = me.lookupReference ('devpOpsCiGroupSearchWindow');
        var devpOpsCiGroupGrid = devpOpsCiGroupSearchWindow.up('gridpanel');
        devpOpsCiGroupGrid.getStore().proxy.extraParams={searchCondition:devpOpsCiGroupSearchWindow.getCondition()};
        devpOpsCiGroupGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})