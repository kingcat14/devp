Ext.define('AM.view.product.sys.DevpSysElementInfoController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.sys_DevpSysElementInfoController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var devpSysElementInfoAddWindow = me.lookupReference ('devpSysElementInfoAddWindow');
        var devpSysElementInfoGrid = devpSysElementInfoAddWindow.up('gridpanel');

        devpSysElementInfoGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var devpSysElementInfoSearchWindow = me.lookupReference ('devpSysElementInfoSearchWindow');
        var devpSysElementInfoGrid = devpSysElementInfoSearchWindow.up('gridpanel');
        devpSysElementInfoGrid.getStore().proxy.extraParams={searchCondition:devpSysElementInfoSearchWindow.getCondition()};
        devpSysElementInfoGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})