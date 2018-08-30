Ext.define('AM.view.icode4.system.SystemMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.SystemMainPanel'
    ,requires: [
        'AM.view.icode4.system.SystemPanel'
		,'AM.view.icode4.system.SystemController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统'
	,store:null
	,controller: 'system_SystemController'
    ,initComponent: function() {
        var me = this;

		var systemStore = Ext.create('AM.store.icode4.system.SystemStore');
		systemStore.proxy.extraParams={searchCondition:{}};
		systemStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemSystemPanel'
				    ,region: 'center'
				    ,itemId: 'systemGrid'
					,reference: 'systemGrid'
				    ,store: systemStore
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
		var panel = this.lookup('systemGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('systemGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var systemStore = Ext.create('AM.store.icode4.system.SystemStore');
		systemS.proxy.extraParams={searchCondition:{}};
		me.systemStore = systemStore;
		systemStore.load();
	}

});