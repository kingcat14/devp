Ext.define('AM.view.devp.sys.DevpSysCmpController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.sys_DevpSysCmpController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysCmpAddWindow = me.lookupReference ('devpSysCmpAddWindow');
        var devpSysCmpGrid = devpSysCmpAddWindow.up('gridpanel');

        devpSysCmpGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysCmpSearchWindow = me.lookupReference ('devpSysCmpSearchWindow');
        var devpSysCmpGrid = devpSysCmpSearchWindow.up('gridpanel');
        devpSysCmpGrid.getStore().proxy.extraParams={searchCondition:devpSysCmpSearchWindow.getCondition()};
        devpSysCmpGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})