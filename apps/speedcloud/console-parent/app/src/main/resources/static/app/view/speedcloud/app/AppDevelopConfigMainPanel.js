Ext.define('AM.view.speedcloud.app.AppDevelopConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.AppDevelopConfigMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.AppDevelopConfigPanel'
		,'AM.view.speedcloud.app.AppDevelopConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用开发配置'
	,store:null
	,controller: 'app_AppDevelopConfigController'
    ,initComponent: function() {
        var me = this;

		var appDevelopConfigStore = Ext.create('AM.store.speedcloud.app.AppDevelopConfigStore');
		appDevelopConfigStore.proxy.extraParams={searchCondition:{}};
		appDevelopConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.AppDevelopConfigPanel'
				    ,region: 'center'
				    ,itemId: 'appDevelopConfigGrid'
					,reference: 'appDevelopConfigGrid'
				    ,store: appDevelopConfigStore
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
		var panel = this.lookup('appDevelopConfigGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('appDevelopConfigGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var appDevelopConfigStore = Ext.create('AM.store.speedcloud.app.AppDevelopConfigStore');
		appDevelopConfigStore.proxy.extraParams={searchCondition:{}};
		me.appDevelopConfigStore = appDevelopConfigStore;
		appDevelopConfigStore.load();
	}

});