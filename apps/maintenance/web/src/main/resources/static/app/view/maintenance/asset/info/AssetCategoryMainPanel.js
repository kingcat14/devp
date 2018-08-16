Ext.define('AM.view.maintenance.asset.info.AssetCategoryMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.asset.info.AssetCategoryMainPanel'
    ,requires: [
        'AM.view.maintenance.asset.info.AssetCategoryPanel'
		,'AM.view.maintenance.asset.info.AssetCategoryController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产大类'
	,store:null
	,controller: 'asset.info_AssetCategoryController'
    ,initComponent: function() {
        var me = this;

		var assetCategoryStore = Ext.create('AM.store.maintenance.asset.info.AssetCategoryStore');
		assetCategoryStore.proxy.extraParams={searchCondition:{}};
		assetCategoryStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'maintenance.asset.info.AssetCategoryPanel'
				    ,region: 'center'
				    ,itemId: 'assetCategoryGrid'
					,reference: 'assetCategoryGrid'
				    ,store: assetCategoryStore
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
		var panel = this.lookup('assetCategoryGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('assetCategoryGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var assetCategoryStore = Ext.create('AM.store.maintenance.asset.info.AssetCategoryStore');
		assetCategoryStore.proxy.extraParams={searchCondition:{}};
		me.assetCategoryStore = assetCategoryStore;
		assetCategoryStore.load();
	}

});