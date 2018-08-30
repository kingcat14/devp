Ext.define('AM.view.icode4.microservice.MicroServiceItfcParametersMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.MicroServiceItfcParametersMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.MicroServiceItfcParametersPanel'
		,'AM.view.icode4.microservice.MicroServiceItfcParametersController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '微服务接口参数'
	,store:null
	,controller: 'microservice_MicroServiceItfcParametersController'
    ,initComponent: function() {
        var me = this;

		var microServiceItfcParametersStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcParametersStore');
		microServiceItfcParametersStore.proxy.extraParams={searchCondition:{}};
		microServiceItfcParametersStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceMicroServiceItfcParametersPanel'
				    ,region: 'center'
				    ,itemId: 'microServiceItfcParametersGrid'
					,reference: 'microServiceItfcParametersGrid'
				    ,store: microServiceItfcParametersStore
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
		var panel = this.lookup('microServiceItfcParametersGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('microServiceItfcParametersGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var microServiceItfcParametersStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcParametersStore');
		microServiceItfcParametersS.proxy.extraParams={searchCondition:{}};
		me.microServiceItfcParametersStore = microServiceItfcParametersStore;
		microServiceItfcParametersStore.load();
	}

});