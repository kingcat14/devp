Ext.define('AM.view.icode4.system.ProductMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.ProductMainPanel'
    ,requires: [
        'AM.view.icode4.system.ProductPanel'
		,'AM.view.icode4.system.ProductController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: 'Product'
	,store:null
	,controller: 'system_ProductController'
    ,initComponent: function() {
        var me = this;

		var productStore = Ext.create('AM.store.icode4.system.ProductStore');
		productStore.proxy.extraParams={searchCondition:{}};
		productStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemProductPanel'
				    ,region: 'center'
				    ,itemId: 'productGrid'
					,reference: 'productGrid'
				    ,store: productStore
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
		var panel = this.lookup('productGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('productGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var productStore = Ext.create('AM.store.icode4.system.ProductStore');
		productS.proxy.extraParams={searchCondition:{}};
		me.productStore = productStore;
		productStore.load();
	}

});