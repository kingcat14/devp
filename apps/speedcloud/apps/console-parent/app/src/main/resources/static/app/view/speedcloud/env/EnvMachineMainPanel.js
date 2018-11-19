Ext.define('AM.view.speedcloud.env.EnvMachineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.env.EnvMachineMainPanel'
    ,requires: [
        'AM.view.speedcloud.env.EnvMachinePanel'
		,'AM.view.speedcloud.env.EnvMachineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '环境设备关联'
	,store:null
	,controller: 'env_EnvMachineController'
    ,initComponent: function() {
        var me = this;

		var envMachineStore = Ext.create('AM.store.speedcloud.env.EnvMachineStore');
		envMachineStore.proxy.extraParams={searchCondition:{}};
		envMachineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.env.EnvMachinePanel'
				    ,region: 'center'
				    ,itemId: 'envMachineGrid'
					,reference: 'envMachineGrid'
				    ,store: envMachineStore
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
		var panel = this.lookup('envMachineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('envMachineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var envMachineStore = Ext.create('AM.store.speedcloud.env.EnvMachineStore');
		envMachineStore.proxy.extraParams={searchCondition:{}};
		me.envMachineStore = envMachineStore;
		envMachineStore.load();
	}

});