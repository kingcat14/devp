Ext.define('AM.view.devp.product.DevpPrdPersonMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.product.DevpPrdPersonMainPanel'
    ,requires: [
        'AM.view.devp.product.DevpPrdPersonPanel'
		,'AM.view.devp.product.DevpPrdPersonController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品干系人'
	,store:null
	,controller: 'product_DevpPrdPersonController'
    ,initComponent: function() {
        var me = this;

		var devpPrdPersonStore = Ext.create('AM.store.devp.product.DevpPrdPersonStore');
		devpPrdPersonStore.proxy.extraParams={searchCondition:{}};
		devpPrdPersonStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'productDevpPrdPersonPanel'
				    ,region: 'center'
				    ,itemId: 'devpPrdPersonGrid'
					,reference: 'devpPrdPersonGrid'
				    ,store: devpPrdPersonStore
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
		var panel = this.lookup('devpPrdPersonGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpPrdPersonGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpPrdPersonStore = Ext.create('AM.store.devp.product.DevpPrdPersonStore');
		devpPrdPersonS.proxy.extraParams={searchCondition:{}};
		me.devpPrdPersonStore = devpPrdPersonStore;
		devpPrdPersonStore.load();
	}

});