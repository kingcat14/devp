Ext.define('AM.view.icode4.system.AmountRelationMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.system.AmountRelationMainPanel'
    ,requires: [
        'AM.view.icode4.system.AmountRelationPanel'
		,'AM.view.icode4.system.AmountRelationController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '数量关系'
	,store:null
	,controller: 'system_AmountRelationController'
    ,initComponent: function() {
        var me = this;

		var amountRelationStore = Ext.create('AM.store.icode4.system.AmountRelationStore');
		amountRelationStore.proxy.extraParams={searchCondition:{}};
		amountRelationStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'systemAmountRelationPanel'
				    ,region: 'center'
				    ,itemId: 'amountRelationGrid'
					,reference: 'amountRelationGrid'
				    ,store: amountRelationStore
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
		var panel = this.lookup('amountRelationGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('amountRelationGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var amountRelationStore = Ext.create('AM.store.icode4.system.AmountRelationStore');
		amountRelationS.proxy.extraParams={searchCondition:{}};
		me.amountRelationStore = amountRelationStore;
		amountRelationStore.load();
	}

});