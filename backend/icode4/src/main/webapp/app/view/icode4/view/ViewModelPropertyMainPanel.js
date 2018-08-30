Ext.define('AM.view.icode4.view.ViewModelPropertyMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.view.ViewModelPropertyMainPanel'
    ,requires: [
        'AM.view.icode4.view.ViewModelPropertyPanel'
		,'AM.view.icode4.view.ViewModelPropertyController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '展现对象属性'
	,store:null
	,controller: 'view_ViewModelPropertyController'
    ,initComponent: function() {
        var me = this;

		var viewModelPropertyStore = Ext.create('AM.store.icode4.view.ViewModelPropertyStore');
		viewModelPropertyStore.proxy.extraParams={searchCondition:{}};
		viewModelPropertyStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'viewViewModelPropertyPanel'
				    ,region: 'center'
				    ,itemId: 'viewModelPropertyGrid'
					,reference: 'viewModelPropertyGrid'
				    ,store: viewModelPropertyStore
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
		var panel = this.lookup('viewModelPropertyGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('viewModelPropertyGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var viewModelPropertyStore = Ext.create('AM.store.icode4.view.ViewModelPropertyStore');
		viewModelPropertyS.proxy.extraParams={searchCondition:{}};
		me.viewModelPropertyStore = viewModelPropertyStore;
		viewModelPropertyStore.load();
	}

});