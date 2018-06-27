Ext.define('AM.view.maintenance.software.BusinessSoftwareController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.software_BusinessSoftwareController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		detailTabPanel.expand();

		var id = record.get('id');



	}
	,reloadStore:function () {
        var me = this;
        var businessSoftwareAddWindow = me.lookupReference ('businessSoftwareAddWindow');
        var businessSoftwareGrid = businessSoftwareAddWindow.up('gridpanel');

        businessSoftwareGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var businessSoftwareSearchWindow = me.lookupReference ('businessSoftwareSearchWindow');
        var businessSoftwareGrid = businessSoftwareSearchWindow.up('gridpanel');
        businessSoftwareGrid.getStore().proxy.extraParams={searchCondition:businessSoftwareSearchWindow.getCondition()};
        businessSoftwareGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})