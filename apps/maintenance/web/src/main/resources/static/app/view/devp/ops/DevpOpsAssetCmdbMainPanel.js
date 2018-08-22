Ext.define('AM.view.devp.ops.DevpOpsAssetCmdbMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.ops.DevpOpsAssetCmdbMainPanel'
    ,requires: [
        'AM.view.devp.ops.DevpOpsAssetCmdbPanel'
		,'AM.view.devp.ops.DevpOpsAssetCmdbController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产定义'
	,store:null
	,controller: 'ops_DevpOpsAssetCmdbController'
    ,initComponent: function() {
        var me = this;

		var devpOpsAssetCmdbStore = Ext.create('AM.store.devp.ops.DevpOpsAssetCmdbStore');
		devpOpsAssetCmdbStore.proxy.extraParams={searchCondition:{}};
		devpOpsAssetCmdbStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.ops.DevpOpsAssetCmdbPanel'
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
		var devpOpsAssetCmdbStore = Ext.create('AM.store.devp.ops.DevpOpsAssetCmdbStore');
		devpOpsAssetCmdbS.proxy.extraParams={searchCondition:{}};
		me.devpOpsAssetCmdbStore = devpOpsAssetCmdbStore;
		devpOpsAssetCmdbStore.load();
	}

});