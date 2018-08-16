Ext.define('AM.view.maintenance.asset.info.AssetTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.asset.info.AssetTypeMainPanel'
    ,requires: [
        'AM.view.maintenance.asset.info.AssetTypePanel'
		,'AM.view.maintenance.asset.info.AssetTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产分类'
	,store:null
	,controller: 'asset.info_AssetTypeController'
    ,initComponent: function() {
        var me = this;

		var assetTypeStore = Ext.create('AM.store.maintenance.asset.info.AssetTypeStore');
		assetTypeStore.proxy.extraParams={searchCondition:{}};
		assetTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.asset.info.AssetTypePanel'
				    ,region: 'center'
				    ,itemId: 'assetTypeGrid'
					,reference: 'assetTypeGrid'
				    ,store: assetTypeStore
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
		var panel = this.lookup('assetTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('assetTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var assetTypeStore = Ext.create('AM.store.maintenance.asset.info.AssetTypeStore');
		assetTypeStore.proxy.extraParams={searchCondition:{}};
		me.assetTypeStore = assetTypeStore;
		assetTypeStore.load();
	}

});