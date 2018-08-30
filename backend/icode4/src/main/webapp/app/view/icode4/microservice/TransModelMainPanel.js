Ext.define('AM.view.icode4.microservice.TransModelMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.microservice.TransModelMainPanel'
    ,requires: [
        'AM.view.icode4.microservice.TransModelPanel'
		,'AM.view.icode4.microservice.TransModelController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '传输对象'
	,store:null
	,controller: 'microservice_TransModelController'
    ,initComponent: function() {
        var me = this;

		var transModelStore = Ext.create('AM.store.icode4.microservice.TransModelStore');
		transModelStore.proxy.extraParams={searchCondition:{}};
		transModelStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'microserviceTransModelPanel'
				    ,region: 'center'
				    ,itemId: 'transModelGrid'
					,reference: 'transModelGrid'
				    ,store: transModelStore
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
		var panel = this.lookup('transModelGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('transModelGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var transModelStore = Ext.create('AM.store.icode4.microservice.TransModelStore');
		transModelS.proxy.extraParams={searchCondition:{}};
		me.transModelStore = transModelStore;
		transModelStore.load();
	}

});