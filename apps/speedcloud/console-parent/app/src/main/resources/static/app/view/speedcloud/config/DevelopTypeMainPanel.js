Ext.define('AM.view.speedcloud.config.DevelopTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.config.DevelopTypeMainPanel'
    ,requires: [
        'AM.view.speedcloud.config.DevelopTypePanel'
		,'AM.view.speedcloud.config.DevelopTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '开发模式'
	,store:null
	,controller: 'config_DevelopTypeController'
    ,initComponent: function() {
        var me = this;

		var developTypeStore = Ext.create('AM.store.speedcloud.config.DevelopTypeStore');
		developTypeStore.proxy.extraParams={searchCondition:{}};
		developTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.config.DevelopTypePanel'
				    ,region: 'center'
				    ,itemId: 'developTypeGrid'
					,reference: 'developTypeGrid'
				    ,store: developTypeStore
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
		var panel = this.lookup('developTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('developTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var developTypeStore = Ext.create('AM.store.speedcloud.config.DevelopTypeStore');
		developTypeStore.proxy.extraParams={searchCondition:{}};
		me.developTypeStore = developTypeStore;
		developTypeStore.load();
	}

});