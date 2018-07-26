Ext.define('AM.view.deploy.ops.DevpOpsIssueMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.ops.DevpOpsIssueMainPanel'
    ,requires: [
        'AM.view.deploy.ops.DevpOpsIssuePanel'
		,'AM.view.deploy.ops.DevpOpsIssueController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '问题记录'
	,store:null
	,controller: 'ops_DevpOpsIssueController'
    ,initComponent: function() {
        var me = this;

		var devpOpsIssueStore = Ext.create('AM.store.deploy.ops.DevpOpsIssueStore');
		devpOpsIssueStore.proxy.extraParams={searchCondition:{}};
		devpOpsIssueStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.ops.DevpOpsIssuePanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsIssueGrid'
					,reference: 'devpOpsIssueGrid'
				    ,store: devpOpsIssueStore
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
		var panel = this.lookup('devpOpsIssueGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsIssueGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsIssueStore = Ext.create('AM.store.deploy.ops.DevpOpsIssueStore');
		devpOpsIssueS.proxy.extraParams={searchCondition:{}};
		me.devpOpsIssueStore = devpOpsIssueStore;
		devpOpsIssueStore.load();
	}

});