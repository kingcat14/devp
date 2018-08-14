Ext.define('AM.view.application.common.SimpleConfigTypeController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.common_SimpleConfigTypeController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;
        var searchCondition = {configType:record.get('id')}
        var simpleConfigPanel = me.lookup('simpleConfigPanel');
        simpleConfigPanel.expand();

        console.log(searchCondition);
        simpleConfigPanel.getStore().proxy.extraParams = {searchCondition:searchCondition};
        simpleConfigPanel.getStore().load({
            params:{
                page:0
            }
        });

		console.log(111111)
        //配置项页展现对应

	}
	,reloadStore:function () {
        var me = this;
        var simpleConfigTypeAddWindow = me.lookupReference ('simpleConfigTypeAddWindow');
        var simpleConfigTypeGrid = simpleConfigTypeAddWindow.up('gridpanel');

        simpleConfigTypeGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var simpleConfigTypeSearchWindow = me.lookupReference ('simpleConfigTypeSearchWindow');
        var simpleConfigTypeGrid = simpleConfigTypeSearchWindow.up('gridpanel');
        simpleConfigTypeGrid.getStore().proxy.extraParams={searchCondition:simpleConfigTypeSearchWindow.getCondition()};
        simpleConfigTypeGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})