Ext.define('AM.view.devp.publish.DevpSysOpsPipePlanMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsPipePlanMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsPipePlanPanel'
		,'AM.view.devp.publish.DevpSysOpsPipePlanController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品运维流水线执行计划'
	,store:null
	,controller: 'publish_DevpSysOpsPipePlanController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsPipePlanStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipePlanStore');
		devpSysOpsPipePlanStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsPipePlanStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsPipePlanPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsPipePlanGrid'
					,reference: 'devpSysOpsPipePlanGrid'
				    ,store: devpSysOpsPipePlanStore
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
		var panel = this.lookup('devpSysOpsPipePlanGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsPipePlanGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsPipePlanStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipePlanStore');
		devpSysOpsPipePlanStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsPipePlanStore = devpSysOpsPipePlanStore;
		devpSysOpsPipePlanStore.load();
	}

});