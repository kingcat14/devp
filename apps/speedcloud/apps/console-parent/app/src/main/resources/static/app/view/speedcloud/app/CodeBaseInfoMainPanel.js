Ext.define('AM.view.speedcloud.app.CodeBaseInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.CodeBaseInfoMainPanel'
    ,requires: [
        'AM.view.speedcloud.app.CodeBaseInfoPanel'
		,'AM.view.speedcloud.app.CodeBaseInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '代码库详细信息'
	,store:null
	,controller: 'app_CodeBaseInfoController'
    ,initComponent: function() {
        var me = this;

		var codeBaseInfoStore = Ext.create('AM.store.speedcloud.app.CodeBaseInfoStore');
		codeBaseInfoStore.proxy.extraParams={searchCondition:{}};
		codeBaseInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.app.CodeBaseInfoPanel'
				    ,region: 'center'
				    ,itemId: 'codeBaseInfoGrid'
					,reference: 'codeBaseInfoGrid'
				    ,store: codeBaseInfoStore
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
		var panel = this.lookup('codeBaseInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('codeBaseInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var codeBaseInfoStore = Ext.create('AM.store.speedcloud.app.CodeBaseInfoStore');
		codeBaseInfoStore.proxy.extraParams={searchCondition:{}};
		me.codeBaseInfoStore = codeBaseInfoStore;
		codeBaseInfoStore.load();
	}

});