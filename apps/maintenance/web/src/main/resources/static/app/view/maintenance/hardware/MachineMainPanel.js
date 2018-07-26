Ext.define('AM.view.maintenance.hardware.MachineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.hardware.MachineMainPanel'
    ,requires: [
        'AM.view.maintenance.hardware.MachinePanel'
		,'AM.view.maintenance.hardware.MachineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '服务器'
	,store:null
	,controller: 'hardware_MachineController'
    ,initComponent: function() {
        var me = this;

		var machineStore = Ext.create('AM.store.maintenance.hardware.MachineStore');
		machineStore.proxy.extraParams={searchCondition:{}};
		machineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.hardware.MachinePanel'
				    ,region: 'center'
				    ,itemId: 'machineGrid'
					,reference: 'machineGrid'
				    ,store: machineStore
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
		var panel = this.lookup('machineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('machineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var machineStore = Ext.create('AM.store.maintenance.hardware.MachineStore');
		machineS.proxy.extraParams={searchCondition:{}};
		me.machineStore = machineStore;
		machineStore.load();
	}

});