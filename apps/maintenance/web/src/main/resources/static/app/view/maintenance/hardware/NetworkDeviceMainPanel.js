Ext.define('AM.view.maintenance.hardware.NetworkDeviceMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.hardware.NetworkDeviceMainPanel'
    ,requires: [
        'AM.view.maintenance.hardware.NetworkDevicePanel'
		,'AM.view.maintenance.hardware.NetworkDeviceController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '网络设备'
	,store:null
	,controller: 'hardware_NetworkDeviceController'
    ,initComponent: function() {
        var me = this;

		var networkDeviceStore = Ext.create('AM.store.maintenance.hardware.NetworkDeviceStore');
		networkDeviceStore.proxy.extraParams={searchCondition:{}};
		networkDeviceStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.hardware.NetworkDevicePanel'
				    ,region: 'center'
				    ,itemId: 'networkDeviceGrid'
					,reference: 'networkDeviceGrid'
				    ,store: networkDeviceStore
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
		var panel = this.lookup('networkDeviceGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('networkDeviceGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var networkDeviceStore = Ext.create('AM.store.maintenance.hardware.NetworkDeviceStore');
		networkDeviceS.proxy.extraParams={searchCondition:{}};
		me.networkDeviceStore = networkDeviceStore;
		networkDeviceStore.load();
	}

});