Ext.define('AM.view.devp.ops.DevpOpsRequirementMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.ops.DevpOpsRequirementMainPanel'
    ,requires: [
        'AM.view.devp.ops.DevpOpsRequirementPanel'
		,'AM.view.devp.ops.DevpOpsRequirementController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '需求定义'
	,store:null
	,controller: 'ops_DevpOpsRequirementController'
    ,initComponent: function() {
        var me = this;

		var devpOpsRequirementStore = Ext.create('AM.store.devp.ops.DevpOpsRequirementStore');
		devpOpsRequirementStore.proxy.extraParams={searchCondition:{}};
		devpOpsRequirementStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.ops.DevpOpsRequirementPanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsRequirementGrid'
					,reference: 'devpOpsRequirementGrid'
				    ,store: devpOpsRequirementStore
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
		var panel = this.lookup('devpOpsRequirementGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsRequirementGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsRequirementStore = Ext.create('AM.store.devp.ops.DevpOpsRequirementStore');
		devpOpsRequirementS.proxy.extraParams={searchCondition:{}};
		me.devpOpsRequirementStore = devpOpsRequirementStore;
		devpOpsRequirementStore.load();
	}

});