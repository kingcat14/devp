Ext.define('AM.view.icode4.codegenboard.CodeGenBoard', {
	extend: 'Ext.panel.Panel'
	,xtype: 'icode4.codegenboard.CodeGenBoard'
	,bodyPadding:10
	,bodyStyle: 'background:white; padding:10px;'
	,requires:[
		'AM.model.icode4.codegen.DomainModelCodeGenTask'
		,'AM.store.icode4.codegen.DomainModelCodeGenTaskStore'
		,'AM.store.icode4.codegenboard.TplFileTreeStore'
		,'AM.store.icode4.codegenboard.ModelTree'
	]
	,autoScroll: false
	,layout: {
		type: 'vbox',
		pack: 'start',
		align: 'stretch'
	}
	,title: '代码生成'
	,maximizable: true
	,initComponent: function() {

		var me = this;

		Ext.apply(me, {
			items: [
				{
					xtype: 'container',
					columnWidth: 1,
					layout: {
						type: 'anchor'
					},
					autoScroll: false,
					bodyPadding: 0,
					items: [
						{
							xtype: 'button',
							text: '开始生成代码!',
							anchor: '100%',
							margin: '5 0 0 5',
							scale: 'large',
							handler:function(button,event){

								var window = me.down('#taskWindow');
                                window.down('grid').getStore().each(function(record){
                                    record.drop()
                                })
                                window.show();
								var models = me.down("#codegenProjectTreePanel").getView().getChecked();
								var tpls = me.down("#codegenCodeTplTreePanel").getView().getChecked();

								var usableModels = [];
								var modelIds = "";
								var usableTpls = [];
								var tplIds = "";
								var taskList = [];
								for(var i = 0;i< models.length;i++ ){
									if(models[i].get('type')=='DOMAIN_MODEL' || models[i].get('type')=='VIEW_MODEL'|| models[i].get('type')=='childModel'){
										usableModels.push(models[i]);
										modelIds += models[i].get('type')+"--"+models[i].get('objectId')+",";
									}
								}
								for(var i = 0;i< tpls.length;i++ ){
									if(tpls[i].get('type')=='TPL_CODE'){
										usableTpls.push(tpls[i]);
										tplIds += tpls[i].get('objectId')+",";
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
                                    	var task = Ext.create("AM.model.icode4.codegen.DomainModelCodeGenTask",{
                                            modelId: model.get('objectId')
                                            ,tplCodeId: tpl.get("objectId")
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

								// var task = Ext.create("AM.model.icode4.codegen.DomainModelCodeGenTask");
								// task.set("modelIds", modelIds);
								// task.set("tplCodeIds", tplIds);
								// task.set("destPath",me.down("#projectPathField").getValue());
								// task.save();
							}
						}
					]
				}
				,{
					xtype: 'container',
					layout: {
						type: 'hbox',
						align: 'stretch',
						padding: '5 0 0 5'
					},

					autoScroll: false,
					bodyPadding: 10,
					flex:1,
					items: [
						{
							xtype: 'treepanel',
							itemId:'codegenProjectTreePanel',
							//columnWidth: 0.5,
							title:'选择对象',
							padding: '0 5 0 0',
							flex:1,
							rootVisible: false,
							displayField: 'name',
							checkPropagation:'both',
							store: Ext.create('AM.store.icode4.codegenboard.ModelTree', {autoLoad:true}),
							tbar:[
								{
									xtype:'button',
									text:'展开全部',
									iconCls: 'folder_go',
									handler:function(button){
										me.down("#codegenProjectTreePanel").expandAll()
									}
								}
								,{
                                    xtype:'button',
                                    text:'重新加载',
                                    iconCls: 'arrow_refresh',
                                    handler:function(button){
                                        me.down("#codegenProjectTreePanel").getStore().reload()
                                    }
                                }
							]
						}
						,{
							xtype: 'treepanel',
							itemId:'codegenCodeTplTreePanel',
							//columnWidth: 0.5,
							title:'选择模板',
							padding: '0 0 0 5',
							flex:1,
							rootVisible: false,
							displayField: 'name',
							checkPropagation:'both',
							store: Ext.create('AM.store.icode4.codegenboard.TplFileTreeStore', {autoLoad:true}),
							tbar:[
								{
									xtype:'button',
									text:'展开全部',
									iconCls: 'folder_go',
									handler:function(button){
										me.down("#codegenCodeTplTreePanel").expandAll()
									}
								}
                                ,{
                                    xtype:'button',
                                    text:'重新加载',
                                    iconCls: 'arrow_refresh',
                                    handler:function(button){
                                        me.down("#codegenCodeTplTreePanel").getStore().reload()
                                    }
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
                            ,columns: [
                                {xtype: 'rownumberer', width:20}
                                ,{xtype: 'gridcolumn',dataIndex: 'modelName',text: '对象', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'projectName',text: '项目', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'tplName',text: '模板', flex:1}
                                ,{xtype: 'gridcolumn',dataIndex: 'status',text: '状态', width:100}
							]
                            ,store:Ext.create('AM.store.icode4.codegen.DomainModelCodeGenTaskStore')
						}
					]

				}
			],
			dockedItems: [

			],
			listeners:{
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
