Ext.define('AM.view.deploy.ops.DevpOpsAttachmentMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.ops.DevpOpsAttachmentMainPanel'
    ,requires: [
        'AM.view.deploy.ops.DevpOpsAttachmentPanel'
		,'AM.view.deploy.ops.DevpOpsAttachmentController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '附件定义'
	,store:null
	,controller: 'ops_DevpOpsAttachmentController'
    ,initComponent: function() {
        var me = this;

		var devpOpsAttachmentStore = Ext.create('AM.store.deploy.ops.DevpOpsAttachmentStore');
		devpOpsAttachmentStore.proxy.extraParams={searchCondition:{}};
		devpOpsAttachmentStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.ops.DevpOpsAttachmentPanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsAttachmentGrid'
					,reference: 'devpOpsAttachmentGrid'
				    ,store: devpOpsAttachmentStore
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
		var panel = this.lookup('devpOpsAttachmentGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsAttachmentGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsAttachmentStore = Ext.create('AM.store.deploy.ops.DevpOpsAttachmentStore');
		devpOpsAttachmentS.proxy.extraParams={searchCondition:{}};
		me.devpOpsAttachmentStore = devpOpsAttachmentStore;
		devpOpsAttachmentStore.load();
	}

});