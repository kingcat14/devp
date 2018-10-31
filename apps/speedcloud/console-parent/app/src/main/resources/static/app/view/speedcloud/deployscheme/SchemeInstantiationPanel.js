Ext.define('AM.view.speedcloud.deployscheme.SchemeInstantiationPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.SchemeInstantiationPanel'
    , alias: 'widget.speedcloud.deployscheme.SchemeInstantiationPanel'
    , title: '部署方案'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deployscheme.SchemeInstantiationController'
        ,'AM.store.speedcloud.deployscheme.SchemeStore'
    ]
    ,controller: 'speedcloud.deployscheme.SchemeInstantiationController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    record:null
                }
                ,stores:{
                    schemeStore:Ext.create('AM.store.speedcloud.deployscheme.SchemeStore').load()
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
                    ,bind:{store:'{schemeStore}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:35
                            ,items: [{
                                iconCls: 'fab fa-connectdevelop black'
                                ,tooltip: '详情'
                                ,handler: function(grid, rowIndex, colIndex) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onDetailButtonClick();
                                }
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '方案名称'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '方案代码'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '方案别名'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,text: '方案类型'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'version'
                            ,text: '版本'
                            
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'verPostfix'
                            ,text: '版本标识后缀'
                            
                        }
                        ,{
                            xtype: 'booleancolumn'
                            ,dataIndex: 'status'
                            ,trueText: '是'
                            ,falseText: '否'
                            ,emptyCellText :'不确定'
                            ,text: '已生效'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'project'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("projectVO")?record.get("projectVO").name:'';
                            }
                            ,text: '所属项目（产品）'
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'env'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("envVO")?record.get("envVO").name:'';
                            }
                            ,text: '所属环境'
                            ,flex:1
                        }
                    ]
                    ,viewConfig: {

                    }
                    ,dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'方案名称'
                                    ,reference: 'nameField'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,width:120
                                    ,emptyText:'方案代码'
                                    ,reference: 'codeField'
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'search'
                                    ,text: '查询'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
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
});