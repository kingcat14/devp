Ext.define('AM.view.icode4.system.ModelTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.ModelTypeMainPanel'
    ,requires: [
        'AM.view.icode4.system.ModelTypePanel'
		,'AM.view.icode4.system.ModelTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '对象类型'
	,store:null
	,controller: 'system_ModelTypeController'
    ,initComponent: function() {
        var me = this;

		var modelTypeStore = Ext.create('AM.store.icode4.system.ModelTypeStore');
		modelTypeStore.proxy.extraParams={searchCondition:{}};
		modelTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemModelTypePanel'
				    ,region: 'center'
				    ,itemId: 'modelTypeGrid'
					,reference: 'modelTypeGrid'
				    ,store: modelTypeStore
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
		var panel = this.lookup('modelTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('modelTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var modelTypeStore = Ext.create('AM.store.icode4.system.ModelTypeStore');
		modelTypeS.proxy.extraParams={searchCondition:{}};
		me.modelTypeStore = modelTypeStore;
		modelTypeStore.load();
	}

});