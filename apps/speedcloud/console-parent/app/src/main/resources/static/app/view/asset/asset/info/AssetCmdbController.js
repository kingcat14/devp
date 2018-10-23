Ext.define('AM.view.asset.asset.info.AssetCmdbController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.asset.info.AssetCmdbController'

    ,onAssetTypeTreeItemClick : function(view, record) {
        var me = this;

        var assetCategoryCode = record.get('assetCategoryCode');
        var typeCode = record.get('code');

        var panel = me.getView();

        console.log('assetCategoryCode:'+assetCategoryCode)
        console.log('typeCode:'+typeCode)


        var addWindow = panel.lookup('mainAddWindow')
        addWindow.down('#typeCodeField').setValue(typeCode);
        addWindow.down('#categoryCodeField').setValue(assetCategoryCode);

        var searchWindow = panel.lookup('mainSearchWindow')
        searchWindow.down('#categoryCodeField').setValue(assetCategoryCode);
        searchWindow.down('#typeCodeField').setValue(typeCode);
        searchWindow.onSearchButtonClick();
        console.log(searchWindow);

    }
    ,loadTypeTree:function(){
        var me = this;
        var store = me.lookupReference ('assetTypeTree').getStore()
        store.removeAll(true)
        Ext.Ajax.request({
            url: 'asset/asset/config/assettype/tree'
            ,method: 'POST'
            ,scope:this
            // ,params:{parentCode:'ASSET_HOST'}
            ,success:function(response){

                // var list = Ext.decode(response.responseText);
                // var root = {
                //     children:list
                // }
                // store.setRoot(root);
                // me.lookupReference ('assetTypeTree').expandAll();

                var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.asset.asset.config.AssetTypeTreeNode').getProxy().getReader().read(response);
                var nodeList = resultSet.getRecords()
                var root = {
                    children:nodeList
                    ,expanded:true
                }
                store.setRoot(root);
                // me.down('treepanel').expandAll();
                //
            }
            // ,failure: this.onFailure
            // ,mask:myMask
        });
    }

    ,onAddButtonClick: function() {

        var modelConfig = {}
        var record = Ext.create('AM.model.asset.asset.info.AssetCmdb', modelConfig);

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
                        	Ext.MsgUtil.show('操作成功','删除IT资产成功!');
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
            ,url: "asset/info/assetCmdb/export"
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