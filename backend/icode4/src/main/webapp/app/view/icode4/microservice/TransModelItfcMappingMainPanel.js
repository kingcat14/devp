Ext.define('AM.view.icode4.microservice.TransModelItfcMappingMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.TransModelItfcMappingMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.TransModelItfcMappingPanel'
		,'AM.view.icode4.microservice.TransModelItfcMappingController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '传输对象接口映射'
	,store:null
	,controller: 'microservice_TransModelItfcMappingController'
    ,initComponent: function() {
        var me = this;

		var transModelItfcMappingStore = Ext.create('AM.store.icode4.microservice.TransModelItfcMappingStore');
		transModelItfcMappingStore.proxy.extraParams={searchCondition:{}};
		transModelItfcMappingStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceTransModelItfcMappingPanel'
				    ,region: 'center'
				    ,itemId: 'transModelItfcMappingGrid'
					,reference: 'transModelItfcMappingGrid'
				    ,store: transModelItfcMappingStore
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
		var panel = this.lookup('transModelItfcMappingGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('transModelItfcMappingGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var transModelItfcMappingStore = Ext.create('AM.store.icode4.microservice.TransModelItfcMappingStore');
		transModelItfcMappingS.proxy.extraParams={searchCondition:{}};
		me.transModelItfcMappingStore = transModelItfcMappingStore;
		transModelItfcMappingStore.load();
	}

});