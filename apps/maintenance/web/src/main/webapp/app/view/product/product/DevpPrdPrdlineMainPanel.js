Ext.define('AM.view.product.product.DevpPrdPrdlineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.product.DevpPrdPrdlineMainPanel'
    ,requires: [
        'AM.view.product.product.DevpPrdPrdlinePanel'
		,'AM.view.product.product.DevpPrdPrdlineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品线定义'
	,store:null
	,controller: 'product_DevpPrdPrdlineController'
    ,initComponent: function() {
        var me = this;

		var devpPrdPrdlineStore = Ext.create('AM.store.product.product.DevpPrdPrdlineStore');
		devpPrdPrdlineStore.proxy.extraParams={searchCondition:{}};
		devpPrdPrdlineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'productDevpPrdPrdlinePanel'
				    ,region: 'center'
				    ,itemId: 'devpPrdPrdlineGrid'
					,reference: 'devpPrdPrdlineGrid'
				    ,store: devpPrdPrdlineStore
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
		var panel = this.lookup('devpPrdPrdlineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpPrdPrdlineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpPrdPrdlineStore = Ext.create('AM.store.product.product.DevpPrdPrdlineStore');
		devpPrdPrdlineS.proxy.extraParams={searchCondition:{}};
		me.devpPrdPrdlineStore = devpPrdPrdlineStore;
		devpPrdPrdlineStore.load();
	}

});