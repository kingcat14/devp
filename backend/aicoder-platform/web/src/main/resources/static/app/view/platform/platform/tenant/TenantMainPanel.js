Ext.define('AM.view.platform.platform.tenant.TenantMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'platform.platform.tenant.TenantMainPanel'
    ,requires: [
        'AM.view.platform.platform.tenant.TenantPanel'
		,'AM.view.platform.platform.tenant.TenantController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '租户'
	,store:null
	,controller: 'platform.tenant_TenantController'
    ,initComponent: function() {
        var me = this;

		var tenantStore = Ext.create('AM.store.platform.platform.tenant.TenantStore');
		tenantStore.proxy.extraParams={searchCondition:{}};
		tenantStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'platform.platform.tenant.TenantPanel'
				    ,region: 'center'
				    ,itemId: 'tenantGrid'
					,reference: 'tenantGrid'
				    ,store: tenantStore
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
		var panel = this.lookup('tenantGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('tenantGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var tenantStore = Ext.create('AM.store.platform.platform.tenant.TenantStore');
		tenantStore.proxy.extraParams={searchCondition:{}};
		me.tenantStore = tenantStore;
		tenantStore.load();
	}

});