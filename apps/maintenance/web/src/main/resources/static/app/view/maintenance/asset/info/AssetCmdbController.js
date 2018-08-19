Ext.define('AM.view.maintenance.asset.info.AssetCmdbController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.maintenance.asset.info.AssetCmdbController'

    ,onTreepanelItemClick : function() {
        var me = this;

        var treePanel = this.lookup('assetTypeTree');
        var record = treePanel.getSelectionModel().getSelection()[0];
        if(!record){
            return;
        }

        var topNode = record;
        while(topNode.get('code') != topNode.get('parentCode')){
            topNode = topNode.parentNode;
        }
        var topCode = topNode.get('code');
        console.log(topNode.get('code'))

        var panel = me.lookup(topCode+'_GRID');
        if(!panel){
            panel = me.lookup('OTHER_GRID');
            panel.setTitle("暂不支持类型:"+topNode.get('name'))
        }else{
            var typeCode = record.get('code') == record.get('parentCode')?null:record.get('code')

            var addWindow = panel.lookup('mainAddWindow')
            addWindow.down('#typeCodeField').setValue(typeCode);

            var searchWindow = panel.lookup('mainSearchWindow')
            searchWindow.down('#typeCodeField').setValue(typeCode);
            searchWindow.onSearchButtonClick();
            console.log(searchWindow);
        }
        me.lookup('CENTER_REGION').getLayout().setActiveItem(panel)

    }

    ,loadTypeTree:function(){
        var me = this;
        var store = me.lookupReference ('assetTypeTree').getStore()
        store.removeAll(true)
        Ext.Ajax.request({
            url: 'asset/info/assetType/tree'
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

                var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.maintenance.asset.info.AssetTypeTreeNode').getProxy().getReader().read(response);
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
    ,deleteRecord:function(view, rowIndex, colIndex){
	    var me = this;
	    var record = view.getStore().getAt(rowIndex);
	    console.log(record.get('name'))

        var assetType = AM.model.maintenance.asset.info.AssetType.load(record.get('id'));
        assetType.erase({
            success: function(record, operation) {
                Ext.toast({html: "删除节点成功", title: "操作成功", width: 200, align: 'tr'})
                me.loadTypeTree()
            }
        })
    }
    ,addChildRecord:function(view, rowIndex, colIndex){

	    view.getSelectionModel().deselectAll()

        var record = view.getStore().getAt(rowIndex);

        var form = this.lookupReference('detailForm');
        var submitButton = this.lookupReference('submitButton');
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '请先选择节点', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});

            return;
        }

        //准备数据
        var modelConfig = {parentCode:record.get('code')}
        var newRecord = Ext.create('AM.model.maintenance.asset.info.AssetType', modelConfig);
        form.getForm().loadRecord(newRecord)

        //准备界面
        form.down('#codeField').setDisabled(false)
        form.down('#codeField').setDisabled(false)

        form.setTitle('新增['+record.get('name')+']的子节点');

        submitButton.setDisabled(false);
    }
})