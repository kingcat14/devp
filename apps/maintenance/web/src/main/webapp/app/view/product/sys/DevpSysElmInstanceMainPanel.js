Ext.define('AM.view.product.sys.DevpSysElmInstanceMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysElmInstanceMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysElmInstancePanel'
		,'AM.view.product.sys.DevpSysElmInstanceController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素实例'
	,store:null
	,controller: 'sys_DevpSysElmInstanceController'
    ,initComponent: function() {
        var me = this;

		var devpSysElmInstanceStore = Ext.create('AM.store.product.sys.DevpSysElmInstanceStore');
		devpSysElmInstanceStore.proxy.extraParams={searchCondition:{}};
		devpSysElmInstanceStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysElmInstancePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysElmInstanceGrid'
					,reference: 'devpSysElmInstanceGrid'
				    ,store: devpSysElmInstanceStore
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
		var panel = this.lookup('devpSysElmInstanceGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysElmInstanceGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysElmInstanceStore = Ext.create('AM.store.product.sys.DevpSysElmInstanceStore');
		devpSysElmInstanceS.proxy.extraParams={searchCondition:{}};
		me.devpSysElmInstanceStore = devpSysElmInstanceStore;
		devpSysElmInstanceStore.load();
	}

});