Ext.define('AM.view.platform.platform.tenant.TenantController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.platform.tenant_TenantController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		if(detailTabPanel) {
            detailTabPanel.expand();
        }

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var tenantAddWindow = me.lookupReference ('tenantAddWindow');
        var tenantGrid = tenantAddWindow.up('gridpanel');

        tenantGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var tenantSearchWindow = me.lookupReference ('tenantSearchWindow');
        var tenantGrid = tenantSearchWindow.up('gridpanel');
        tenantGrid.getStore().proxy.extraParams={searchCondition:tenantSearchWindow.getCondition()};
        tenantGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})