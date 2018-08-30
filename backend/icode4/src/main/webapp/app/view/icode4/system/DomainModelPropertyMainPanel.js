Ext.define('AM.view.icode4.system.DomainModelPropertyMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.DomainModelPropertyMainPanel'
    ,requires: [
        'AM.view.icode4.system.DomainModelPropertyPanel'
		,'AM.view.icode4.system.DomainModelPropertyController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '领域对象属性'
	,store:null
	,controller: 'system_DomainModelPropertyController'
    ,initComponent: function() {
        var me = this;

		var domainModelPropertyStore = Ext.create('AM.store.icode4.system.DomainModelPropertyStore');
		domainModelPropertyStore.proxy.extraParams={searchCondition:{}};
		domainModelPropertyStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemDomainModelPropertyPanel'
				    ,region: 'center'
				    ,itemId: 'domainModelPropertyGrid'
					,reference: 'domainModelPropertyGrid'
				    ,store: domainModelPropertyStore
					,listeners: {

						itemdblclick: 'onMainPanelRowClick'
					}
			    } 		    ]
		    ,listeners: {
			    beforeshow: {
				    fn: me.onBeforeShow,
				    scope: me
			    },
			    beforehide: {
				    fn: me.onPanelBeforeHide,
				    scope: me
			    }
		    }
	    });
        me.callParent(arguments);
    }
	,onBeforeShow:function(abstractcomponent, options) {
		var panel = this.lookup('domainModelPropertyGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('domainModelPropertyGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var domainModelPropertyStore = Ext.create('AM.store.icode4.system.DomainModelPropertyStore');
		domainModelPropertyS.proxy.extraParams={searchCondition:{}};
		me.domainModelPropertyStore = domainModelPropertyStore;
		domainModelPropertyStore.load();
	}

});