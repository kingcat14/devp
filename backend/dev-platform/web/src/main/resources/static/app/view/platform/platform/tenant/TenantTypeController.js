Ext.define('AM.view.platform.platform.tenant.TenantTypeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.platform.tenant_TenantTypeController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var tenantTypeAddWindow = me.lookupReference ('tenantTypeAddWindow');
        var tenantTypeGrid = tenantTypeAddWindow.up('gridpanel');

        tenantTypeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var tenantTypeSearchWindow = me.lookupReference ('tenantTypeSearchWindow');
        var tenantTypeGrid = tenantTypeSearchWindow.up('gridpanel');
        tenantTypeGrid.getStore().proxy.extraParams={searchCondition:tenantTypeSearchWindow.getCondition()};
        tenantTypeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})