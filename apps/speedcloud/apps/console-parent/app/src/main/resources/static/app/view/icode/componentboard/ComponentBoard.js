Ext.define('AM.view.icode.componentboard.ComponentBoard', {
    extend: 'Ext.panel.Panel'
    , xtype: 'icode.domainboard.DomainBoard'
    , alias: 'widget.icode.domainboard.DomainBoard'
    , title: '系统组件面板'
    , bodyCls: 'app-dashboard'
	, bodyPadding: '5'
    , layout: 'border'
    , requires: [
        'AM.view.icode.componentboard.ComponentBoardController'
        ,'AM.view.icode.domainboard.DomainBoardComponentPanel'
        ,'AM.view.icode.domainboard.DomainBoardDomainPanel'
        ,'AM.view.icode.domainboard.DomainBoardEntityPanel'
        ,'AM.view.icode.domainboard.DomainBoardEntityActionPanel'
        ,'AM.view.icode.domainboard.EntityActionParameterPanel'
        ,'AM.store.icode.domain.DomainTreeStore'
    ]
    ,controller: 'icode.componentboard.ComponentBoardController'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:{type:'icode.domain.DomainStore'}
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: [
				{
					xtype:'treepanel'
                    , region:'west'
                    , split: true
                    , collapsible: true
                    , width: 250
                    , rootVisible: false
                    , frame: true
                    , displayField: 'name'
                    , title: '组件树'
                    , reference: 'domainTreePanel'
                    , store: Ext.create('AM.store.icode.domain.DomainTreeStore')
                    , tbar:[
                        {
                            xtype:'button'
                            //text:'重新加载',
                            ,iconCls: 'fas fa-redo-alt'
                            ,handler:'reloadTree'
                        }
                    ]
                    ,listeners: {
                        itemclick: 'treeItemClick'
                        , itemcontextmenu: 'treeItemContextMenu'
                    }

				}
                ,{
                    xtype: 'panel'
                    , region:'center'
                    , frame: true
                    , layout:'card'
                    , activeItem:0
                    , reference: 'domainBoardContentPanel'
                    , items: [
                        {
                            xtype:'panel'
                            ,reference: 'domainBoardProductPanel'
                            ,html:"产品"
                        }
                        ,{
                            xtype:'icode.domainboard.DomainBoardComponentPanel'
                            ,reference: 'domainBoardComponentPanel'
                            ,html:"组件"
                        }

                    ]
                }
            ]
            ,listeners: {

            }
            ,contextMenu:{
                component:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'新增领域', iconCls:'fas fa-plus', handler:function(){me.getController().addDomainAction()}}
                    ]
                })
                ,domain:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'新增子领域', iconCls:'fas fa-plus', handler:function(){me.getController().addDomainAction()}}
                        ,{text:'删除领域', iconCls:'fas fa-trash-alt orange'}
                        ,'-'
                        ,{text:'新增领域对象', iconCls:'fas fa-plus', handler:function(){me.getController().addEntityAction()}}
                    ]
                })
                ,entity:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'复制对象', iconCls:'fas fa-copy'}
                        ,'-'
                        ,{text:'删除实体', iconCls:'fas fa-trash-alt orange'}
                        ,'-'
                        ,{text:'新增服务', iconCls:'fas fa-plus', handler:function(){me.getController().addEntityActionAction()}}
                    ]
                })
                ,entityAction:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'删除服务', iconCls:'fas fa-trash-alt orange'}
                        ,'-'
                        ,{text:'设置输入', iconCls:'fas fa-arrow-right', handler:function(){me.getController().setEntityActionParamAction('IN')}}
                        ,{text:'设置输出', iconCls:'fas fa-arrow-left', handler:function(){me.getController().setEntityActionParamAction('OUT')}}
                        ,'-'
                        ,{text:'清除输入', iconCls:'fas fa-minus-circle'}
                        ,{text:'清除输出', iconCls:'fas fa-minus-circle'}
                    ]
                })
            }
        });


        me.callParent(arguments);
    }

});