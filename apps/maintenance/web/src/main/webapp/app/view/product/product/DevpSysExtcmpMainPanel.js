Ext.define('AM.view.product.product.DevpSysExtcmpMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.product.DevpSysExtcmpMainPanel'
    ,requires: [
        'AM.view.product.product.DevpSysExtcmpPanel'
		,'AM.view.product.product.DevpSysExtcmpController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品包含的外部组件'
	,store:null
	,controller: 'product_DevpSysExtcmpController'
    ,initComponent: function() {
        var me = this;

		var devpSysExtcmpStore = Ext.create('AM.store.product.product.DevpSysExtcmpStore');
		devpSysExtcmpStore.proxy.extraParams={searchCondition:{}};
		devpSysExtcmpStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'productDevpSysExtcmpPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysExtcmpGrid'
					,reference: 'devpSysExtcmpGrid'
				    ,store: devpSysExtcmpStore
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
		var panel = this.lookup('devpSysExtcmpGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysExtcmpGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysExtcmpStore = Ext.create('AM.store.product.product.DevpSysExtcmpStore');
		devpSysExtcmpS.proxy.extraParams={searchCondition:{}};
		me.devpSysExtcmpStore = devpSysExtcmpStore;
		devpSysExtcmpStore.load();
	}

});