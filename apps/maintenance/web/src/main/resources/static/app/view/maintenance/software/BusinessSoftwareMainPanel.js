Ext.define('AM.view.maintenance.software.BusinessSoftwareMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.software.BusinessSoftwareMainPanel'
    ,requires: [
        'AM.view.maintenance.software.BusinessSoftwarePanel'
		,'AM.view.maintenance.software.BusinessSoftwareController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '应用软件'
	,store:null
	,controller: 'software_BusinessSoftwareController'
    ,initComponent: function() {
        var me = this;

		var businessSoftwareStore = Ext.create('AM.store.maintenance.software.BusinessSoftwareStore');
		businessSoftwareStore.proxy.extraParams={searchCondition:{}};
		businessSoftwareStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.software.BusinessSoftwarePanel'
				    ,region: 'center'
				    ,itemId: 'businessSoftwareGrid'
					,reference: 'businessSoftwareGrid'
				    ,store: businessSoftwareStore
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
		var panel = this.lookup('businessSoftwareGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('businessSoftwareGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var businessSoftwareStore = Ext.create('AM.store.maintenance.software.BusinessSoftwareStore');
		businessSoftwareS.proxy.extraParams={searchCondition:{}};
		me.businessSoftwareStore = businessSoftwareStore;
		businessSoftwareStore.load();
	}

});