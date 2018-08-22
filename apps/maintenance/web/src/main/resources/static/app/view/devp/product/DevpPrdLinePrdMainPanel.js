Ext.define('AM.view.devp.product.DevpPrdLinePrdMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.product.DevpPrdLinePrdMainPanel'
    ,requires: [
        'AM.view.devp.product.DevpPrdLinePrdPanel'
		,'AM.view.devp.product.DevpPrdLinePrdController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品所属产品线定义'
	,store:null
	,controller: 'product_DevpPrdLinePrdController'
    ,initComponent: function() {
        var me = this;

		var devpPrdLinePrdStore = Ext.create('AM.store.devp.product.DevpPrdLinePrdStore');
		devpPrdLinePrdStore.proxy.extraParams={searchCondition:{}};
		devpPrdLinePrdStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'productDevpPrdLinePrdPanel'
				    ,region: 'center'
				    ,itemId: 'devpPrdLinePrdGrid'
					,reference: 'devpPrdLinePrdGrid'
				    ,store: devpPrdLinePrdStore
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
		var panel = this.lookup('devpPrdLinePrdGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpPrdLinePrdGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpPrdLinePrdStore = Ext.create('AM.store.devp.product.DevpPrdLinePrdStore');
		devpPrdLinePrdS.proxy.extraParams={searchCondition:{}};
		me.devpPrdLinePrdStore = devpPrdLinePrdStore;
		devpPrdLinePrdStore.load();
	}

});