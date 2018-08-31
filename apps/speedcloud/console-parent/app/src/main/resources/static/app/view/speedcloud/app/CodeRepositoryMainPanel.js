Ext.define('AM.view.speedcloud.app.CodeRepositoryMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.CodeRepositoryMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.CodeRepositoryPanel'
		,'AM.view.speedcloud.app.CodeRepositoryController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '代码库'
	,store:null
	,controller: 'app_CodeRepositoryController'
    ,initComponent: function() {
        var me = this;

		var codeRepositoryStore = Ext.create('AM.store.speedcloud.app.CodeRepositoryStore');
		codeRepositoryStore.proxy.extraParams={searchCondition:{}};
		codeRepositoryStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.CodeRepositoryPanel'
				    ,region: 'center'
				    ,itemId: 'codeRepositoryGrid'
					,reference: 'codeRepositoryGrid'
				    ,store: codeRepositoryStore
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
		var panel = this.lookup('codeRepositoryGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('codeRepositoryGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var codeRepositoryStore = Ext.create('AM.store.speedcloud.app.CodeRepositoryStore');
		codeRepositoryStore.proxy.extraParams={searchCondition:{}};
		me.codeRepositoryStore = codeRepositoryStore;
		codeRepositoryStore.load();
	}

});