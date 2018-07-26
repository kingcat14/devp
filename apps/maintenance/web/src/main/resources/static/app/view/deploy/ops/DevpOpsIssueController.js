Ext.define('AM.view.deploy.ops.DevpOpsIssueController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsIssueController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsIssueAddWindow = me.lookupReference ('devpOpsIssueAddWindow');
        var devpOpsIssueGrid = devpOpsIssueAddWindow.up('gridpanel');

        devpOpsIssueGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsIssueSearchWindow = me.lookupReference ('devpOpsIssueSearchWindow');
        var devpOpsIssueGrid = devpOpsIssueSearchWindow.up('gridpanel');
        devpOpsIssueGrid.getStore().proxy.extraParams={searchCondition:devpOpsIssueSearchWindow.getCondition()};
        devpOpsIssueGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})