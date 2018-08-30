Ext.define('AM.view.icode4.system.PropertyTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.PropertyTypeMainPanel'
    ,requires: [
        'AM.view.icode4.system.PropertyTypePanel'
		,'AM.view.icode4.system.PropertyTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '属性类型'
	,store:null
	,controller: 'system_PropertyTypeController'
    ,initComponent: function() {
        var me = this;

		var propertyTypeStore = Ext.create('AM.store.icode4.system.PropertyTypeStore');
		propertyTypeStore.proxy.extraParams={searchCondition:{}};
		propertyTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemPropertyTypePanel'
				    ,region: 'center'
				    ,itemId: 'propertyTypeGrid'
					,reference: 'propertyTypeGrid'
				    ,store: propertyTypeStore
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
		var panel = this.lookup('propertyTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('propertyTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var propertyTypeStore = Ext.create('AM.store.icode4.system.PropertyTypeStore');
		propertyTypeS.proxy.extraParams={searchCondition:{}};
		me.propertyTypeStore = propertyTypeStore;
		propertyTypeStore.load();
	}

});