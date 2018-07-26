Ext.define('AM.view.deploy.ops.DevpOpsAssetCmdbMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.ops.DevpOpsAssetCmdbMainPanel'
    ,requires: [
        'AM.view.deploy.ops.DevpOpsAssetCmdbPanel'
		,'AM.view.deploy.ops.DevpOpsAssetCmdbController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产定义'
	,store:null
	,controller: 'ops_DevpOpsAssetCmdbController'
    ,initComponent: function() {
        var me = this;

		var devpOpsAssetCmdbStore = Ext.create('AM.store.deploy.ops.DevpOpsAssetCmdbStore');
		devpOpsAssetCmdbStore.proxy.extraParams={searchCondition:{}};
		devpOpsAssetCmdbStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.ops.DevpOpsAssetCmdbPanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsAssetCmdbGrid'
					,reference: 'devpOpsAssetCmdbGrid'
				    ,store: devpOpsAssetCmdbStore
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
		var panel = this.lookup('devpOpsAssetCmdbGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsAssetCmdbGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsAssetCmdbStore = Ext.create('AM.store.deploy.ops.DevpOpsAssetCmdbStore');
		devpOpsAssetCmdbS.proxy.extraParams={searchCondition:{}};
		me.devpOpsAssetCmdbStore = devpOpsAssetCmdbStore;
		devpOpsAssetCmdbStore.load();
	}

});