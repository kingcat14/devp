Ext.define('AM.view.maintenance.software.InfrastructuralSoftwareController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.software_InfrastructuralSoftwareController'

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
        var infrastructuralSoftwareAddWindow = me.lookupReference ('infrastructuralSoftwareAddWindow');
        var infrastructuralSoftwareGrid = infrastructuralSoftwareAddWindow.up('gridpanel');

        infrastructuralSoftwareGrid.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var infrastructuralSoftwareSearchWindow = me.lookupReference ('infrastructuralSoftwareSearchWindow');
        var infrastructuralSoftwareGrid = infrastructuralSoftwareSearchWindow.up('gridpanel');
        infrastructuralSoftwareGrid.getStore().proxy.extraParams={searchCondition:infrastructuralSoftwareSearchWindow.getCondition()};
        infrastructuralSoftwareGrid.getStore().load({
            params:{
                start:0,
                page:0
            }
        });
    }
})