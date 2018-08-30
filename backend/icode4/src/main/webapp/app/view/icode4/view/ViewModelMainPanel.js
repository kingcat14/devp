Ext.define('AM.view.icode4.view.ViewModelMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.view.ViewModelMainPanel'
    ,requires: [
        'AM.view.icode4.view.ViewModelPanel'
		,'AM.view.icode4.view.ViewModelController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '展现对象'
	,store:null
	,controller: 'view_ViewModelController'
    ,initComponent: function() {
        var me = this;

		var viewModelStore = Ext.create('AM.store.icode4.view.ViewModelStore');
		viewModelStore.proxy.extraParams={searchCondition:{}};
		viewModelStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'viewViewModelPanel'
				    ,region: 'center'
				    ,itemId: 'viewModelGrid'
					,reference: 'viewModelGrid'
				    ,store: viewModelStore
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
		var panel = this.lookup('viewModelGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('viewModelGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var viewModelStore = Ext.create('AM.store.icode4.view.ViewModelStore');
		viewModelS.proxy.extraParams={searchCondition:{}};
		me.viewModelStore = viewModelStore;
		viewModelStore.load();
	}

});