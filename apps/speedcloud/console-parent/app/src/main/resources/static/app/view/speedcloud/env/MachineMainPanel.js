Ext.define('AM.view.speedcloud.env.MachineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.env.MachineMainPanel'
    ,requires: [
        'AM.view.speedcloud.env.MachinePanel'
		,'AM.view.speedcloud.env.MachineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '服务器'
	,store:null
	,controller: 'env_MachineController'
    ,initComponent: function() {
        var me = this;

		var machineStore = Ext.create('AM.store.speedcloud.env.MachineStore');
		machineStore.proxy.extraParams={searchCondition:{}};
		machineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.env.MachinePanel'
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
		var machineStore = Ext.create('AM.store.speedcloud.env.MachineStore');
		machineStore.proxy.extraParams={searchCondition:{}};
		me.machineStore = machineStore;
		machineStore.load();
	}

});