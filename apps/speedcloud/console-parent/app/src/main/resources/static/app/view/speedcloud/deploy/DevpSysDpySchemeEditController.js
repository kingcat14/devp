Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditController', {
	extend: 'Ext.app.ViewController',
	requires: [
		'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode'
		,'AM.model.speedcloud.deploy.DevpSysDpyResourceRef'
	]
	,alias: 'controller.speedcloud.deploy.DevpSysDpySchemeEditController'
    ,loadResourcesTree:function(){
		var me = this;
		// var store = me.lookupReference ('schemeResourceTree').getStore()
		var store = me.getViewModel().getStore('schemeResourceTreeStore')
		console.log(me.getViewModel())
		store.removeAll(true)
		Ext.Ajax.request({
			url: 'speedcloud/deploy/devpsysdpyresources/tree'
			,method: 'POST'
			,scope:this
			,success:function(response){
				var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode').getProxy().getReader().read(response);
				var nodeList = resultSet.getRecords()
				var root = {
					children:nodeList
					,expanded:true
				}
				store.setRoot(root);
			}
		});
	}
	,deleteRelation:function(view, rowIndex, colIndex, item, e, record, row){

		console.log('deleteRelation')

		var me = this;
        var store = me.getViewModel().getStore('schemeResourceTreeStore')

		var relationId = record.get('relationId')
        console.log(record.get('relationId'))
		console.log(record)
		if(!relationId){
			return;
		}


        var relation = AM.model.speedcloud.deploy.DevpSysDpyResourceRef.load(relationId,{
            scope: this,
            failure: function(record, operation) {
                Ext.toast({html: "解除失败", title: "操作失败", width: 200, align: 'tr'})
            },
            success: function(record, operation) {
                relation.erase({
                    success: function(newRecord, operation) {

                        Ext.toast({html: "解除成功", title: "操作成功", width: 200, align: 'tr'})
                        //me.loadTypeTree()
                        store.each(function(node){
                            if(node.get('relationId') == relationId){
                                node.drop()
                            }
                        })
                    }
                })
            }
        });

	}

})