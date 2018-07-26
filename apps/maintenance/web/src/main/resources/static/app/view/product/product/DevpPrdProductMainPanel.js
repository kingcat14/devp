Ext.define('AM.view.product.product.DevpPrdProductMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.product.DevpPrdProductMainPanel'
    ,requires: [
        'AM.view.product.product.DevpPrdProductPanel'
		,'AM.view.product.product.DevpPrdProductController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品定义'
	,store:null
	,controller: 'product_DevpPrdProductController'
    ,initComponent: function() {
        var me = this;

		var devpPrdProductStore = Ext.create('AM.store.product.product.DevpPrdProductStore');
		devpPrdProductStore.proxy.extraParams={searchCondition:{}};
		devpPrdProductStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'productDevpPrdProductPanel'
				    ,region: 'center'
				    ,itemId: 'devpPrdProductGrid'
					,reference: 'devpPrdProductGrid'
				    ,store: devpPrdProductStore
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
		var panel = this.lookup('devpPrdProductGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpPrdProductGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpPrdProductStore = Ext.create('AM.store.product.product.DevpPrdProductStore');
		devpPrdProductS.proxy.extraParams={searchCondition:{}};
		me.devpPrdProductStore = devpPrdProductStore;
		devpPrdProductStore.load();
	}

});