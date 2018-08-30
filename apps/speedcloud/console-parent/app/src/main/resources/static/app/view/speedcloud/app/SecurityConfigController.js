Ext.define('AM.view.speedcloud.app.SecurityConfigController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.speedcloud.app.SecurityConfigController'

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
        var record = Ext.create('AM.model.speedcloud.app.SecurityConfig', modelConfig);

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
                    scope: this,
                    callback: function(records, operation, success) {
                        if(!success)
                        	Ext.Msg.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.show('操作成功','删除应用私密配置成功!');
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
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];
        me.showEditWindow(record, mainGridPanel.getView().getRow(record));
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.onSearchButtonClick();

    }
    ,onExportButtonClick: function(button, e, options) {

        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        var condition = searchWindow.getCondition();
        if(!condition){
            condition = {searchCondition:{}};
        }
        if (!Ext.fly('formFly')) {
            var frm = document.createElement('form');
            frm.id = 'formFly';
            frm.className = 'x-hidden';
            document.body.appendChild(frm);
        }
        console.log(condition)
        Ext.Ajax.request({
            disableCaching: true
            ,url: "app/securityConfig/export"
            ,method: "POST"
            ,async: false  //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            ,params:condition
            ,isUpload: true
            ,form: Ext.fly('formFly')
        });

    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('mainAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('mainEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showSearchWindow: function() {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.show();
    }
	,reloadStore:function () {

        var me = this;

        var mainGridPanel = me.lookupReference('mainGridPanel');

        mainGridPanel.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var mainSearchWindow = me.lookupReference ('mainSearchWindow');
        var mainGridPanel = me.lookupReference('mainGridPanel');
        mainGridPanel.getStore().proxy.extraParams={searchCondition:mainSearchWindow.getCondition()};
        mainGridPanel.getStore().load({
            params:{
                start:0
                ,page:0
            }
        });
    }
})