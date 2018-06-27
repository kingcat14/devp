Ext.define('AM.view.maintenance.software.SoftwareLicenseMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.software.SoftwareLicenseMainPanel'
    ,requires: [
        'AM.view.maintenance.software.SoftwareLicensePanel'
		,'AM.view.maintenance.software.SoftwareLicenseController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '服务许可'
	,store:null
	,controller: 'software_SoftwareLicenseController'
    ,initComponent: function() {
        var me = this;

		var softwareLicenseStore = Ext.create('AM.store.maintenance.software.SoftwareLicenseStore');
		softwareLicenseStore.proxy.extraParams={searchCondition:{}};
		softwareLicenseStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.software.SoftwareLicensePanel'
				    ,region: 'center'
				    ,itemId: 'softwareLicenseGrid'
					,reference: 'softwareLicenseGrid'
				    ,store: softwareLicenseStore
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
		var panel = this.lookup('softwareLicenseGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('softwareLicenseGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var softwareLicenseStore = Ext.create('AM.store.maintenance.software.SoftwareLicenseStore');
		softwareLicenseS.proxy.extraParams={searchCondition:{}};
		me.softwareLicenseStore = softwareLicenseStore;
		softwareLicenseStore.load();
	}

});