Ext.define('AM.view.product.sys.DevpSysElmInstInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysElmInstInfoMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysElmInstInfoPanel'
		,'AM.view.product.sys.DevpSysElmInstInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素实例'
	,store:null
	,controller: 'sys_DevpSysElmInstInfoController'
    ,initComponent: function() {
        var me = this;

		var devpSysElmInstInfoStore = Ext.create('AM.store.product.sys.DevpSysElmInstInfoStore');
		devpSysElmInstInfoStore.proxy.extraParams={searchCondition:{}};
		devpSysElmInstInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysElmInstInfoPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysElmInstInfoGrid'
					,reference: 'devpSysElmInstInfoGrid'
				    ,store: devpSysElmInstInfoStore
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
		var panel = this.lookup('devpSysElmInstInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysElmInstInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysElmInstInfoStore = Ext.create('AM.store.product.sys.DevpSysElmInstInfoStore');
		devpSysElmInstInfoS.proxy.extraParams={searchCondition:{}};
		me.devpSysElmInstInfoStore = devpSysElmInstInfoStore;
		devpSysElmInstInfoStore.load();
	}

});