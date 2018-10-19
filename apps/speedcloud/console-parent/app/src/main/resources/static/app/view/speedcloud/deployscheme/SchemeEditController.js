Ext.define('AM.view.speedcloud.deployscheme.SchemeEditController', {
	extend: 'Ext.app.ViewController',
	requires: [
		'AM.model.speedcloud.deployscheme.ResourceTreeNode'
		,'AM.model.speedcloud.deployscheme.ResourceRelation'
		,'AM.model.speedcloud.deployscheme.Resource'
        ,'AM.model.console.jointjs.JointData'
	]
	,alias: 'controller.speedcloud.deployscheme.SchemeEditController'
    ,initPanel:function(){
	    var me = this;
	    me.loadResourcesTree();
        me.loadResourceGraph();
    }
    ,loadResourcesTree:function(){
		var me = this;

		var record = me.getViewModel().get('record');

		var store = me.getViewModel().getStore('schemeResourceTreeStore');

		store.removeAll(true)
		Ext.Ajax.request({
			url: 'speedcloud/deployscheme/resource/tree'
			,method: 'POST'
            ,params:{scheme:record.getId()}
			,scope:this
			,success:function(response){
				var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.speedcloud.deployscheme.ResourceTreeNode').getProxy().getReader().read(response);
				var nodeList = resultSet.getRecords()
				var root = {
					children:nodeList
					,expanded:true
				}
				store.setRoot(root);
			}
		});
	}

    ,handleResourceSaved:function(){
        var me = this;
        me.loadResourcesTree();

        var schemeResourceStore = me.getViewModel().getStore('schemeResourceStore');

        var resourceEditPanel = me.lookup('resourceEditPanel');
        var record = resourceEditPanel.getViewModel().get('record');

        if(!schemeResourceStore.getById(record.getId())){
            schemeResourceStore.add(record);
            me.createGraphState(0, 0, record);
        }else{
            me.getView().graphUpdateCell(record.getId(), record.get('name'));
        }

    }
    ,handleRelationSaved:function () {
        var me = this;

        var schemeRelationStore = me.getViewModel().getStore('schemeRelationStore');

        var relationEditPanel = me.lookup('relationEditPanel');
        var record = relationEditPanel.getViewModel().get('record');

        var relation = schemeRelationStore.getById(record.getId())
        if(!relation){
            schemeRelationStore.add(record);

            me.createGraphLink(record);
            me.loadResourcesTree();
        }else{
            me.getView().graphUpdateCell(record.getId(), record.get('name'));
        }
    }
	,createResource:function () {


        var me = this;

        var schemeRecord  = me.getViewModel().get('record')

        var resource = Ext.create('AM.model.speedcloud.deployscheme.Resource', {
            scheme:schemeRecord.get('id')
			,env:schemeRecord.get('env')
			,project:schemeRecord.get('project')
            // ,scheme:schemeRecord.get('id')
        });



        var detailEditPanel = me.lookup('detailEditPanel');
        var resourceEditPanel = me.lookup('resourceEditPanel');



        resourceEditPanel.setModel(resource)

        detailEditPanel.getLayout().setActiveItem(resourceEditPanel);

    }
    ,deleteResource:function(view, rowIndex, colIndex, item, e, record, row){

        console.log('deleteResource')

        var me = this;
        var currentId = record.getId()
        var schemeResourceStore = me.getViewModel().getStore('schemeResourceStore').applyCondition({scheme:record.getId()});

        var resourceEditPanel = me.lookup('resourceEditPanel');
		var resource = AM.model.speedcloud.deployscheme.Resource.load(record.getId(),{
            success: function(record, operation) {
                resource.erase({

                    failure: function(record, operation) {
                        var response = Ext.decode(operation.getError().response.responseText)
                        Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+response.exception+"<br/>"+response.message);
                    }
                    ,success: function(record, operation) {
                        Ext.toast({html: "删除资源", title: "操作成功", width: 200, align: 'tr'})
                        me.loadResourcesTree();
                        //如果编辑面板是当前资源，要删掉
						var editRecord = resourceEditPanel.getViewModel().get('record')
						if(editRecord != null && editRecord.getId() == currentId){
							me.createResource();
						}

						//途中删除关系



                        var record = schemeResourceStore.getById(currentId);
						if(record){
                            me.deleteGraphState(record)
                        }
					}
				})
			}
		})

        //删除所有的关联
        var schemeRelationStore = me.getViewModel().getStore('schemeRelationStore').applyCondition({scheme:record.getId()});
        schemeRelationStore.each(function(relation){
            if(relation.get('resource') == currentId || relation.get('refResource') == currentId){
                relation.drop();
            }
        })


    }
	,createRelation:function (view, rowIndex, colIndex, item, e, record) {
        console.log('createRelation')
		var me = this;

        var schemeRecord  = me.getViewModel().get('record')

		var relation = Ext.create('AM.model.speedcloud.deployscheme.ResourceRelation', {
            resource:record.get('id')
			,direction:'EMPTY'
			// ,scheme:schemeRecord.get('id')
		});

		var detailEditPanel = me.lookup('detailEditPanel');
        var relationEditPanel = me.lookup('relationEditPanel');



        relationEditPanel.setModel(relation)

        detailEditPanel.getLayout().setActiveItem(relationEditPanel);

        e.stopPropagation();


    }
	,deleteRelationBtnClick:function(view, rowIndex, colIndex, item, e, record, row){

		console.log('deleteRelationBtnClick')

		var me = this;

		var relationId = record.get('relationId')

		if(!relationId){
			return;
		}

        me.deleteRelation(relationId);
	}
    ,deleteRelation:function(relationId){

        console.log('deleteRelation')

        var me = this;

        if(!relationId){
            return;
        }

        var store = me.getViewModel().getStore('schemeResourceTreeStore')

        var schemeRelationStore = me.getViewModel().getStore('schemeRelationStore');

        var relation = AM.model.speedcloud.deployscheme.ResourceRelation.load(relationId,{
            scope: this,
            failure: function(record, operation) {
                var response = Ext.decode(operation.getError().response.responseText)
                Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+response.exception+"<br/>"+response.message);
            }
            ,success: function(record, operation) {
                relation.erase({
                    failure: function(record, operation) {
                        var response = Ext.decode(operation.getError().response.responseText)
                        Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+response.exception+"<br/>"+response.message);
                    }
                    ,success: function(newRecord, operation) {

                        Ext.toast({html: "解除成功", title: "操作成功", width: 200, align: 'tr'})
                        //me.loadTypeTree()
                        store.each(function(node){
                            if(node.get('relationId') == relationId){
                                node.drop()
                            }
                        })

                        me.deleteGraphLink(relationId)

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

        //没有关系ID，说明是资源节点
		var detailEditPanel = me.lookup('detailEditPanel');

        if(!record.get('relationId')){
        	console.log('resourceEditPanel')
            detailEditPanel.getLayout().setActiveItem(0);
            var resourceEditPanel = me.lookup('resourceEditPanel');

            var resource = AM.model.speedcloud.deployscheme.Resource.load(record.get('objId'), {
            	success:function () {
                    resourceEditPanel.setModel(resource)
                }
			})

		}else{
            console.log('relationEditPanel')

            var relationEditPanel = me.lookup('relationEditPanel');

			var relationId = record.get('relationId');
            var relation = AM.model.speedcloud.deployscheme.ResourceRelation.load(relationId, {
            	success:function(){
                    relationEditPanel.setModel(relation);
                }
			})

            detailEditPanel.getLayout().setActiveItem(relationEditPanel);

		}

    }
    ,redrawResourceGraph:function(){
        var me = this;

        var graph = me.getViewModel().get('graph');
        if(graph) {
            graph.clear({ignoreRemove:true});
        }
        var record = me.getViewModel().get('record')
        var schemeResourceStore = me.getViewModel().getStore('schemeResourceStore').applyCondition({scheme:record.getId()});
        schemeResourceStore.loadPage(0,{
            scope: this,
            callback: function(records, operation, success) {

                if(!success){
                    Ext.Msg.show({title: '加载资源失败', msg: "ERROR:"+response.status+"<br/>请重试或联系管理员", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
                    return ;
                }

                for(var i =0; i < records.length;i++){

                    var record = records[i];

                    me.createGraphState(190 * (i+1), 100, record)
                }
                me.loadRelationGraph()
            }
        });


    }
    ,loadRelationGraph:function(){

	    var me = this;

        var record = me.getViewModel().get('record')

        var schemeRelationStore = me.getViewModel().getStore('schemeRelationStore').applyCondition({scheme:record.getId()});
        schemeRelationStore.loadPage(0,{
            scope: this,
            callback: function(records, operation, success) {

                if(!success){
                    Ext.Msg.show({title: '加载关系失败', msg: "ERROR:"+response.status+"<br/>请重试或联系管理员", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
                    return ;
                }

                for(var i in records){

					var record = records[i];
					me.createGraphLink(record)

                }
            }
        });
    }
    ,createGraphState:function(x, y, record){
		var me = this;

        me.getView().graphState(x, y, record.get('name'), record.getId())
        me.saveResourceGraph()
	}
    ,createGraphLink:function(record){
        var me = this;

        var resource = record.get('resource')
        var refResource = record.get('refResource');

        me.getView().graphLink(resource, refResource, record.get('name'), null, record.getId())
        me.saveResourceGraph()

    }
    ,deleteGraphLink:function(id){

		var me = this;

        me.getView().graphDelLink(id)

        me.saveResourceGraph();
    }
    ,deleteGraphState:function(record){

        var me = this;

        me.getView().graphDelState(record.getId())

        me.saveResourceGraph();

    }
    ,loadResourceGraph:function(){
        var me = this;
        var record = me.getViewModel().get('record');
        var graph = me.getViewModel().get('graph');

        if(!graph){return};

        var jsonData = AM.model.console.jointjs.JointData.load(record.getId(),{
            failure: function(record, operation) {
                var response = Ext.decode(operation.getError().response.responseText)
                Ext.MessageBox.alert('操作失败', "ERROR:"+response.status+"<br/>"+response.exception+"<br/>"+response.message);
            }
            ,success: function(record, operation) {
                var viewJson = record.get('viewJson');
                graph.fromJSON(JSON.parse(viewJson));
            }
        })
    }
    ,saveResourceGraph:function(){

	    var me = this;

	    var record = me.getViewModel().get('record');
        var graph = me.getViewModel().get('graph');

        var jsonString = JSON.stringify(graph.toJSON());

        var jsonData = AM.model.console.jointjs.JointData.create({id:record.getId(),viewJson:jsonString})

        jsonData.setId(record.getId())

        jsonData.save();

    }
})