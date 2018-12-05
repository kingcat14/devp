Ext.define('AM.view.icode.codegenboard.CodeGenBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode.codegenboard.CodeGenBoard'
	,bodyPadding: '5 5 0 5'
    , bodyCls: 'app-dashboard'
	// ,bodyStyle: 'background:white; padding:10px;'
	,requires:[
		'AM.view.icode.codegenboard.CodeGenBoardController'
		// 'AM.model.icode.codegen.DomainModelCodeGenTask'
		// ,'AM.store.icode.codegen.DomainModelCodeGenTaskStore'
		,'AM.store.icode.codegenboard.TplFileTreeStore'
		// ,'AM.store.icode.codegenboard.ModelTree'
	]
	,autoScroll: false
	,layout: {
		type: 'vbox'
		,pack: 'start'
		,align: 'stretch'
	}
	,title: '代码生成'
	,maximizable: true
	,controller:'icode.codegenboard.CodeGenBoardController'
	,initComponent: function() {

		var me = this;

		Ext.apply(me, {
			items: [
				{
					xtype: 'container'
					,columnWidth: 1
					,layout: {
						type: 'anchor'
					}
					,autoScroll: false
					,bodyPadding: 0
					,items: [
						{
							xtype: 'button'
							,text: '开始生成代码!'
							,anchor: '100%'
							,margin: '0 0 0 0'
							,scale: 'large'
							,handler:function(button,event){

								var window = me.down('#taskWindow');
								// window = me.lookupReference('taskGrid');
                                window.down('grid').getStore().each(function(record){
                                    record.drop()
                                })
                                window.show();
								var models = me.down("#codegenProjectTreePanel").getView().getChecked();
								var tpls = me.down("#codegenCodeTplTreePanel").getView().getChecked();

								var usableModels = [];
								var usableTpls = [];
								var taskList = [];

								function findRecordComponentId(model){
									var current = model.parentNode;
									var count = 0;

                                    while(current){
                                    	//避免埋的太深
                                    	if(++count > 100){
                                            Ext.MsgUtil.notification('操作失败', '埋的太深, 无法确定组件!');
                                    		return ;
										}

										console.log('current:'+current.get('type'))

                                    	var type = current.get('type');

                                    	if(type == 'COMPONENT'){
											return current.get('objectId');
										}

                                        current = current.parentNode;
									}
									return null;
                                }

								for(var i = 0;i< models.length;i++ ){
									console.log(models[i])
									if(models[i].get('type')=='ENTITY' || models[i].get('type')=='PARAMETER'){
										// usableModels.push({id:models[i], type:models[i].get('type'), componentId:findModelComponentId(models[i])});
                                        usableModels.push(models[i]);
									}
								}
								for(var i = 0;i< tpls.length;i++ ){
									if(tpls[i].get('type')=='TPL_CODE'){
										usableTpls.push(tpls[i]);
									}
								}


                                for(var i = 0;i< usableModels.length;i++ ){

                                	var model = usableModels[i]

									for(var j = 0;j< usableTpls.length;j++ ){
                                    	var tpl = usableTpls[j];
                                    	console.log(model.get('objectId')+"-"+tpl.get("objectId")+"-"+tpl.get("projectId"));
                                    	var node = tpl;
                                    	var type = "";
                                    	while(node.parentNode && node.get('type') != 'TPL_PROJECT'){
                                            node = node.parentNode;
                                            type = (node.get('type') == 'TPL_TYPE')?node.get('name'):type;
										}
                                    	var task = Ext.create("AM.model.icode.codegen.DomainModelCodeGenTask",{
                                            modelId: model.get('objectId')
											,modelType: model.get('type')
                                            ,modelComponentId: findRecordComponentId(model)
                                            ,tplCodeId: tpl.get("objectId")
                                            ,targetComponentId: findRecordComponentId(tpl)
                                            ,projectId: tpl.get("projectId")
											,modelName: model.get('name')
											,tplName:"("+type+")"+tpl.get('name')
											,projectName:node.get('name')
                                            ,status:'未执行'
                                        })
                                    	taskList.push(task);

                                    }
                                }
                                // window.down('grid').getStore().removeAll(true)
                                window.down('grid').getStore().add(taskList)
                                window.down('grid').getStore().each(function(record){
                                    record.set('status', '执行中');
								})


                                for(var i = 0;i< taskList.length;i++ ){
                                    var task = taskList[i];
                                    task.status='执行中...'
                                    task.save();
                                }

								// var task = Ext.create("AM.model.icode.codegen.DomainModelCodeGenTask");
								// task.set("modelIds", modelIds);
								// task.set("tplCodeIds", tplIds);
								// task.set("destPath",me.down("#projectPathField").getValue());
								// task.save();
							}
						}
					]
				}
				,{
					xtype: 'container'
					,layout: {
						type: 'hbox'
						,align: 'stretch'
						,padding: '5 0 0 0'
					}

					,autoScroll: false
					,flex:1
					,items: [
                        {
                            xtype: 'treepanel'
                            ,itemId:'codegenProjectTreePanel'
                            //columnWidth: 0.5,
                            ,title:'选择对象'
                            ,margin: '0 5 0 0'
                        	,frame: true
                            ,flex:1
                            ,rootVisible: false
                            ,displayField: 'name'
                            ,checkPropagation:'none'
                            ,store: Ext.create('AM.store.icode.codegenboard.ModelTree', {autoLoad:true})
                            ,tbar:[
                                {
                                    xtype:'button'
                                    ,text:'展开全部'
                                    ,iconCls: 'fas fa-expand'
                                    ,handler:function(button){
                                        me.down("#codegenProjectTreePanel").expandAll()
                                    }
                                }
                                ,{
                                    xtype:'button'
                                    ,text:'重新加载'
                                    ,iconCls: 'fas fa-sync-alt'
                                    ,handler:function(button){
                                        me.down("#codegenProjectTreePanel").getStore().reload()
                                    }
                                }
                            ]
                            ,columns:[
                                {
                                    xtype: 'treecolumn'
                                    ,text: 'Name'
                                    ,dataIndex: 'name'
                                    ,flex: 1
                                    ,sortable: true
                                    ,renderer: function(v, metaData, record) {
                                        metaData.glyph = record.glyph;
                                        return v;
                                    }
                                }
                            ]
                        }
						,{
							xtype: 'treepanel'
							,itemId:'codegenCodeTplTreePanel'
							//columnWidth: 0.5,
							,title:'选择模板'
                            ,frame: true
							,margin: '0 0 0 5'
							,flex:1
							,rootVisible: false
							,displayField: 'name'
							,checkPropagation: 'both'
							,store: Ext.create('AM.store.icode.codegenboard.TplFileTreeStore', {autoLoad:true})
							,tbar:[
								{
									xtype:'button'
									,text:'展开全部'
									,iconCls: 'fas fa-expand'
									,handler:function(button){
										me.down("#codegenCodeTplTreePanel").expandAll()
									}
								}
                                ,{
                                    xtype:'button'
                                    ,text:'重新加载'
                                    ,iconCls: 'fas fa-sync-alt'
                                    ,handler:function(button){
                                        me.down("#codegenCodeTplTreePanel").getStore().reload()
                                    }
                                }
							]
							,columns:[
                                {
                                    xtype: 'treecolumn'
                                    ,text: 'Name'
                                    ,dataIndex: 'name'
                                    ,flex: 1
                                    ,sortable: true
                                    ,renderer: function(v, metaData, record) {
                                        metaData.glyph = record.glyph;
                                        return v;
                                    }
                                }, {
                                    xtype: 'actioncolumn'
                                    , menuDisabled: true
                                    , width: 30
                                    , items: [{
                                        tooltip: '展开'
                                        ,handler: function (view, rowIndex, colIndex, item, event, record){
                                        	if(record.isExpanded()){
                                                record.collapse()
											}else {
                                                view.expand(record, true);
                                            }
										}
                                        ,getClass:function(value, metadata, record){
                                            // if(record.parentNode && record.parentNode.isRoot()) {
                                            //     return 'fas fa-angle-double-down';
                                            // }
                                            if(record.get('type') == 'COMPONENT' || record.get('type') == 'TPL_TYPE'){
                                                return 'fas fa-angle-double-down';
											}
                                        }
                                        ,isDisabled:function (view, rowIndex, colIndex, item, record) {
                                            // if(record.parentNode && record.parentNode.isRoot()) {
                                            //     return false;
                                            // }
                                            if(record.get('type') == 'COMPONENT' || record.get('type') == 'TPL_TYPE'){
                                                return false;
                                            }
                                            return true;
                                        }
									}]
                                }
							]
						}
					]
				}
				,{
					xtype:'window'
					,reference:'taskWindow'
					,itemId:'taskWindow'
					,layout:'fit'
					,width:'50%'
					,height:'50%'
					,autoScroll:true
					,closeAction:'hide'
					,title:'代码生成任务'
					,items:[
						{
							xtype:'grid'
							,columnLines: true
							,reference:'taskGrid'
                            ,columns: [
                                {xtype: 'rownumberer', width:20}
                                ,{xtype: 'gridcolumn',dataIndex: 'modelName',text: '对象', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'projectName',text: '项目', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'tplName',text: '模板', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'status',text: '状态', width:100}
							]
                            ,store:Ext.create('AM.store.icode.codegen.DomainModelCodeGenTaskStore')
						}
					]

				}
			]
			,dockedItems: []
			,listeners:{
				render:{
					fn: function(){
						//me.down("#codegenProjectTreePanel").expandAll()
					}
				}
			}
		});

		me.callParent(arguments);
	}

});
