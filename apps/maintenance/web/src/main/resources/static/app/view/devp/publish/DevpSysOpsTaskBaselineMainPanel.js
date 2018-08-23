Ext.define('AM.view.devp.publish.DevpSysOpsTaskBaselineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskBaselineMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskBaselinePanel'
		,'AM.view.devp.publish.DevpSysOpsTaskBaselineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '基线设置'
	,store:null
	,controller: 'publish_DevpSysOpsTaskBaselineController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskBaselineStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskBaselineStore');
		devpSysOpsTaskBaselineStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskBaselineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskBaselinePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskBaselineGrid'
					,reference: 'devpSysOpsTaskBaselineGrid'
				    ,store: devpSysOpsTaskBaselineStore
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
		var panel = this.lookup('devpSysOpsTaskBaselineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskBaselineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskBaselineStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskBaselineStore');
		devpSysOpsTaskBaselineStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskBaselineStore = devpSysOpsTaskBaselineStore;
		devpSysOpsTaskBaselineStore.load();
	}

});