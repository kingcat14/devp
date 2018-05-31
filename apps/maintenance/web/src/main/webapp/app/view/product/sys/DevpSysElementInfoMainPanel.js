Ext.define('AM.view.product.sys.DevpSysElementInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysElementInfoMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysElementInfoPanel'
		,'AM.view.product.sys.DevpSysElementInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素扩充信息'
	,store:null
	,controller: 'sys_DevpSysElementInfoController'
    ,initComponent: function() {
        var me = this;

		var devpSysElementInfoStore = Ext.create('AM.store.product.sys.DevpSysElementInfoStore');
		devpSysElementInfoStore.proxy.extraParams={searchCondition:{}};
		devpSysElementInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysElementInfoPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysElementInfoGrid'
					,reference: 'devpSysElementInfoGrid'
				    ,store: devpSysElementInfoStore
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
		var panel = this.lookup('devpSysElementInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysElementInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysElementInfoStore = Ext.create('AM.store.product.sys.DevpSysElementInfoStore');
		devpSysElementInfoS.proxy.extraParams={searchCondition:{}};
		me.devpSysElementInfoStore = devpSysElementInfoStore;
		devpSysElementInfoStore.load();
	}

});