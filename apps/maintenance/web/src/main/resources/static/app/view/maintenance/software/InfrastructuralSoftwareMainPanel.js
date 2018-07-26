Ext.define('AM.view.maintenance.software.InfrastructuralSoftwareMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.software.InfrastructuralSoftwareMainPanel'
    ,requires: [
        'AM.view.maintenance.software.InfrastructuralSoftwarePanel'
		,'AM.view.maintenance.software.InfrastructuralSoftwareController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '基础软件'
	,store:null
	,controller: 'software_InfrastructuralSoftwareController'
    ,initComponent: function() {
        var me = this;

		var infrastructuralSoftwareStore = Ext.create('AM.store.maintenance.software.InfrastructuralSoftwareStore');
		infrastructuralSoftwareStore.proxy.extraParams={searchCondition:{}};
		infrastructuralSoftwareStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.software.InfrastructuralSoftwarePanel'
				    ,region: 'center'
				    ,itemId: 'infrastructuralSoftwareGrid'
					,reference: 'infrastructuralSoftwareGrid'
				    ,store: infrastructuralSoftwareStore
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
		var panel = this.lookup('infrastructuralSoftwareGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('infrastructuralSoftwareGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var infrastructuralSoftwareStore = Ext.create('AM.store.maintenance.software.InfrastructuralSoftwareStore');
		infrastructuralSoftwareS.proxy.extraParams={searchCondition:{}};
		me.infrastructuralSoftwareStore = infrastructuralSoftwareStore;
		infrastructuralSoftwareStore.load();
	}

});