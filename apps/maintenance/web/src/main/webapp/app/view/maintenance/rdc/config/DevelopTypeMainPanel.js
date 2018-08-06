Ext.define('AM.view.maintenance.rdc.config.DevelopTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.rdc.config.DevelopTypeMainPanel'
    ,requires: [
        'AM.view.maintenance.rdc.config.DevelopTypePanel'
		,'AM.view.maintenance.rdc.config.DevelopTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '开发模式'
	,store:null
	,controller: 'rdc.config_DevelopTypeController'
    ,initComponent: function() {
        var me = this;

		var developTypeStore = Ext.create('AM.store.maintenance.rdc.config.DevelopTypeStore');
		developTypeStore.proxy.extraParams={searchCondition:{}};
		developTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.rdc.config.DevelopTypePanel'
				    ,region: 'center'
				    ,itemId: 'developTypeGrid'
					,reference: 'developTypeGrid'
				    ,store: developTypeStore
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
		var panel = this.lookup('developTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('developTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var developTypeStore = Ext.create('AM.store.maintenance.rdc.config.DevelopTypeStore');
		developTypeS.proxy.extraParams={searchCondition:{}};
		me.developTypeStore = developTypeStore;
		developTypeStore.load();
	}

});