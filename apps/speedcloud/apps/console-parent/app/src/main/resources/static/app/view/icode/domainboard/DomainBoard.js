Ext.define('AM.view.icode.domainboard.DomainBoard', {
    extend: 'Ext.panel.Panel'
    , xtype: 'icode.domainboard.DomainBoard'
    , alias: 'widget.icode.domainboard.DomainBoard'
    , title: '领域模型面板'
    , bodyCls: 'app-dashboard'
	, bodyPadding: '5'
    , layout: 'border'
    , requires: [
        'AM.view.icode.domainboard.DomainBoardController'
        ,'AM.view.icode.domainboard.DomainBoardComponentPanel'
        ,'AM.view.icode.domainboard.DomainBoardDomainPanel'
        ,'AM.view.icode.domainboard.DomainBoardEntityPanel'
        ,'AM.view.icode.domainboard.DomainBoardEntityActionPanel'
        ,'AM.view.icode.domainboard.EntityActionParameterPanel'
        ,'AM.store.icode.domain.DomainTreeStore'
    ]
    ,controller: 'icode.domainboard.DomainBoardController'
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
                    , title: '领域'
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
                            ,listeners:{saved:'reloadTree'}
                        }
                        ,{
                            xtype:'icode.domainboard.DomainBoardDomainPanel'
                            ,reference: 'domainBoardDomainPanel'
                            ,html:"领域"
                            ,listeners:{saved:'reloadTree'}
                        }
                        ,{
                            xtype:'icode.domainboard.DomainBoardEntityPanel'
                            ,reference: 'domainBoardEntityPanel'
                            ,html:"实体"
                            ,listeners:{saved:'reloadTree'}
                        }
                        ,{
                            xtype:'icode.domainboard.DomainBoardEntityActionPanel'
                            ,reference: 'domainBoardEntityActionPanel'
                            ,html:"实体行为"
                            ,listeners:{saved:'reloadTree'}
                        }
                        ,{
                            xtype:'icode.domainboard.EntityActionParameterPanel'
                            ,reference: 'entityActionParameterPanel'
                            ,html:"行为参数"
                            ,listeners:{saved:'reloadTree'}
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
            ,contextMenu:{
                component:Ext.create('Ext.menu.Menu', {
                    items: [
                        ,{text:'删除组件', handler:function(){me.getController().deleteComponentAction()}}
                        ,'-'
                        ,{text:'新增领域', handler:function(){me.getController().addDomainAction()}}
                    ]
                })
                ,domain:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'新增领域', handler:function(){me.getController().addDomainAction()}}
                        ,{text:'删除领域'}
                        ,'-'
                        ,{text:'新增领域对象', handler:function(){me.getController().addEntityAction()}}
                    ]
                })
                ,entity:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'复制对象', iconCls:'fas fa-copy'}
                        ,{text:'删除对象'}
                        ,'-'
                        ,{text:'新增服务', iconCls:'fas fa-plus', handler:function(){me.getController().addEntityActionAction()}}
                    ]
                })
                ,entityAction:Ext.create('Ext.menu.Menu', {
                    items: [
                        {text:'删除服务', iconCls:'fas fa-trash-alt'}
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