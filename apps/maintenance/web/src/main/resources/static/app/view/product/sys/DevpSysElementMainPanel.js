Ext.define('AM.view.product.sys.DevpSysElementMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysElementMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysElementPanel'
		,'AM.view.product.sys.DevpSysElementController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素'
	,store:null
	,controller: 'sys_DevpSysElementController'
    ,initComponent: function() {
        var me = this;

		var devpSysElementStore = Ext.create('AM.store.product.sys.DevpSysElementStore');
		devpSysElementStore.proxy.extraParams={searchCondition:{}};
		devpSysElementStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysElementPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysElementGrid'
					,reference: 'devpSysElementGrid'
				    ,store: devpSysElementStore
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
		var panel = this.lookup('devpSysElementGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysElementGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysElementStore = Ext.create('AM.store.product.sys.DevpSysElementStore');
		devpSysElementS.proxy.extraParams={searchCondition:{}};
		me.devpSysElementStore = devpSysElementStore;
		devpSysElementStore.load();
	}

});