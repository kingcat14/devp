Ext.define('AM.view.maintenance.config.SimpleConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.config.SimpleConfigMainPanel'
    ,requires: [
        'AM.view.maintenance.config.SimpleConfigPanel'
		,'AM.view.maintenance.config.SimpleConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '通用配置'
	,store:null
	,controller: 'config_SimpleConfigController'
    ,initComponent: function() {
        var me = this;

		var simpleConfigStore = Ext.create('AM.store.maintenance.config.SimpleConfigStore');
		simpleConfigStore.proxy.extraParams={searchCondition:{}};
		simpleConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.config.SimpleConfigPanel'
				    ,region: 'center'
				    ,itemId: 'simpleConfigGrid'
					,reference: 'simpleConfigGrid'
				    ,store: simpleConfigStore
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
		var panel = this.lookup('simpleConfigGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('simpleConfigGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var simpleConfigStore = Ext.create('AM.store.maintenance.config.SimpleConfigStore');
		simpleConfigS.proxy.extraParams={searchCondition:{}};
		me.simpleConfigStore = simpleConfigStore;
		simpleConfigStore.load();
	}

});