Ext.define('AM.view.platform.platform.application.ApplicationPasswordMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'platform.platform.application.ApplicationPasswordMainPanel'
    ,requires: [
        'AM.view.platform.platform.application.ApplicationPasswordPanel'
		,'AM.view.platform.platform.application.ApplicationPasswordController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用密码'
	,store:null
	,controller: 'platform.application_ApplicationPasswordController'
    ,initComponent: function() {
        var me = this;

		var applicationPasswordStore = Ext.create('AM.store.platform.platform.application.ApplicationPasswordStore');
		applicationPasswordStore.proxy.extraParams={searchCondition:{}};
		applicationPasswordStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'platform.platform.application.ApplicationPasswordPanel'
				    ,region: 'center'
				    ,itemId: 'applicationPasswordGrid'
					,reference: 'applicationPasswordGrid'
				    ,store: applicationPasswordStore
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
		var panel = this.lookup('applicationPasswordGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('applicationPasswordGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var applicationPasswordStore = Ext.create('AM.store.platform.platform.application.ApplicationPasswordStore');
		applicationPasswordStore.proxy.extraParams={searchCondition:{}};
		me.applicationPasswordStore = applicationPasswordStore;
		applicationPasswordStore.load();
	}

});