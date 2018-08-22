Ext.define('AM.view.platform.platform.tenant.TenantTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'platform.platform.tenant.TenantTypeMainPanel'
    ,requires: [
        'AM.view.platform.platform.tenant.TenantTypePanel'
		,'AM.view.platform.platform.tenant.TenantTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '租户类型'
	,store:null
	,controller: 'platform.tenant_TenantTypeController'
    ,initComponent: function() {
        var me = this;

		var tenantTypeStore = Ext.create('AM.store.platform.platform.tenant.TenantTypeStore');
		tenantTypeStore.proxy.extraParams={searchCondition:{}};
		tenantTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'platform.platform.tenant.TenantTypePanel'
				    ,region: 'center'
				    ,itemId: 'tenantTypeGrid'
					,reference: 'tenantTypeGrid'
				    ,store: tenantTypeStore
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
		var panel = this.lookup('tenantTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('tenantTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var tenantTypeStore = Ext.create('AM.store.platform.platform.tenant.TenantTypeStore');
		tenantTypeStore.proxy.extraParams={searchCondition:{}};
		me.tenantTypeStore = tenantTypeStore;
		tenantTypeStore.load();
	}

});