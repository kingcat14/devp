Ext.define('AM.view.devp.ops.DevpOpsAttachmentController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.ops_DevpOpsAttachmentController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpOpsAttachmentAddWindow = me.lookupReference ('devpOpsAttachmentAddWindow');
        var devpOpsAttachmentGrid = devpOpsAttachmentAddWindow.up('gridpanel');

        devpOpsAttachmentGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpOpsAttachmentSearchWindow = me.lookupReference ('devpOpsAttachmentSearchWindow');
        var devpOpsAttachmentGrid = devpOpsAttachmentSearchWindow.up('gridpanel');
        devpOpsAttachmentGrid.getStore().proxy.extraParams={searchCondition:devpOpsAttachmentSearchWindow.getCondition()};
        devpOpsAttachmentGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})