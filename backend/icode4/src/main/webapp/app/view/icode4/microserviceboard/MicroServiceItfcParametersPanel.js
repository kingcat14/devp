Ext.define('AM.view.icode4.microserviceboard.MicroServiceItfcParametersPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.microserviceboard.MicroServiceItfcParametersPanel'
    ,title: '接口参数'
	,collapsible:true
	,requires:[
		'Ext.tree.plugin.TreeViewDragDrop'
		,'AM.model.icode4.system.DomainModelProperty'
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
			        ,text: '排序'
			        ,editor: {
				        xtype: 'numberfield'
			        }
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'name'
			        ,text: '参数名称'
			        ,editor: {
				        xtype: 'textfield'
			        }
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'code'
			        ,text: '代码'
			        ,editor: {
				        xtype: 'textfield'
			        }
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'type'
			        ,text: '参数类型'
			        ,editor: new Ext.form.field.ComboBox({
				        typeAhead: true
				        ,triggerAction: 'all'
				        ,displayField:'name'
				        ,valueField:'code'
				        ,store:Ext.create('AM.store.icode4.system.PropertyTypeStore', {autoLoad:true, asynchronousLoad:false})
				        ,value : 'String'
			        })
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'pathMapping'
			        ,text: '路径参数映射'
			        ,editor: {
				        xtype: 'textfield'
			        }
		        }

		        ,{
			        xtype: 'gridcolumn'
			        ,dataIndex: 'memo'
			        ,text: '备注'
			        ,editor: {
				        xtype: 'textfield'
			        }
		        }
		        ,{
			        xtype: 'booleancolumn'
			        ,dataIndex: 'required'
			        ,trueText: '是'
			        ,falseText: '否'
			        ,emptyCellText :'不确定'
			        ,text: '必填'
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
                }
                // ,{
                //     xtype: 'pagingtoolbar'
                //     ,dock: 'bottom'
                //     ,displayInfo: true
                // }
            ]
	        ,plugins: [
		        {
			        ptype: 'cellediting'
			        ,clicksToEdit: 1
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
		var r = Ext.create('AM.model.icode4.microservice.MicroServiceItfcParameters', {
			microServiceItfcId: me.microServiceItfc.get('id')
			,type:'String'
			,required:true
			,viewIndex:99

		});

		me.getStore().insert(me.getStore().getCount(), r);
		me.findPlugin('cellediting').startEdit(r, 2);


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
        //this.down('pagingtoolbar').bindStore(store);

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
	,setModel: function(microServiceItfc) {
		// var searchCondition = Ext.JSON.encode({
		// 	domainModelId : domainModel.get('id')
		// });
		var me = this;
		this.microServiceItfc = microServiceItfc;
		var searchCondition = {
			microServiceItfcId : microServiceItfc.get('id')
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