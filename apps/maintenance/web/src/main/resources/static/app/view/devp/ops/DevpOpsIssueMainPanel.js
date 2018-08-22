Ext.define('AM.view.devp.ops.DevpOpsIssueMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.ops.DevpOpsIssueMainPanel'
    ,requires: [
        'AM.view.devp.ops.DevpOpsIssuePanel'
		,'AM.view.devp.ops.DevpOpsIssueController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '问题记录'
	,store:null
	,controller: 'ops_DevpOpsIssueController'
    ,initComponent: function() {
        var me = this;

		var devpOpsIssueStore = Ext.create('AM.store.devp.ops.DevpOpsIssueStore');
		devpOpsIssueStore.proxy.extraParams={searchCondition:{}};
		devpOpsIssueStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.ops.DevpOpsIssuePanel'
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
		var devpOpsIssueStore = Ext.create('AM.store.devp.ops.DevpOpsIssueStore');
		devpOpsIssueS.proxy.extraParams={searchCondition:{}};
		me.devpOpsIssueStore = devpOpsIssueStore;
		devpOpsIssueStore.load();
	}

});