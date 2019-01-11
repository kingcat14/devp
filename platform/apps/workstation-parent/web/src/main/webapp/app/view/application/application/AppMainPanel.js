Ext.define('AM.view.application.application.AppMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'application.application.AppMainPanel'
    ,requires: [
        'AM.view.application.application.AppPanel'
		,'AM.view.application.application.AppController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用'
	,store:null
	,controller: 'application_AppController'
    ,initComponent: function() {
        var me = this;

		var appStore = Ext.create('AM.store.application.application.AppStore');
		appStore.proxy.extraParams={searchCondition:{}};
		appStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'applicationAppPanel'
				    ,region: 'center'
				    ,itemId: 'appGrid'
					,reference: 'appGrid'
				    ,store: appStore
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
		var panel = this.lookup('appGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('appGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var appStore = Ext.create('AM.store.application.application.AppStore');
		appS.proxy.extraParams={searchCondition:{}};
		me.appStore = appStore;
		appStore.load();
	}

});