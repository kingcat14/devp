Ext.define('AM.view.maintenance.rdc.config.DevelopTypeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.rdc.config_DevelopTypeController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var developTypeAddWindow = me.lookupReference ('developTypeAddWindow');
        var developTypeGrid = developTypeAddWindow.up('gridpanel');

        developTypeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var developTypeSearchWindow = me.lookupReference ('developTypeSearchWindow');
        var developTypeGrid = developTypeSearchWindow.up('gridpanel');
        developTypeGrid.getStore().proxy.extraParams={searchCondition:developTypeSearchWindow.getCondition()};
        developTypeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})