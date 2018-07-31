Ext.define('AM.view.product.sys.DevpSysCmpModuleController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.sys_DevpSysCmpModuleController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysCmpModuleAddWindow = me.lookupReference ('devpSysCmpModuleAddWindow');
        var devpSysCmpModuleGrid = devpSysCmpModuleAddWindow.up('gridpanel');

        devpSysCmpModuleGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysCmpModuleSearchWindow = me.lookupReference ('devpSysCmpModuleSearchWindow');
        var devpSysCmpModuleGrid = devpSysCmpModuleSearchWindow.up('gridpanel');
        devpSysCmpModuleGrid.getStore().proxy.extraParams={searchCondition:devpSysCmpModuleSearchWindow.getCondition()};
        devpSysCmpModuleGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})