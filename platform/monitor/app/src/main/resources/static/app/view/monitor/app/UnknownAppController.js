Ext.define('AM.view.monitor.app.UnknownAppController', {
	extend: 'Ext.app.ViewController'
	,requires: [
        'AM.view.monitor.app.ApplicationAddWindow'
	]
	,alias: 'controller.monitor.app.UnknownAppController'

	,addToMonitor:function(grid, rowIndex, colIndex, item, event, record) {

	    //点击将未知应用加入监控列表
		var me = this;

        var modelConfig = {code:record.get('code'), totalCount:1, alarm:true, enable:true, thresholdValue:0}
        var record = Ext.create('AM.model.monitor.app.Application', modelConfig);

        this.showAddApplicationWindow(record);


	}

    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;

        var store = me.getViewModel().getStore('store');
        var codeField = me.lookupReference("codeField");
        var ignoredField = me.lookupReference("ignoredField");
        store.applyCondition({code:codeField.getValue(),ignored:ignoredField.getValue()})

    }

    ,showAddApplicationWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('applicationAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
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
                            Ext.MsgUtil.notification('操作成功','删除程序成功!');
                    }
                });
            }
            ,scope:me.getStore()
        });
    }
})