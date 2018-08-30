Ext.define('AM.view.speedcloud.config.ConfigDevelopLanguageVersionMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.config.ConfigDevelopLanguageVersionMainPanel'
    ,requires: [
        'AM.view.speedcloud.config.ConfigDevelopLanguageVersionPanel'
		,'AM.view.speedcloud.config.ConfigDevelopLanguageVersionController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '开发语言版本'
	,store:null
	,controller: 'config_ConfigDevelopLanguageVersionController'
    ,initComponent: function() {
        var me = this;

		var configDevelopLanguageVersionStore = Ext.create('AM.store.speedcloud.config.ConfigDevelopLanguageVersionStore');
		configDevelopLanguageVersionStore.proxy.extraParams={searchCondition:{}};
		configDevelopLanguageVersionStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.config.ConfigDevelopLanguageVersionPanel'
				    ,region: 'center'
				    ,itemId: 'configDevelopLanguageVersionGrid'
					,reference: 'configDevelopLanguageVersionGrid'
				    ,store: configDevelopLanguageVersionStore
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
		var panel = this.lookup('configDevelopLanguageVersionGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('configDevelopLanguageVersionGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var configDevelopLanguageVersionStore = Ext.create('AM.store.speedcloud.config.ConfigDevelopLanguageVersionStore');
		configDevelopLanguageVersionStore.proxy.extraParams={searchCondition:{}};
		me.configDevelopLanguageVersionStore = configDevelopLanguageVersionStore;
		configDevelopLanguageVersionStore.load();
	}

});