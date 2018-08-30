Ext.define('AM.view.icode4.systemboard.SystemBoardDomainModelPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.systemboard.SystemBoardDomainModelPropertyPanel'
    ,title: '对象属性'
	,requires:[
		'Ext.tree.plugin.TreeViewDragDrop'
		,'AM.model.icode4.system.DomainModelProperty'
		,'AM.store.icode4.system.DomainModelPropertyStore'
        ,'AM.store.icode4.system.ReferencePropertyTypeStore'

	]
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [
            	{
		        xtype: 'numbercolumn'
		        ,dataIndex: 'viewIndex'
		        ,format:'0,000'
		        ,width:80
		        ,text: '展现顺序'
		        ,editor: {
			        xtype: 'numberfield'
		        }
	        }
	        , {
                xtype: 'gridcolumn'
                ,dataIndex: 'name'

                ,text: '属性名'
                ,editor: {
	                xtype: 'textfield'
                }
            }
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'code'
                ,text: '属性代码'
		        ,editor: {
			        xtype: 'textfield'
		        }
            }
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'type'
                ,text: '属性类型'
			    ,itemId:'typeCombo'
		        ,width:200
		        ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view){
			        var paramStore = me.down('#typeCombo').getEditor().getStore();

			        var index = paramStore.find('code', value);

			        if(index != -1){
				        return paramStore.getAt(index).get('name');
			        }
			        return value;
		        }
		        ,editor: new Ext.form.field.ComboBox({
			        typeAhead: true
			        ,triggerAction: 'all'
			        ,displayField:'name'
			        ,valueField:'code'
			        ,store:Ext.create('AM.store.icode4.system.ReferencePropertyTypeStore', {autoLoad:false, asynchronousLoad:false})
			        ,value : 'String'
		        })



            }
			,{
				xtype: 'checkcolumn'
				,width:50
				,dataIndex: 'primaryKey'
				,text: '主键'

			}
			,{
				xtype: 'booleancolumn'
				,width:70
				,dataIndex: 'search'
				,trueText: '是'
				,falseText: '否'
				,emptyCellText :'不确定'
				,text: '可查询'
				,editor: new Ext.form.field.ComboBox({
					editable:false
					,store: [
						[true,'是']
						,[false,'否']
					]
					,value : true
				})
			}
            ,{
                xtype: 'booleancolumn'
				,width:70
                ,dataIndex: 'persist'
                ,trueText: '是'
                ,falseText: '否'
                ,emptyCellText :'不确定'
                ,text: '持久化'
		        ,editor: new Ext.form.field.ComboBox({
			        editable:false
			        ,store: [
				        [true,'是']
				        ,[false,'否']
			        ]
			        ,value : true
		        })
            }
            ,{
                xtype: 'booleancolumn'
                ,width:70
                ,dataIndex: 'editable'
                ,trueText: '是'
                ,falseText: '否'
                ,emptyCellText :'不确定'
                ,text: '可修改'
		        ,editor: new Ext.form.field.ComboBox({
			        editable:false
			        ,store: [
				        [true,'是']
				        ,[false,'否']
			        ]
			        ,value : true
		        })
            }
            ,{
                xtype: 'booleancolumn'
                ,width:70
				,dataIndex: 'nullable'
                ,trueText: '是'
                ,falseText: '否'
                ,emptyCellText :'不确定'
                ,text: '可为空'
		        ,editor: new Ext.form.field.ComboBox({
			        editable:false
			        ,store: [
				        [true,'是']
				        ,[false,'否']
			        ]
			        ,value : true
		        })
            }
			,{
				xtype: 'booleancolumn'
				,width:70
				,dataIndex: 'visible'
				,trueText: '是'
				,falseText: '否'
				,emptyCellText :'不确定'
				,text: '可展现'
				,editor: new Ext.form.field.ComboBox({
					editable:false
					,store: [
						[true,'是']
						,[false,'否']
					]
					,value : true
				})
			}
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'relatedDomainModelId'
                ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                    return record.get("relatedDomainModelName")?record.get("relatedDomainModelName"):'';
                }
                ,text: '关联对象'
                ,hidden:true
            }
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'relatedDomainModelPropertyId'
                ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                    return record.get("relatedDomainModelPropertyName")?record.get("relatedDomainModelPropertyName"):'';
                }
                ,text: '展现的关联对象属性'
                // ,editor: new Ext.form.field.ComboBox({
                //     typeAhead: false
                //     ,triggerAction: 'all'
                //     ,editable: false
                //     ,store:Ext.create("AM.store.icode4.system.DomainModelPropertyStore", {asynchronousLoad:false, autoLoad:false, pageSize: 1000,})
                //     ,displayField:'name'
                //     ,valueField:'id'
                //     ,allowBlank:false
                // })
                ,editor: {
                    xtype:'combo'
                    ,typeAhead: false
                    ,triggerAction: 'all'
                    ,editable: false
                    // ,store:Ext.create("AM.store.icode4.system.DomainModelPropertyStore", {asynchronousLoad:false, autoLoad:false, pageSize: 1000,})
                    ,store:{type:'systemDomainModelPropertyStore',asynchronousLoad:false, autoLoad:false, pageSize: 1000}
                    ,displayField:'name'
                    ,valueField:'id'
                    ,allowBlank:false
                }
            }
            ,{
                xtype: 'gridcolumn'
                ,flex:1
                ,dataIndex: 'memo'
                ,text: '备注'
		        ,editor: {
			        xtype: 'textfield'
		        }
            }

            ,{
                xtype: 'actioncolumn'
                ,menuDisabled: true
                ,width:30
                ,items: [{
                    iconCls: 'delete'
                    ,tooltip: '删除'
                    ,handler: function(grid, rowIndex, colIndex) {
                        var record = grid.getStore().getAt(rowIndex);
                        me.getSelectionModel().deselectAll()
                        me.getSelectionModel().select(record)
                        me.onDeleteButtonClick();
                    }
                }]
            }]
            ,viewConfig: {
		        plugins: {
			        ddGroup: 'systemTree-to-other'
			        ,ptype: 'gridviewdragdrop'
			        ,enableDrag: false
			        ,dropZone:{
				       handleNodeDrop : function(data, record, position) {
					        var view = this.view,
						        store = view.getStore(),
						        crossView = view !== data.view,
						        index, records, i, len;

					        console.log('position:'+position);
                            console.log(position);
					        console.log("record:"+record)
                            console.log(record)
					        // If the copy flag is set, create a copy of the models
					        var gridRecords = [];
					        records = data.records;

					        for (i = 0, len = records.length; i < len; i++) {
								//console.log(records);

						        if('DOMAIN_MODEL' == records[i].get('type') ){

							        gridRecords[i] = AM.model.icode4.system.DomainModelProperty.create({
								        domainModelId: me.domainModel.get('id')
								        , relatedDomainModelId: records[i].get('id')
								        , relatedDomainModelName: records[i].get('name')
								        , relatedDomainModelPropertyId: "-1"
								        , relatedDomainModelPropertyName: ""
								        , name: records[i].get('name')
								        , code: Ext.String.uncapitalize(records[i].get('code'))
								        //, type: 'String'
                                        , type: records[i].get('code')
								        , persist: false
								        , editable: false
								        , nullable: true
                                        , primaryKey: false
                                        , search: true
                                        , visible: false
								        , viewIndex: '99'

							        });
							        records[i] = records[i].copy();
							        console.log(records[i].get('code')+":"+Ext.String.uncapitalize(records[i].get('code')))
						        }
					        }

                            if (record && position) {
                                index = store.indexOf(record);

                                // 'after', or undefined (meaning a drop at index -1 on an empty View)...
                                if (position !== 'before') {
                                    index++;
                                }
                            }
					        // store.insert(index, data.records);
                            store.insert(index, gridRecords);

					        //view.getSelectionModel().select(gridRecords);


					        // Focus the first dropped node.
					        view.getNavigationModel().setPosition(gridRecords[0]);
				        }
			        }
		        }
            }
            ,dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button'
                            ,iconCls: 'x-fa fa-plus-circle'
                            ,text: '新增'
                            ,listeners: {
                                click: {
                                    fn: me.onAddButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'x-fa fa-minus-circle red'
                            ,text: '删除'
                            ,listeners: {
                                click: {
                                    fn: me.onDeleteButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'text_list_numbers'
                            ,text: '编号'
                            ,listeners: {
                                click: {
                                    fn: me.onReIndexButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'key_add'
                            ,text: '添加主键'
                            ,listeners: {
                                click: {
                                    fn: me.onAddKeyButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'key_add'
                            ,text: '添加SaaS属性'
                            ,listeners: {
                                click: {
                                    fn: me.onAddSaaSKeyButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,conCls: 'x-fa fa-eye green'
                            ,text: '全可见'
                            ,listeners: {
                                click: {
                                    fn: me.onMakeVallPropertyVisiblableButtonClick
                                    ,scope: me
                                }
                            }
                        }

                    ]
                },
                {
                    xtype: 'pagingtoolbar'
                    ,dock: 'bottom'
                    ,displayInfo: true
                }
            ]
	        ,plugins: [
                new Ext.grid.plugin.CellEditing({
                    clicksToEdit: 1
                    ,listeners: {
                        beforeedit: {
                            fn:function(editor, context, eOpts){
                                var grid = context.grid;
                                var record = context.record;
                                var column = context.column;
                                var colIdx = context.colIdx
                                // var rowIdx = context.rowIdx;

                                // console.log(rowIdx);
                                // if(rowIdx == 1)
                                //    return false;
                                console.log(Ext.create('Ext.data.identifier.Uuid').generate())
                                console.log("dataIndex:"+column.dataIndex)
                                //
                                //record.get('type')


                                if('relatedDomainModelPropertyId' == column.dataIndex){

                                    //不是简单类型时,ID长度大于15
                                    if(!record.get('type') || record.get('type').length < 15){
                                        record.set('relatedDomainModelPropertyName' ,null)
                                        record.set('relatedDomainModelPropertyId' ,null)
                                        record.set('relatedDomainModelId', null);
                                        console.log("set relatedDomainModelId null")
                                        return false;
                                    }
                                    console.log(record.get("code"))
                                    record.set('relatedDomainModelId', record.get('type'));
                                    //指定对象后第一次才选
                                    var targetEditor = column.getEditor();
                                    // var targetEditor = editor;

                                    var extraParams = targetEditor.getStore().proxy.extraParams;
                                    console.log("extraParams")
                                    console.log("type:"+record.get('type'))
                                    console.log(extraParams)
                                    if(!extraParams || !extraParams.searchCondition||extraParams.searchCondition.domainModelId != record.get('type')){
                                        console.log(1)
                                        console.log(targetEditor.getStore())
                                        targetEditor.getStore().proxy.extraParams = {searchCondition:{domainModelId:record.get('type')}}
                                        //第一次编辑的时候，会自动触发一次load，所以这里必须也设置一下，否则发出一次
                                        //targetEditor.getStore().proxy.isSynchronous=true

                                        var s = function(combo, row){
                                            console.log("select:"+record.get("name"));
                                            record.set('relatedDomainModelPropertyName' ,row.get('name'))
                                            //每次选择完了以后,要把自己这个监听器移除掉
                                            targetEditor.un('select', s)
                                        }

                                        targetEditor.on('select', s);

                                        //column.getEditor().getStore().load({params:{searchCondition:{domainModelId:record.get('type')}}});
                                        targetEditor.getStore().load();

                                    }


                                }
                            }
                        }
                    }
                })

	        ]
            ,selModel: 'checkboxmodel'
            ,listeners: {
                beforeshow: {
                    fn: me.onBeforeShow
                    ,scope: me
                }
                ,beforehide: {
                    fn: me.onPanelBeforeHide
                    ,scope: me
                }
                ,itemdblclick: {
                    fn: me.onItemDblClick
                    ,scope: me
                }
            }
        });
        me.callParent(arguments);
    }
	,onAddButtonClick: function(button, e, options) {

    	var me = this;

    	var count = me.getStore().getCount();

		// Create a model instance
		var r = Ext.create('AM.model.icode4.system.DomainModelProperty', {
			domainModelId: me.domainModel.get('id')
			, type:'String'
			, editable:true
			, nullable:true
			, persist:true
            , visible: true
            , primaryKey: false
            , search: true
			, viewIndex:count+1

		});

		me.getStore().insert(me.getStore().getCount(), r);
		me.findPlugin('cellediting').startEdit(r, 2);


    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改领域对象属性信息');
    }
    ,onDeleteButtonClick: function(button, e, options) {
        var me = this;
        var selections = me.getSelectionModel( ).getSelection( );
        me.getStore().remove(selections);
        me.getStore().sync({
            success:function(batch,options){

                var store = options.scope;
		        var count = store.getCount();

		        var targetPage = count<=0 ? store.currentPage-1 : store.currentPage;
		        targetPage = targetPage <=0 ? 1 :targetPage;

                store.loadPage(targetPage,{
                    scope: this,
                    callback: function(records, operation, success) {
                        if(!success)
                        	Ext.Msg.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.show('操作成功','删除领域对象属性成功!');
                    }
                });
            }
            ,scope:me.getStore()
        });
    }
	,onReIndexButtonClick: function () {
        var me = this;
        var i = 0;
        me.getStore().each(function (record) {
			record.set("viewIndex", ++i);
			console.log(record)
            record.save();
        })
        // me.getStore().sync({
        //
        // })
    }
    ,onAddKeyButtonClick: function () {
        var me = this;
        var hasKey = false;
        me.getStore().each(function (record) {
            var primaryKey = record.get("primaryKey");

            if(primaryKey){
            	hasKey = true;
			}
        })
		if(!hasKey){
            var r = Ext.create('AM.model.icode4.system.DomainModelProperty', {
                domainModelId: me.domainModel.get('id')
                , name:'主键'
                , code:'id'
                , type:'String'
                , editable:false
                , nullable:false
                , persist:true
                , visible: false
                , primaryKey: true
                , search: false
                , viewIndex:'1'

            });
            me.getStore().insert(0, r);
		}
        // me.getStore().sync({
        //
        // })
    }
    ,onAddSaaSKeyButtonClick: function () {
        var me = this;
        me.onAddKeyButtonClick();
        var hasTid = false;
        me.getStore().each(function (record) {
            hasTid = hasTid || record.get('code') == 'tid';
        })

        if(!hasTid) {
            var tid = Ext.create('AM.model.icode4.system.DomainModelProperty', {
                domainModelId: me.domainModel.get('id')
                , name: '租户id'
                , code: 'tid'
                , type: 'Long'
                , editable: false
                , nullable: false
                , persist: true
                , visible: false
                , primaryKey: false
                , search: true
                , viewIndex: '2'

            });
            me.getStore().insert(1, tid);
        }
    }
        // me.getStore().sync({
        //
        // })
    ,onMakeVallPropertyVisiblableButtonClick: function () {
        var me = this;

        me.getStore().each(function (record) {
           // if(record.get('code') != 'tid' && !record.get("primaryKey"))
            record.set("visible", true);
        })

        if(!hasTid) {
            var tid = Ext.create('AM.model.icode4.system.DomainModelProperty', {
                domainModelId: me.domainModel.get('id')
                , name: '租户id'
                , code: 'tid'
                , type: 'Long'
                , editable: false
                , nullable: false
                , persist: true
                , visible: false
                , primaryKey: false
                , search: true
                , viewIndex: '2'

            });
            me.getStore().insert(1, tid);
        }
    }
    ,setStore: function(store) {
        this.reconfigure(store);
        this.down('pagingtoolbar').bindStore(store);

        this.store=store;
    }
    ,onBeforeShow:function(abstractcomponent, options) {

    	if(this.store.loadCount >0) {
		    this.store.reload({
			    scope: this, callback: function () {
			    }
		    });
	    }
    }
    ,onPanelBeforeHide: function(abstractcomponent, options) {
    	var me = this;

    	if(me.searchWindow){
    		me.searchWindow.hide();
    	}
    	if(me.detailWindow){
    		me.detailWindow.hide();
    	}
    	if(me.editWindow){
    		me.editWindow.hide();
    	}
    	if(me.addWindow){
    		me.addWindow.hide();
    	}
    }
	,setDomainModel: function(domainModel) {
		// var searchCondition = Ext.JSON.encode({
		// 	domainModelId : domainModel.get('id')
		// });
		var me = this;
		this.domainModel = domainModel;
		var searchCondition = {
			domainModelId : domainModel.get('id')
		};

		//如果是新对象,则默认添加一个逐渐属性
		console.log(domainModel.phantom)

		//这里这样处理，是为了避免新增属性的时候把searchConditon带上了
		this.store.on('beforeload', function(){
			me.store.proxy.extraParams = {searchCondition: searchCondition};
		})

        this.store.on('load', function(){
			me.store.proxy.extraParams = null;
		})

        this.store.proxy.isSynchronous = true;
		// this.store.proxy.extraParams = {searchCondition: searchCondition};

        this.store.load({
			params: {
				start: 0
				,page: 0
			}
			,callback:function(){
                if(domainModel.phantom){

                    var r = Ext.create('AM.model.icode4.system.DomainModelProperty', {
                        domainModelId: me.domainModel.get('id')
                        , name:'主键'
                        , code:'id'
                        , type:'String'
                        , editable:false
                        , nullable:false
                        , persist:true
                        , visible: false
                        , primaryKey: true
                        , search: false
                        , viewIndex:'1'

                    });

                    me.getStore().insert(0, r);
                }
			}
		});


		var moduleId = domainModel.get('moduleId');

		this.down('#typeCombo').getEditor().getStore().proxy.extraParams = {moduleId:moduleId}
		this.down('#typeCombo').getEditor().getStore().load();
	}
	,setDomainChildModel: function(domainChildModel) {
		// var searchCondition = Ext.JSON.encode({
		// 	domainModelId : domainModel.get('id')
		// });
		var me = this;
		var domainModelId = domainChildModel.get('domainModelId');
		this.domainModel = domainChildModel;
		var searchCondition = {
			domainModelId : domainChildModel.get('id')
		};

		//这里这样处理，是为了避免新增属性的时候把searchConditon带上了
		this.store.on('beforeload', function(){
			me.store.proxy.extraParams = {searchCondition: searchCondition};
		})
		this.store.on('load', function(){
			me.store.proxy.extraParams = null;
		})
		this.store.proxy.isSynchronous = true;
		// this.store.proxy.extraParams = {searchCondition: searchCondition};
		this.store.load({
			params: {
				start: 0
				,page: 0
			}
		});


		this.down('#typeCombo').getEditor().getStore().proxy.extraParams = {domainModelId:domainModelId}
		this.down('#typeCombo').getEditor().getStore().load();
	}

});