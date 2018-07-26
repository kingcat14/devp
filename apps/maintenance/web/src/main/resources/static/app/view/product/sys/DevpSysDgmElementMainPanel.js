Ext.define('AM.view.product.sys.DevpSysDgmElementMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysDgmElementMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysDgmElementPanel'
		,'AM.view.product.sys.DevpSysDgmElementController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: 'UML图包含元素'
	,store:null
	,controller: 'sys_DevpSysDgmElementController'
    ,initComponent: function() {
        var me = this;

		var devpSysDgmElementStore = Ext.create('AM.store.product.sys.DevpSysDgmElementStore');
		devpSysDgmElementStore.proxy.extraParams={searchCondition:{}};
		devpSysDgmElementStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysDgmElementPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDgmElementGrid'
					,reference: 'devpSysDgmElementGrid'
				    ,store: devpSysDgmElementStore
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
		var panel = this.lookup('devpSysDgmElementGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDgmElementGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDgmElementStore = Ext.create('AM.store.product.sys.DevpSysDgmElementStore');
		devpSysDgmElementS.proxy.extraParams={searchCondition:{}};
		me.devpSysDgmElementStore = devpSysDgmElementStore;
		devpSysDgmElementStore.load();
	}

});