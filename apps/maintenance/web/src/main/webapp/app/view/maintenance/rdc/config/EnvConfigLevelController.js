Ext.define('AM.view.maintenance.rdc.config.EnvConfigLevelController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.rdc.config_EnvConfigLevelController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var envConfigLevelAddWindow = me.lookupReference ('envConfigLevelAddWindow');
        var envConfigLevelGrid = envConfigLevelAddWindow.up('gridpanel');

        envConfigLevelGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var envConfigLevelSearchWindow = me.lookupReference ('envConfigLevelSearchWindow');
        var envConfigLevelGrid = envConfigLevelSearchWindow.up('gridpanel');
        envConfigLevelGrid.getStore().proxy.extraParams={searchCondition:envConfigLevelSearchWindow.getCondition()};
        envConfigLevelGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})