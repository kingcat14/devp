Ext.define('AM.view.icode4.system.DomainModelMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.DomainModelMainPanel'
    ,requires: [
        'AM.view.icode4.system.DomainModelPanel'
		,'AM.view.icode4.system.DomainModelController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '领域根对象'
	,store:null
	,controller: 'system_DomainModelController'
    ,initComponent: function() {
        var me = this;

		var domainModelStore = Ext.create('AM.store.icode4.system.DomainModelStore');
		domainModelStore.proxy.extraParams={searchCondition:{}};
		domainModelStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemDomainModelPanel'
				    ,region: 'center'
				    ,itemId: 'domainModelGrid'
					,reference: 'domainModelGrid'
				    ,store: domainModelStore
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
		var panel = this.lookup('domainModelGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('domainModelGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var domainModelStore = Ext.create('AM.store.icode4.system.DomainModelStore');
		domainModelS.proxy.extraParams={searchCondition:{}};
		me.domainModelStore = domainModelStore;
		domainModelStore.load();
	}

});