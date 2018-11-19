Ext.define('AM.view.asset.asset.config.AssetTypePropertyController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.asset.config.AssetTypePropertyController'

    ,loadAssetTypeTree:function(){

        var me = this;

        var assetTypeTreeStore = me.getViewModel().getStore('assetTypeTreeStore')
        assetTypeTreeStore.removeAll(true)
        Ext.Ajax.request({
            url: 'asset/asset/config/assettype/tree'
            ,method: 'POST'
            ,scope:this
            ,success:function(response){

                var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.asset.asset.config.AssetTypeTreeNode').getProxy().getReader().read(response);
                var nodeList = resultSet.getRecords()
                var root = {
                    children:nodeList
                    ,expanded:true
                }
                assetTypeTreeStore.setRoot(root);
            }
        });
    }
    ,onAssetTypeItemClick : function(view, record) {
        var me = this;
        var typeId = record.getId();
        var store = me.getViewModel().getStore('store')
        store.applyCondition({assetType:typeId}).load()


        // Ext.MessageBox.alert('Ext.MessageBox.alert', 'Ext.MessageBox.alert');

    }
    ,onAddButtonClick: function() {

        var me = this;
        var treePanel = this.lookup('assetTypeTree');
        var record = treePanel.getSelectionModel().getSelection()[0];
        if(!record){
            Ext.MessageBox.alert('未选择分类', '请先在右侧选择分类');
            return;
        }

        var rowEditing =  this.lookup('mainGridPanel').getPlugin('assetTypePropertyRowEditing');
        rowEditing.cancelEdit();

        var modelConfig = {assetType:record.getId(), assetTypeVO:{name:record.get('name')}, type:'String', required:false}
        var record = Ext.create('AM.model.asset.asset.config.AssetTypeProperty', modelConfig);
        var store = me.getViewModel().getStore('store');
        store.add(record);
        rowEditing.startEdit(record, 3);
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
                            Ext.MessageBox.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.show('操作成功','删除资产分类属性成功!');
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
        var rowEditing =  this.lookup('mainGridPanel').getPlugin('assetTypePropertyRowEditing');
        rowEditing.cancelEdit();
        rowEditing.startEdit(record, 3);
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

})