Ext.define('AM.view.asset.asset.config.AssetTypeMainController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.asset.configAssetTypeMainController'


	,onTreepanelItemClick : function() {

        var me = this;

        var treePanel = this.lookup('assetTypeTree');
        var record = treePanel.getSelectionModel().getSelection()[0];
        if(!record){
            return;
        }

        me.updateForm(record)

    }
    ,updateForm:function(record){
	    var me = this;
        var form = me.lookupReference('detailForm');
        var submitButton = me.lookupReference('submitButton');
        var parentCode = record.get('parentCode');

        if(parentCode == record.get('code')){
            form.setTitle('查看分类[' +
                '['+record.get('name')+']');
        }else{
            form.setTitle('修改分类['+record.get('name')+']');
        }

        submitButton.setDisabled(parentCode == '-1');

        form.down('#codeField').setDisabled(true)

        form.getForm().loadRecord(record)
    }
    ,onNodeSaveClick:function () {
        var me = this;
	    var form = me.lookupReference('detailForm');

        if (!form.getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var record = form.getForm().getRecord();
        var needReload = record.phantom;
        if(record.get("parentCode") == '-1'){
            Ext.MessageBox.alert('顶层节点不能更新', '顶层节点不能更新');
        }
        //将form中的数据刷进record
        form.getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存资产分类成功!');
                // form.down('#codeField').setDisabled(true);
                // form.getForm().loadRecord(newRecord);
                me.updateForm(newRecord)
                me.fireEvent('saved');

                if(needReload){
                    me.loadTypeTree();
                }
            }
        });
    }
    ,reloadStore:function () {
        var me = this;
        var assetTypeGrid = me.lookupReference ('assetTypeGrid');
        assetTypeGrid.getStore().load();

        console.log('outer reload 1')
        me.loadTypeTree()
    }
    ,loadTypeTree:function(){
        var me = this;
        var store = me.lookupReference ('assetTypeTree').getStore()
        store.removeAll(true)
        Ext.Ajax.request({
            url: 'asset/asset/config/assettype/tree'
            ,method: 'POST'
            ,scope:this
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
    ,deleteRecord:function(view, rowIndex, colIndex){
	    var me = this;
	    var record = view.getStore().getAt(rowIndex);
	    console.log(record.get('name'))

        var assetType = AM.model.asset.asset.config.AssetType.load(record.get('id'));
        assetType.erase({
            success: function(record, operation) {
                Ext.toast({html: "删除节点成功", title: "操作成功", width: 200, align: 'tr'})
                me.loadTypeTree()
            }
        })
    }
    ,addChildRecord:function(view, rowIndex, colIndex){

        var record = view.getStore().getAt(rowIndex);
        this.lookupReference('assetTypeTree').getSelectionModel().deselectAll();

        var form = this.lookupReference('detailForm');
        var submitButton = this.lookupReference('submitButton');
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '请先选择节点', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});

            return;
        }
        console.log(11111)
        //准备数据
        var modelConfig = {parentCode:record.get('code'), assetCategoryCode:record.get('assetCategoryCode')}
        var newRecord = Ext.create('AM.model.asset.asset.config.AssetType', modelConfig);
        form.getForm().loadRecord(newRecord)
        console.log(22222)
        //准备界面
        form.down('#codeField').setDisabled(false)

        form.setTitle('新增['+record.get('name')+']的子节点');

        submitButton.setDisabled(false);
        console.log(333)
    }
})