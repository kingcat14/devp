Ext.define('AM.view.speedcloud.app.CodeRepertoryMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.CodeRepertoryMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.CodeRepertoryPanel'
		,'AM.view.speedcloud.app.CodeRepertoryController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '代码库'
	,store:null
	,controller: 'app_CodeRepertoryController'
    ,initComponent: function() {
        var me = this;

		var codeRepertoryStore = Ext.create('AM.store.speedcloud.app.CodeRepertoryStore');
		codeRepertoryStore.proxy.extraParams={searchCondition:{}};
		codeRepertoryStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.CodeRepertoryPanel'
				    ,region: 'center'
				    ,itemId: 'codeRepertoryGrid'
					,reference: 'codeRepertoryGrid'
				    ,store: codeRepertoryStore
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
		var panel = this.lookup('codeRepertoryGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('codeRepertoryGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var codeRepertoryStore = Ext.create('AM.store.speedcloud.app.CodeRepertoryStore');
		codeRepertoryStore.proxy.extraParams={searchCondition:{}};
		me.codeRepertoryStore = codeRepertoryStore;
		codeRepertoryStore.load();
	}

});