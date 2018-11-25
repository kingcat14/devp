Ext.define('AM.view.icode.domainboard.DomainBoardEntityPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode.domainboard.DomainBoardEntityPropertyPanel'
    ,title: '对象属性'
	,requires:[
		'AM.model.icode.domain.EntityProperty'
		,'AM.store.icode.domain.EntityPropertyStore'
        ,'AM.store.icode.domain.ReferencePropertyTypeStore'
	]
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    component: null
                    ,entity: null
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [
            	{
		        xtype: 'numbercolumn'
		        ,dataIndex: 'idx'
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
			        ,store:Ext.create('AM.store.icode.domain.ReferencePropertyTypeStore', {autoLoad:false, asynchronousLoad:false/*, sorters: 'name'*/})
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
                xtype: 'booleancolumn'
                ,width:70
                ,dataIndex: 'sortable'
                ,trueText: '是'
                ,falseText: '否'
                ,emptyCellText :'不确定'
                ,text: '可排序'

                ,editor: new Ext.form.field.ComboBox({
                    editable:false
                        ,defaultValue:false
                    ,store: [
                        [true,'是']
                        ,[false,'否']
                    ]
                    ,value : true
                })
            }
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'relatedEntityId'
                ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                    return record.get("relatedEntityName")?record.get("relatedEntityName"):'';
                }
                ,text: '关联对象'
                ,hidden:true
            }
            ,{
                xtype: 'gridcolumn'
                ,dataIndex: 'relatedEntityPropertyId'
                ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                    return record.get("relatedEntityPropertyName")?record.get("relatedEntityPropertyName"):'';
                }
                ,text: '展现的关联对象属性'

                ,editor: {
                    xtype:'combo'
                    ,typeAhead: false
                    ,triggerAction: 'all'
                    ,editable: false
                    ,store:{type:'icode.domain.EntityPropertyStore',asynchronousLoad:false, autoLoad:false, pageSize: 1000}
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
                    iconCls: 'fas fa-minus-circle red'
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
                            ,iconCls: 'fas fa-key'
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
                            ,iconCls: 'fas fa-building'
                            ,text: '+ tid'
                            ,listeners: {
                                click: {
                                    fn: me.onAddSaaSKeyButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'fas fa-user'
                            ,text: '+ accountId'
                            ,listeners: {
                                click: {
                                    fn: me.onAddAccountKeyButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'fas fa-smile'
                            ,text: '+ name'
                            ,listeners: {
                                click: {
                                    fn: me.onAddNameButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'fas fa-hashtag'
                            ,text: '+ code'
                            ,listeners: {
                                click: {
                                    fn: me.onAddCodeButtonClick
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
                                    fn: me.onMakeAllPropertyVisiblableButtonClick
                                    ,scope: me
                                }
                            }
                        }

                    ]
                }
                ,{
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


                                if('relatedEntityPropertyId' == column.dataIndex){

                                    //不是简单类型时,ID长度大于15
                                    if(!record.get('type') || record.get('type').length < 15){
                                        //不是负责类型时,不让选择
                                        record.set('relatedEntityPropertyName' ,null)
                                        record.set('relatedEntityPropertyId' ,null)
                                        record.set('relatedEntityId', null);
                                        console.log("set relatedEntityId null")
                                        return false;
                                    }
                                    console.log(record.get("code"))
                                    record.set('relatedEntityId', record.get('type'));
                                    //指定对象后第一次才选
                                    var targetEditor = column.getEditor();
                                    // var targetEditor = editor;

                                    var extraParams = targetEditor.getStore().proxy.extraParams;
                                    console.log("extraParams")
                                    console.log("type:"+record.get('type'))
                                    console.log(extraParams)
                                    if(!extraParams || !extraParams.searchCondition||extraParams.searchCondition.entity != record.get('type')){
                                        // console.log(1)
                                        // console.log(targetEditor.getStore())
                                        targetEditor.getStore().proxy.extraParams = {searchCondition:{entity:record.get('type')}}
                                        //第一次编辑的时候，会自动触发一次load，所以这里必须也设置一下，否则发出一次
                                        //targetEditor.getStore().proxy.isSynchronous=true

                                        var s = function(combo, row){
                                            console.log("select:"+record.get("name"));
                                            record.set('relatedEntityPropertyName' ,row.get('name'))
                                            //每次选择完了以后,要把自己这个监听器移除掉
                                            targetEditor.un('select', s)
                                        }

                                        targetEditor.on('select', s);

                                        //column.getEditor().getStore().load({params:{searchCondition:{entityId:record.get('type')}}});
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
		var r = Ext.create('AM.model.icode.domain.EntityProperty', {
			entityId: me.entity.get('id')
			, type:'String'
			, editable:true
			, nullable:true
			, persist:true
            , visible: true
            , sortable:false
            , primaryKey: false
            , search: true
			, idx:count+1

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
			record.set("idx", ++i);
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
            var r = Ext.create('AM.model.icode.domain.EntityProperty', {
                entity: me.entity.get('id')
                , name:'主键'
                , code:'id'
                , type:'String'
                , editable:false
                , nullable:false
                , persist:true
                , visible: false
                , sortable:false
                , primaryKey: true
                , search: false
                , idx:'1'

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
            var tid = Ext.create('AM.model.icode.domain.EntityProperty', {
                entity: me.entity.get('id')
                , name: '租户id'
                , code: 'tid'
                , type: 'Long'
                , editable: false
                , nullable: false
                , persist: true
                , visible: false
                , sortable:false
                , primaryKey: false
                , search: true
                , idx: '2'

            });
            me.getStore().insert(1, tid);
        }
    }
    ,onAddAccountKeyButtonClick: function () {
        var me = this;
        me.onAddKeyButtonClick();
        var hasAccountId = false;
        me.getStore().each(function (record) {
            hasAccountId = hasAccountId || record.get('code') == 'accountId';
        })

        if(!hasAccountId) {
            var tid = Ext.create('AM.model.icode.domain.EntityProperty', {
                entity: me.entity.get('id')
                , name: '用户id'
                , code: 'accountId'
                , type: 'Long'
                , editable: false
                , nullable: false
                , persist: true
                , visible: false
                , sortable:false
                , primaryKey: false
                , search: true
                , idx: '3'

            });
            me.getStore().insert(2, tid);
        }
    }
    ,onAddCodeButtonClick: function () {
        var me = this;
        me.onAddKeyButtonClick();
        var hasProperty = false;
        me.getStore().each(function (record) {
            hasProperty = hasProperty || record.get('code') == 'code';
        })

        if(!hasProperty) {
            var tid = Ext.create('AM.model.icode.domain.EntityProperty', {
                entity: me.entity.get('id')
                , name: '代码'
                , code: 'code'
                , type: 'String'
                , editable: false
                , nullable: false
                , persist: true
                , visible: true
                , sortable:true
                , primaryKey: false
                , search: true
                , idx: '4'

            });
            me.getStore().insert(3, tid);
        }
    }
    ,onAddNameButtonClick: function () {
        var me = this;
        me.onAddKeyButtonClick();
        var hasProperty = false;
        me.getStore().each(function (record) {
            hasProperty = hasProperty || record.get('code') == 'name';
        })
        console.log('hasProperty:'+hasProperty)
        if(!hasProperty) {
            var tid = Ext.create('AM.model.icode.domain.EntityProperty', {
                entity: me.entity.get('id')
                , name: '名称'
                , code: 'name'
                , type: 'String'
                , editable: true
                , nullable: false
                , persist: true
                , visible: true
                , sortable:false
                , primaryKey: false
                , search: true
                , idx: '5'

            });
            me.getStore().insert(4,tid);
        }
    }
    ,onMakeAllPropertyVisiblableButtonClick: function () {
        var me = this;

        me.getStore().each(function (record) {
           // if(record.get('code') != 'tid' && !record.get("primaryKey"))
            record.set("visible", true);
        })


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
	,setEntity: function(entity) {
		// var searchCondition = Ext.JSON.encode({
		// 	entity : entity.get('id')
		// });
		var me = this;
		this.entity = entity;
		var searchCondition = {
			entity : entity.get('id')
		};

		//如果是新对象,则默认添加一个逐渐属性
		console.log(entity.phantom)

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
                if(entity.phantom){

                    var r = Ext.create('AM.model.icode.domain.EntityProperty', {
                        entity: me.entity.get('id')
                        , name:'主键'
                        , code:'id'
                        , type:'String'
                        , editable:false
                        , nullable:false
                        , persist:true
                        , visible: false
                        , sortable:false
                        , primaryKey: true
                        , search: false
                        , idx:'1'

                    });

                    me.getStore().insert(0, r);
                }
			}
		});



		var component = this.getViewModel().get('component');

		this.down('#typeCombo').getEditor().getStore().proxy.extraParams = {component:component.getId()}
		this.down('#typeCombo').getEditor().getStore().load();
	}


});