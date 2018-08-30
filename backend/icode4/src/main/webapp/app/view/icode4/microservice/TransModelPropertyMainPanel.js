Ext.define('AM.view.icode4.microservice.TransModelPropertyMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.TransModelPropertyMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.TransModelPropertyPanel'
		,'AM.view.icode4.microservice.TransModelPropertyController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '传输对象属性'
	,store:null
	,controller: 'microservice_TransModelPropertyController'
    ,initComponent: function() {
        var me = this;

		var transModelPropertyStore = Ext.create('AM.store.icode4.microservice.TransModelPropertyStore');
		transModelPropertyStore.proxy.extraParams={searchCondition:{}};
		transModelPropertyStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceTransModelPropertyPanel'
				    ,region: 'center'
				    ,itemId: 'transModelPropertyGrid'
					,reference: 'transModelPropertyGrid'
				    ,store: transModelPropertyStore
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
		var panel = this.lookup('transModelPropertyGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('transModelPropertyGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var transModelPropertyStore = Ext.create('AM.store.icode4.microservice.TransModelPropertyStore');
		transModelPropertyS.proxy.extraParams={searchCondition:{}};
		me.transModelPropertyStore = transModelPropertyStore;
		transModelPropertyStore.load();
	}

});