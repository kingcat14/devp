Ext.define('AM.view.speedcloud.env.AppEnvConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.env.AppEnvConfigMainPanel'
    ,requires: [
        'AM.view.speedcloud.env.AppEnvConfigPanel'
		,'AM.view.speedcloud.env.AppEnvConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用环境'
	,store:null
	,controller: 'env_AppEnvConfigController'
    ,initComponent: function() {
        var me = this;

		var appEnvConfigStore = Ext.create('AM.store.speedcloud.env.AppEnvConfigStore');
		appEnvConfigStore.proxy.extraParams={searchCondition:{}};
		appEnvConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.env.AppEnvConfigPanel'
				    ,region: 'center'
				    ,itemId: 'appEnvConfigGrid'
					,reference: 'appEnvConfigGrid'
				    ,store: appEnvConfigStore
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
		var panel = this.lookup('appEnvConfigGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('appEnvConfigGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var appEnvConfigStore = Ext.create('AM.store.speedcloud.env.AppEnvConfigStore');
		appEnvConfigStore.proxy.extraParams={searchCondition:{}};
		me.appEnvConfigStore = appEnvConfigStore;
		appEnvConfigStore.load();
	}

});