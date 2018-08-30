Ext.define('AM.view.icode4.microservice.MicroServiceMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.MicroServiceMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.MicroServicePanel'
		,'AM.view.icode4.microservice.MicroServiceController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '微服务'
	,store:null
	,controller: 'microservice_MicroServiceController'
    ,initComponent: function() {
        var me = this;

		var microServiceStore = Ext.create('AM.store.icode4.microservice.MicroServiceStore');
		microServiceStore.proxy.extraParams={searchCondition:{}};
		microServiceStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceMicroServicePanel'
				    ,region: 'center'
				    ,itemId: 'microServiceGrid'
					,reference: 'microServiceGrid'
				    ,store: microServiceStore
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
		var panel = this.lookup('microServiceGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('microServiceGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var microServiceStore = Ext.create('AM.store.icode4.microservice.MicroServiceStore');
		microServiceS.proxy.extraParams={searchCondition:{}};
		me.microServiceStore = microServiceStore;
		microServiceStore.load();
	}

});