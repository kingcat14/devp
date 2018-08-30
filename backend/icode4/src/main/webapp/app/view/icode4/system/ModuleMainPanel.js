Ext.define('AM.view.icode4.system.ModuleMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.ModuleMainPanel'
    ,requires: [
        'AM.view.icode4.system.ModulePanel'
		,'AM.view.icode4.system.ModuleController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '模块'
	,store:null
	,controller: 'system_ModuleController'
    ,initComponent: function() {
        var me = this;

		var moduleStore = Ext.create('AM.store.icode4.system.ModuleStore');
		moduleStore.proxy.extraParams={searchCondition:{}};
		moduleStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemModulePanel'
				    ,region: 'center'
				    ,itemId: 'moduleGrid'
					,reference: 'moduleGrid'
				    ,store: moduleStore
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
		var panel = this.lookup('moduleGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('moduleGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var moduleStore = Ext.create('AM.store.icode4.system.ModuleStore');
		moduleS.proxy.extraParams={searchCondition:{}};
		me.moduleStore = moduleStore;
		moduleStore.load();
	}

});