Ext.define('AM.view.maintenance.asset.info.AssetTypeMainController', {
	extend: 'Ext.app.ViewController',
	requires: [

	]
	,alias: 'controller.asset.info_AssetTypeMainController'

    ,onAddButtonClick:function () {
        var treePanel = this.lookup('assetTypeTree');
        var record = treePanel.getSelectionModel().getSelection()[0];
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
	,onTreepanelItemClick : function() {
	    console.log('44444')
        var me = this;

        var treePanel = this.lookup('assetTypeTree');
        var record = treePanel.getSelectionModel().getSelection()[0];
        if(!record){
            return;
        }

        var form = me.lookupReference('detailForm');
        var submitButton = me.lookupReference('submitButton');
        var parentCode = record.get('parentCode');

        console.log(parentCode)
        console.log(parentCode == '-1')
        if(parentCode == '-1'){
            form.setTitle('查看['+record.get('name')+']');
        }else{
            form.setTitle('修改['+record.get('name')+']');
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

        //var id = this.down("#idField").getValue();

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
                form.getForm().loadRecord(newRecord);
                me.fireEvent('saved');
                console.log('phantom2:'+record.phantom)
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
            url: 'asset/info/assetType/tree'
            ,method: 'POST'
            ,scope:this
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

        var record = view.getStore().getAt(rowIndex);
        var form = this.lookupReference('detailForm');
        var submitButton = this.lookupReference('submitButton');
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '请先选择节点', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});

            return;
        }
        console.log(11111)
        //准备数据
        var modelConfig = {parentCode:record.get('code')}
        var newRecord = Ext.create('AM.model.maintenance.asset.info.AssetType', modelConfig);
        form.getForm().loadRecord(newRecord)
        console.log(22222)
        //准备界面
        form.down('#codeField').setDisabled(false)
        form.down('#codeField').setDisabled(false)

        form.setTitle('新增['+record.get('name')+']的子节点');

        submitButton.setDisabled(false);
        console.log(333)
    }
})