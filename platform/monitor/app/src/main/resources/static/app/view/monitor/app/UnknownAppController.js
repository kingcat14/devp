Ext.define('AM.view.monitor.app.UnknownAppController', {
	extend: 'Ext.app.ViewController'
	,requires: [

	]
	,alias: 'controller.monitor.app.UnknownAppController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;


		var detailTabPanel = me.lookup('detailTabPanel');
		if(detailTabPanel) {
            detailTabPanel.expand();
        }

		var id = record.get('id');

	}
    ,onAddButtonClick: function() {

        var modelConfig = {}
        var record = Ext.create('AM.model.monitor.app.UnknownApp', modelConfig);

        this.showAddWindow(record);
    }

    ,onDeleteButtonClick: function(button, e, options) {
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var store = mainGridPanel.getStore();
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        store.remove(selections);
        store.sync({
            success:function(batch,options){

		        var count = store.getCount();

		        var targetPage = count<=0 ? store.currentPage-1 : store.currentPage;
		        targetPage = targetPage <=0 ? 1 :targetPage;
                store.loadPage(targetPage,{
                    scope: this
                    ,callback: function(records, operation, success) {
                        if(!success)
                        	Ext.MessageBox.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.notification('操作成功','删除未知程序成功!');
                    }
                });
            }
            ,scope:me.getStore()
        });
    }
	,onEditButtonClick: function(){
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        if(selections.length <= 0){
            Ext.MessageBox.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];
        me.showEditWindow(record, mainGridPanel.getView().getRow(record));
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;

        var store = me.getViewModel().getStore('store');
        var codeField = me.lookupReference("codeField");
        var ignoredField = me.lookupReference("ignoredField");
        store.applyCondition({code:codeField.getValue(),ignored:ignoredField.getValue()})




    }


})