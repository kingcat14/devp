Ext.define('AM.view.speedcloud.app.AppBaseInfoPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.app.AppBaseInfoPanel'
    , alias: 'widget.speedcloud.app.AppBaseInfoPanel'
    , title: '应用（系统）'
    , bodyCls: 'app-dashboard'
    , bodyPadding: '5'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.app.AppBaseInfoController'
        ,'AM.store.speedcloud.app.AppBaseInfoStore'
        ,'AM.store.speedcloud.project.ProjectStore'
        ,'AM.view.speedcloud.app.AppBaseInfoEditPanel'
        ,'AM.view.speedcloud.app.CodeRepositoryEditPanel'
        ,'AM.view.speedcloud.app.AppDevelopConfigEditPanel'
    ]
    ,controller: 'speedcloud.app.AppBaseInfoController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.speedcloud.app.AppBaseInfoStore', {autoLoad:true, pageSize:100}).applyCondition({project:-999})
                    ,projectStore:Ext.create('AM.store.speedcloud.project.ProjectStore', {autoLoad:true, pageSize:100}).load()
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
                    xtype: 'toolbar'
                    ,region:'north'
                    ,height:50
                    ,split: true
                    ,items:[
                        {
                            xtype: 'combobox'
                            ,hideLabel: false
                            ,fieldLabel: '当前产品'
                            ,labelAlign:'right'
                            ,bind:{store:'{projectStore}'}

                            // }
                            // ,store:Ext.create('AM.store.speedcloud.project.ProjectStore', {autoLoad:true}).load()
                            ,displayField: 'name'
                            ,valueField:'id'
                            ,triggerAction: 'all'
                            ,selectOnFocus: false
                            ,forceSelection: true
                            ,editable:false
                            ,indent: true
                            ,allowBlank: false
                            ,allowEmpty: false
                            ,reference:'currentProject'
                            ,listeners:{
                                change:'productChange'
                            }
                        }
                        ,'<--请选择产品'
                    ]
                }
                ,{
                    xtype: 'grid'
                    ,region:'west'
                    ,width: 300
                    ,frame: true
                    ,split: true
                    , collapsible: true
                    , title: '应用列表'
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,reference:'mainGridPanel'
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '类型'
                            ,flex: 1
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '名称'
                            ,flex: 1
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-minus-circle red'
                                ,tooltip: '删除'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    grid.getSelectionModel().deselectAll()
                                    grid.getSelectionModel().select(record)
                                    me.getController().onDeleteButtonClick();
                                }
                            }]
                        }
                    ]
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
                            ]
                        }

                    ]
                    ,selModel: 'checkboxmodel'
                    ,listeners: {select:'onAppRowSelect'}
                }
                ,{
                    xtype:'tabpanel'
                    ,region:'center'
                    , frame: false
                    , plain: true
                    , items:[
                        {
                            xtype:'speedcloud.app.AppBaseInfoEditPanel'
                            // ,title: '详情'
                            ,reference:'detailPanel'
                            ,listeners:{
                                saved:'baseInfoSaved'
                            }

                        }
                        ,{
                            xtype:'speedcloud.app.CodeRepositoryEditPanel'
                            ,title: '代码库'
                            ,reference:'codeRepositoryEditPanel'
                        }
                        ,{
                            xtype:'speedcloud.app.AppDevelopConfigEditPanel'
                            ,title: '开发配置信息'
                            ,reference:'appDevelopConfigEditPanel'
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