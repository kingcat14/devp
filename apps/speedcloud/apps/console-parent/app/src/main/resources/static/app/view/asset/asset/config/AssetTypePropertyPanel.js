Ext.define('AM.view.asset.asset.config.AssetTypePropertyPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'asset.asset.config.AssetTypePropertyPanel'
    , alias: 'widget.asset.asset.config.AssetTypePropertyPanel'
    , title: '资产分类属性'
    , layout: 'border'
    , requires: [
        'AM.view.asset.asset.config.AssetTypePropertyController'
        ,'AM.store.asset.asset.config.AssetTypePropertyStore'
        ,'AM.model.asset.asset.config.AssetTypeTreeNode'
    ]
    ,bodyPadding:10
    ,bodyCls: 'app-dashboard'
    ,controller: 'asset.asset.config.AssetTypePropertyController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.asset.asset.config.AssetTypePropertyStore').load()
                    ,assetTypeTreeStore:Ext.create('Ext.data.TreeStore', {autoLoad:false, model:'AM.model.asset.asset.config.AssetTypeTreeNode', nodeParam:'id'})
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
                    xtype: 'treepanel'
                    ,title: '资产分类'
                    ,collapsible:true
                    ,region: 'west'
                    ,width: '30%'
                    ,frame: true
                    ,split: true
                    ,reference: 'assetTypeTree'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,bind:{store: '{assetTypeTreeStore}'}
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'fas fa-sync',
                            handler: 'loadAssetTypeTree'
                        }
                    ]
                    ,columns: [{
                        xtype: 'treecolumn',
                        text: 'Name',
                        dataIndex: 'name',
                        flex: 1,
                        sortable: true,
                        renderer: function(v, metaData, record) {
                            metaData.glyph = record.glyph;
                            return v;
                        }
                    }]
                    ,listeners: {
                        itemclick: 'onAssetTypeItemClick'
                        ,afterrender: 'loadAssetTypeTree'
                    }

                }
                ,{
                    xtype: 'grid'
                    ,region:'center'
                    ,title:'分类属性'
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,frame:true
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:35
                            ,items: [{
                                iconCls: 'x-fa fa-eye'
                                ,tooltip: '详情'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.showDetailWindow(record, this);
                                }
                            }]
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'seq'
                            ,format:'0,000'
                            ,text: '顺序'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'assetType'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("assetTypeVO")?record.get("assetTypeVO").name:'';
                            }
                            ,text: '资产分类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '属性名称'
                            ,flex:1
                            ,editor:{xtype: 'textfield', blankText:'必填字段', emptyText:'必填字段'
                                ,allowBlank:false}

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '属性代码'
                            ,flex:1
                            ,editor:{xtype: 'textfield', blankText:'必填字段', emptyText:'必填字段'
                                ,allowBlank:false}
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '属性类型'
                            ,editor: new Ext.form.field.ComboBox({
                                editable:false
                                ,triggerAction: 'all'
                                ,store: [
                                    ['String','字符']
                                    ,['Long','数字']
                                ]
                                ,value : 'String'
                            })
                            ,renderer: function(value){
                                if (value == 'String') {
                                    return '字符';
                                }else if (value == 'Long') {
                                    return '数字';
                                }else{
                                    return '';
                                }
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
                                ,triggerAction: 'all'
                                ,store: [
                                    [true,'是']
                                    ,[false,'否']
                                ]
                                ,value : false
                            })

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'optionValues'
                            ,text: '备选值'
                            ,editor:'textfield'

                        }

                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-pencil-alt'
                                ,tooltip: '修改'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onEditButtonClick();
                                }
                            }]
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
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-plus-circle'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: 'onAddButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-pencil-alt'
                                    ,text: '修改'
                                    ,listeners: {
                                        click: 'onEditButtonClick'
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
                    ,selModel: 'checkboxmodel'
                    ,plugins: [
                        {
                            ptype: 'rowediting'
                            ,id: 'assetTypePropertyRowEditing'
                            ,clicksToEdit: 2
                            ,saveBtnText:'保存'
                            ,cancelBtnText: '取消'
                            ,dirtyText: "你要确认或取消更改"
                        }
                    ]
                    ,listeners: {
                        edit:function (editor, e) {
                            e.record.save();
                        }
                        ,canceledit: function(editor, e) {
                            if(e.record.phantom){
                                e.record.drop()
                            }
                        }
                    }
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




});