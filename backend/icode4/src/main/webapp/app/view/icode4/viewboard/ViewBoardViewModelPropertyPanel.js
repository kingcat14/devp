Ext.define('AM.view.icode4.viewboard.ViewBoardViewModelPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.viewboard.ViewBoardViewModelPropertyPanel'
    ,title: '展现对象属性'
	,requires:[
		'Ext.tree.plugin.TreeViewDragDrop'
		,'AM.model.icode4.view.ViewModelProperty'
		,'AM.store.icode4.system.PropertyTypeStore'
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
		        ,{
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
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'viewModelId'
			        ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				        return record.get("viewModelName")?record.get("viewModelName"):'';
			        }

			        ,text: '所属展现对象'
		        }

		        ,{
			        xtype: 'booleancolumn'
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
			        xtype: 'gridcolumn'
			        ,dataIndex: 'gridIndex'
			        ,text: '列表排序'
			        ,editor: {
				        xtype: 'numberfield'
			        }
		        }

		        ,{
			        xtype: 'booleancolumn'
			        ,dataIndex: 'gridHidden'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'
			        ,text: '列表隐藏'
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
			        ,dataIndex: 'formIndex'
			        ,text: '表单排序'
			        ,editor: {
				        xtype: 'numberfield'
			        }
		        }

		        ,{
			        xtype: 'booleancolumn'
			        ,dataIndex: 'formHidden'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'
			        ,flex:1
			        ,text: '表单隐藏'
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
			        ,dataIndex: 'quickSearchCondition'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'
			        ,text: '是否快速查询条件'
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
			        ,dataIndex: 'searchCondition'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'

			        ,text: '是否查询条件'
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
			        ,dataIndex: 'propertyGroup'
			        ,text: '属性组'
			        ,editor: {
				        xtype: 'textfield'
			        }
		        }
		        ,{
			        xtype: 'booleancolumn'
			        ,dataIndex: 'coreRelation'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'
			        ,text: '核心关联'
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
			        ,dataIndex: 'domainModelId'
			        ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				        return record.get("domainModelName")?record.get("domainModelName"):'';
			        }
			        ,text: '关联对象'
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'domainModelPropertyId'
			        ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				        return record.get("domainModelPropertyName")?record.get("domainModelPropertyName"):'';
			        }
			        ,text: '关联对象属性'
		        }

		        ,{
			        xtype: 'actioncolumn'
			        ,menuDisabled: true
			        ,width:30
			        ,items: [{
				        iconCls: 'x-fa fa-eye'
				        ,tooltip: '详情'
				        ,handler: function(grid, rowIndex, colIndex) {
					        var record = grid.getStore().getAt(rowIndex);
					        me.getSelectionModel().deselectAll()
					        me.getSelectionModel().select(record)
					        me.showDetailWindow(record, this);
				        }
			        }]
		        }
		        ,{
			        xtype: 'actioncolumn'
			        ,menuDisabled: true
			        ,width:30
			        ,items: [{
				        iconCls: 'edit'
				        ,tooltip: '修改'
				        ,handler: function(grid, rowIndex, colIndex) {
					        var record = grid.getStore().getAt(rowIndex);
					        me.getSelectionModel().deselectAll()
					        me.getSelectionModel().select(record)
					        me.showEditWindow(record, this);
				        }
			        }]
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
		        }
	        ]
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

					        // If the copy flag is set, create a copy of the models
					        var gridRecords = [];
					        records = data.records;


					        for (i = 0, len = records.length; i < len; i++) {

								if('PROPERTY' == records[i].get('type')){

									console.log(records[i].parentNode)
							        console.log(Ext.String.capitalize(records[i].get('code')))

							        // records[i] = records[i].copy();
							        gridRecords[i] = AM.model.icode4.view.ViewModelProperty.create({
								        viewModelId: me.viewModel.get('id')
								        ,viewModelName: me.viewModel.get('name')
								        ,coreRelation: false
								        ,domainModelId: records[i].parentNode.get('objectId')
								        ,domainModelName:records[i].parentNode.get('name')
								        ,domainModelPropertyId: records[i].get('objectId')
								        ,domainModelPropertyName: records[i].get('name')
								        ,propertyGroup: Ext.String.uncapitalize(records[i].parentNode.get('code'))
								        ,name: records[i].parentNode.get('name') + Ext.String.capitalize(records[i].get('name'))
								        ,code: Ext.String.uncapitalize(records[i].parentNode.get('code')) + Ext.String.capitalize(records[i].get('code'))
								        ,type:'String'
								        ,editable: false
								        ,nullable: true
								        ,gridIndex: 99
								        ,gridHidden: true
								        ,formIndex: 99
								        ,formHidden: true
								        ,searchCondition:false
								        ,quickSearchCondition:false
								        ,viewIndex:'99'

							        });
									records[i] = records[i].copy();
								}

						        if('DOMAIN_MODEL' == records[i].get('type')){

							        console.log(records[i].parentNode)
							        console.log(Ext.String.capitalize(records[i].get('code')))

							        // records[i] = records[i].copy();
							        gridRecords[i] = AM.model.icode4.view.ViewModelProperty.create({
								        viewModelId: me.viewModel.get('id')
								        ,viewModelName: me.viewModel.get('name')
								        ,coreRelation: true
								        ,domainModelId: records[i].get('objectId')
								        ,domainModelName:records[i].get('name')
								        ,domainModelPropertyName: "id"
								        ,domainModelPropertyId: "-1"
								        ,propertyGroup: Ext.String.uncapitalize(records[i].get('code'))
								        ,name: records[i].get('name')+"Id"
								        ,code: Ext.String.uncapitalize(records[i].get('code'))+"Id"
								        ,type:'String'
								        ,editable: true
								        ,nullable: true
								        ,gridIndex: 99
								        ,gridHidden: false
								        ,formIndex: 99
								        ,formHidden: false
								        ,searchCondition:true
								        ,quickSearchCondition:false
								        ,viewIndex:'99'

							        });
							        records[i] = records[i].copy();
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
                            ,iconCls: 'x-fa fa-minus-circle'
                            ,text: '删除'
                            ,listeners: {
                                click: {
                                    fn: me.onDeleteButtonClick
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
		        {
			        ptype: 'cellediting',
			        clicksToEdit: 1
		        }

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

		// Create a model instance
		var r = Ext.create('AM.model.icode4.view.ViewModelProperty', {
			domainModelId: me.viewModel.get('id')
			,type:'String',
			persist:true,
			editable:true,
			nullable:true,
			persist:true,
			required:true,
			viewIndex:'99'

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

    ,setStore: function(store) {
        this.reconfigure(store);
        this.down('pagingtoolbar').bindStore(store);

        this.store=store;
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    this.store.reload({scope: this,callback: function(){}});
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
	,setViewModel: function(viewModel) {
		// var searchCondition = Ext.JSON.encode({
		// 	domainModelId : domainModel.get('id')
		// });
		var me = this;
		this.viewModel = viewModel;
		var searchCondition = {
			viewModelId : viewModel.get('id')
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
	}

});