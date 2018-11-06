Ext.define('AM.view.platform.platform.application.ConfigAppCategoryMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'platform.platform.application.ConfigAppCategoryMainPanel'
    ,requires: [
        'AM.view.platform.platform.application.ConfigAppCategoryPanel'
		,'AM.view.platform.platform.application.ConfigAppCategoryController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用类别'
	,store:null
	,controller: 'platform.application_ConfigAppCategoryController'
    ,initComponent: function() {
        var me = this;

		var configAppCategoryStore = Ext.create('AM.store.platform.platform.application.ConfigAppCategoryStore');
		configAppCategoryStore.proxy.extraParams={searchCondition:{}};
		configAppCategoryStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'platform.platform.application.ConfigAppCategoryPanel'
				    ,region: 'center'
				    ,itemId: 'configAppCategoryGrid'
					,reference: 'configAppCategoryGrid'
				    ,store: configAppCategoryStore
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
		var panel = this.lookup('configAppCategoryGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('configAppCategoryGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var configAppCategoryStore = Ext.create('AM.store.platform.platform.application.ConfigAppCategoryStore');
		configAppCategoryStore.proxy.extraParams={searchCondition:{}};
		me.configAppCategoryStore = configAppCategoryStore;
		configAppCategoryStore.load();
	}

});