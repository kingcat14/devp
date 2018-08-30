Ext.define('AM.view.icode4.microservice.MicroServiceItfcMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.MicroServiceItfcMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.MicroServiceItfcPanel'
		,'AM.view.icode4.microservice.MicroServiceItfcController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '微服务接口'
	,store:null
	,controller: 'microservice_MicroServiceItfcController'
    ,initComponent: function() {
        var me = this;

		var microServiceItfcStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcStore');
		microServiceItfcStore.proxy.extraParams={searchCondition:{}};
		microServiceItfcStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceMicroServiceItfcPanel'
				    ,region: 'center'
				    ,itemId: 'microServiceItfcGrid'
					,reference: 'microServiceItfcGrid'
				    ,store: microServiceItfcStore
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
		var panel = this.lookup('microServiceItfcGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('microServiceItfcGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var microServiceItfcStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcStore');
		microServiceItfcS.proxy.extraParams={searchCondition:{}};
		me.microServiceItfcStore = microServiceItfcStore;
		microServiceItfcStore.load();
	}

});