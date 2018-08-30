Ext.define('AM.view.speedcloud.app.SecurityConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.SecurityConfigMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.SecurityConfigPanel'
		,'AM.view.speedcloud.app.SecurityConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用私密配置'
	,store:null
	,controller: 'app_SecurityConfigController'
    ,initComponent: function() {
        var me = this;

		var securityConfigStore = Ext.create('AM.store.speedcloud.app.SecurityConfigStore');
		securityConfigStore.proxy.extraParams={searchCondition:{}};
		securityConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.SecurityConfigPanel'
				    ,region: 'center'
				    ,itemId: 'securityConfigGrid'
					,reference: 'securityConfigGrid'
				    ,store: securityConfigStore
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
		var panel = this.lookup('securityConfigGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('securityConfigGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var securityConfigStore = Ext.create('AM.store.speedcloud.app.SecurityConfigStore');
		securityConfigStore.proxy.extraParams={searchCondition:{}};
		me.securityConfigStore = securityConfigStore;
		securityConfigStore.load();
	}

});