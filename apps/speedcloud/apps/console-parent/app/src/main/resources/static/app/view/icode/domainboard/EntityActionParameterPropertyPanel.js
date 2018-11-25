Ext.define('AM.view.icode.domainboard.EntityActionParameterPropertyPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'icode.domainboard.EntityActionParameterPropertyPanel'
    , alias: 'widget.icode.domainboard.EntityActionParameterPropertyPanel'
    , title: '领域对象行为参数属性'
    , bodyCls: 'app-dashboard'
    // , bodyPadding: '10 10'
    , layout: 'border'
    , requires: [
        'AM.view.icode.domainboard.EntityActionParameterPropertyController'
        ,'AM.store.icode.domain.EntityActionParameterStore'
        ,'AM.store.icode.domain.PropertyTypeStore'
    ]
    ,controller: 'icode.domainboard.EntityActionParameterPropertyController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.icode.domain.EntityActionParameterPropertyStore').load()
                    ,actionParameterStore:{type:'icode.domain.EntityActionParameterStore'}
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {
        var me = this;
        me.enableBubble('createMainTabPanel');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'numbercolumn'
                            ,dataIndex: 'idx'
                            ,format:'0,000'
                            ,text: '排序'
                            ,editor: 'numberfield'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '属性名称'
                            ,editor: {
                                xtype: 'textfield'
                            }
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '属性代码'
                            ,editor: 'textfield'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '类型'
                            ,editor: new Ext.form.field.ComboBox({
                                typeAhead: true
                                ,triggerAction: 'all'
                                ,displayField:'name'
                                ,valueField:'code'
                                ,store:{type:'icode.domain.PropertyTypeStore'}
                                ,value : 'String'
                            })
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'wrapperType'
                            ,text: '集合类型'
                            ,editor: new Ext.form.field.ComboBox({
                                editable:false
                                ,defaultValue:''
                                ,store: [
                                    [null,'NULL']
                                    ,['List','List']
                                ]
                                ,value : true
                            })
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'memo'
                            ,text: '备注'
                            ,flex:1
                            ,editor: 'textfield'
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
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onDeleteButtonClick();
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
                                    xtype: 'combobox'
                                    ,bind:{store:'{actionParameterStore}'}
                                    ,typeAhead:false
                                    ,editable:true
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly: false
                                    ,allowBlank: true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'tplSetField'
                                    ,name: 'tplSet'
                                    ,emptyText: '入参对象代码'
                                }
                                ,'-'
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-plus-circle'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: 'onAddButtonClick'
                                    }
                                }

                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-minus-circle red'
                                    ,text: '删除'
                                    ,listeners: {
                                        click: 'onDeleteButtonClick'
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
                                        console.log(Ext.create('Ext.data.identifier.Uuid').generate())
                                        console.log("dataIndex:"+column.dataIndex)



                                    }
                                }
                            }
                        })
                    ]
                    ,selModel: 'checkboxmodel'
                    ,listeners: {}
                }
            ]
            ,listeners: {
            	beforeshow: {
                    fn: me.onBeforeShow
                    ,scope: me
                }
              	,beforehide: {
                	fn: me.onPanelBeforeHide
                  	,scope: me
				}
			}
        });


        me.callParent(arguments);
    }
    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
    ,setParameter:function(model){
        var me = this;
        me.getViewModel().set('entityActionParameter', model);
        me.getViewModel().getStore('store').applyCondition({actionParameter:model.getId()}).load()
    }
});