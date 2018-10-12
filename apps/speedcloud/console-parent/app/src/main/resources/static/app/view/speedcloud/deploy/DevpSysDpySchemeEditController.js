Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditController', {
	extend: 'Ext.app.ViewController',
	requires: [
		'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode'
		,'AM.model.speedcloud.deploy.DevpSysDpyResourceRef'
		,'AM.model.speedcloud.deploy.DevpSysDpyResources'
	]
	,alias: 'controller.speedcloud.deploy.DevpSysDpySchemeEditController'
    ,loadResourcesTree:function(){
		var me = this;
		// var store = me.lookupReference ('schemeResourceTree').getStore()


		var store = me.getViewModel().getStore('schemeResourceTreeStore')

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

	,createResource:function () {
        console.log('createResource')
        var me = this;

        var schemeRecord  = me.getViewModel().get('record')

        var resource = Ext.create('AM.model.speedcloud.deploy.DevpSysDpyResources', {
            scheme:schemeRecord.get('id')
			,env:schemeRecord.get('env')
			,project:schemeRecord.get('project')
            // ,scheme:schemeRecord.get('id')
        });

        console.log(resource);

        var detailEditPanel = me.lookup('detailEditPanel');
        var resourceEditPanel = me.lookup('resourceEditPanel');



        resourceEditPanel.setModel(resource)

        detailEditPanel.getLayout().setActiveItem(resourceEditPanel);

    }
    ,deleteResource:function(view, rowIndex, colIndex, item, e, record, row){

        console.log('deleteResource')

        var me = this;
        var currentId = record.getId()
        var resourceEditPanel = me.lookup('resourceEditPanel');
		var resource = AM.model.speedcloud.deploy.DevpSysDpyResources.load(record.getId(),{
            success: function(record, operation) {
                resource.erase({
                    success: function(record, operation) {
                        Ext.toast({html: "删除资源", title: "操作成功", width: 200, align: 'tr'})
                        me.loadResourcesTree();
                        //如果编辑面板是当前资源，要删掉
						var editRecord = resourceEditPanel.getViewModel().get('record')
						if(editRecord != null && editRecord.getId() == currentId){
							me.createResource();
						}
					}
				})
			}
		})

    }
	,createRelation:function (view, rowIndex, colIndex, item, e, record, row) {
        console.log('createRelation')
		var me = this;

        var schemeRecord  = me.getViewModel().get('record')

		var relation = Ext.create('AM.model.speedcloud.deploy.DevpSysDpyResourceRef', {
            resource:record.get('id')
			,direction:'EMPTY'
			// ,scheme:schemeRecord.get('id')
		});
		console.log(relation);

		var detailEditPanel = me.lookup('detailEditPanel');
        var relationEditPanel = me.lookup('relationEditPanel');



        relationEditPanel.setModel(relation)

        detailEditPanel.getLayout().setActiveItem(relationEditPanel);

        e.stopPropagation();


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
	,onResourceNameClick:function (table, td, cellIndex, record) {
		console.log('onResourceNameClick')

		if(cellIndex != 0){
			console.log('cellIndex:'+cellIndex)
			return;
		}

		var me = this;

		var treePanel = this.lookup('schemeResourceTree');




        console.log('relationId:'+record.get('relationId'));
        //没有关系ID，说明是资源节点
		var detailEditPanel = me.lookup('detailEditPanel');

        if(!record.get('relationId')){
        	console.log('resourceEditPanel')
            detailEditPanel.getLayout().setActiveItem(0);
            var resourceEditPanel = me.lookup('resourceEditPanel');

            var resource = AM.model.speedcloud.deploy.DevpSysDpyResources.load(record.get('objId'), {
            	success:function () {
                    resourceEditPanel.setModel(resource)
                }
			})

		}else{
            console.log('relationEditPanel')

            var relationEditPanel = me.lookup('relationEditPanel');

			var relationId = record.get('relationId');
            var relation = AM.model.speedcloud.deploy.DevpSysDpyResourceRef.load(relationId, {
            	success:function(){
                    relationEditPanel.setModel(relation);
                }
			})

            detailEditPanel.getLayout().setActiveItem(relationEditPanel);

		}

        console.log()
    }
})