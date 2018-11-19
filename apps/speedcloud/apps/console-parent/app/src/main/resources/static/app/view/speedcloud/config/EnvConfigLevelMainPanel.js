Ext.define('AM.view.speedcloud.config.EnvConfigLevelMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.config.EnvConfigLevelMainPanel'
    ,requires: [
        'AM.view.speedcloud.config.EnvConfigLevelPanel'
		,'AM.view.speedcloud.config.EnvConfigLevelController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '环境级别'
	,store:null
	,controller: 'config_EnvConfigLevelController'
    ,initComponent: function() {
        var me = this;

		var envConfigLevelStore = Ext.create('AM.store.speedcloud.config.EnvConfigLevelStore');
		envConfigLevelStore.proxy.extraParams={searchCondition:{}};
		envConfigLevelStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.config.EnvConfigLevelPanel'
				    ,region: 'center'
				    ,itemId: 'envConfigLevelGrid'
					,reference: 'envConfigLevelGrid'
				    ,store: envConfigLevelStore
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
		var panel = this.lookup('envConfigLevelGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('envConfigLevelGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var envConfigLevelStore = Ext.create('AM.store.speedcloud.config.EnvConfigLevelStore');
		envConfigLevelStore.proxy.extraParams={searchCondition:{}};
		me.envConfigLevelStore = envConfigLevelStore;
		envConfigLevelStore.load();
	}

});