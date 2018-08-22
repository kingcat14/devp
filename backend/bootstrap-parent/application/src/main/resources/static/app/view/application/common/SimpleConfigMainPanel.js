Ext.define('AM.view.application.common.SimpleConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'application.common.SimpleConfigMainPanel'
    ,requires: [
        'AM.view.application.common.SimpleConfigPanel'
		,'AM.view.application.common.SimpleConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '通用配置'
	,store:null
	,controller: 'common_SimpleConfigController'
    ,initComponent: function() {
        var me = this;

		var simpleConfigStore = Ext.create('AM.store.application.common.SimpleConfigStore');
		simpleConfigStore.proxy.extraParams={searchCondition:{}};
		simpleConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'application.common.SimpleConfigPanel'
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
		var simpleConfigStore = Ext.create('AM.store.application.common.SimpleConfigStore');
		simpleConfigStore.proxy.extraParams={searchCondition:{}};
		me.simpleConfigStore = simpleConfigStore;
		simpleConfigStore.load();
	}

});