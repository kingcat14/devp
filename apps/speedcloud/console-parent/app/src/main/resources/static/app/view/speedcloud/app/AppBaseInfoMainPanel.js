Ext.define('AM.view.speedcloud.app.AppBaseInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.AppBaseInfoMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.AppBaseInfoPanel'
		,'AM.view.speedcloud.app.AppBaseInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用'
	,store:null
	,controller: 'app_AppBaseInfoController'
    ,initComponent: function() {
        var me = this;

		var appBaseInfoStore = Ext.create('AM.store.speedcloud.app.AppBaseInfoStore');
		appBaseInfoStore.proxy.extraParams={searchCondition:{}};
		appBaseInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.AppBaseInfoPanel'
				    ,region: 'center'
				    ,itemId: 'appBaseInfoGrid'
					,reference: 'appBaseInfoGrid'
				    ,store: appBaseInfoStore
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
		var panel = this.lookup('appBaseInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('appBaseInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var appBaseInfoStore = Ext.create('AM.store.speedcloud.app.AppBaseInfoStore');
		appBaseInfoStore.proxy.extraParams={searchCondition:{}};
		me.appBaseInfoStore = appBaseInfoStore;
		appBaseInfoStore.load();
	}

});