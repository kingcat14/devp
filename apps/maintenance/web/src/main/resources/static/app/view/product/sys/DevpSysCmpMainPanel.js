Ext.define('AM.view.product.sys.DevpSysCmpMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysCmpMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysCmpPanel'
		,'AM.view.product.sys.DevpSysCmpController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统组件'
	,store:null
	,controller: 'sys_DevpSysCmpController'
    ,initComponent: function() {
        var me = this;

		var devpSysCmpStore = Ext.create('AM.store.product.sys.DevpSysCmpStore');
		devpSysCmpStore.proxy.extraParams={searchCondition:{}};
		devpSysCmpStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'product.sys.DevpSysCmpPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysCmpGrid'
					,reference: 'devpSysCmpGrid'
				    ,store: devpSysCmpStore
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
		var panel = this.lookup('devpSysCmpGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysCmpGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysCmpStore = Ext.create('AM.store.product.sys.DevpSysCmpStore');
		devpSysCmpS.proxy.extraParams={searchCondition:{}};
		me.devpSysCmpStore = devpSysCmpStore;
		devpSysCmpStore.load();
	}

});