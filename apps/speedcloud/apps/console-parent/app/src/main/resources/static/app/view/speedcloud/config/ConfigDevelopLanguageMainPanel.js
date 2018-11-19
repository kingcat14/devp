Ext.define('AM.view.speedcloud.config.ConfigDevelopLanguageMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.config.ConfigDevelopLanguageMainPanel'
    ,requires: [
        'AM.view.speedcloud.config.ConfigDevelopLanguagePanel'
		,'AM.view.speedcloud.config.ConfigDevelopLanguageController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '开发语言'
	,store:null
	,controller: 'config_ConfigDevelopLanguageController'
    ,initComponent: function() {
        var me = this;

		var configDevelopLanguageStore = Ext.create('AM.store.speedcloud.config.ConfigDevelopLanguageStore');
		configDevelopLanguageStore.proxy.extraParams={searchCondition:{}};
		configDevelopLanguageStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.config.ConfigDevelopLanguagePanel'
				    ,region: 'center'
				    ,itemId: 'configDevelopLanguageGrid'
					,reference: 'configDevelopLanguageGrid'
				    ,store: configDevelopLanguageStore
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
		var panel = this.lookup('configDevelopLanguageGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('configDevelopLanguageGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var configDevelopLanguageStore = Ext.create('AM.store.speedcloud.config.ConfigDevelopLanguageStore');
		configDevelopLanguageStore.proxy.extraParams={searchCondition:{}};
		me.configDevelopLanguageStore = configDevelopLanguageStore;
		configDevelopLanguageStore.load();
	}

});