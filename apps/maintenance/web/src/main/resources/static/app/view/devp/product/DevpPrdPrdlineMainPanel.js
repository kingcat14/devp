Ext.define('AM.view.devp.product.DevpPrdPrdlineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.product.DevpPrdPrdlineMainPanel'
    ,requires: [
        'AM.view.devp.product.DevpPrdPrdlinePanel'
		,'AM.view.devp.product.DevpPrdPrdlineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品线定义'
	,store:null
	,controller: 'product_DevpPrdPrdlineController'
    ,initComponent: function() {
        var me = this;

		var devpPrdPrdlineStore = Ext.create('AM.store.devp.product.DevpPrdPrdlineStore');
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
		var devpPrdPrdlineStore = Ext.create('AM.store.devp.product.DevpPrdPrdlineStore');
		devpPrdPrdlineS.proxy.extraParams={searchCondition:{}};
		me.devpPrdPrdlineStore = devpPrdPrdlineStore;
		devpPrdPrdlineStore.load();
	}

});