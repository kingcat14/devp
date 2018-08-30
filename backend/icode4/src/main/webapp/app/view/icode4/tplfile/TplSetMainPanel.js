Ext.define('AM.view.icode4.tplfile.TplSetMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.tplfile.TplSetMainPanel'
    ,requires: [
        'AM.view.icode4.tplfile.TplSetPanel'
		,'AM.view.icode4.tplfile.TplSetController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '模板集合'
	,store:null
	,controller: 'tplfile_TplSetController'
    ,initComponent: function() {
        var me = this;

		var tplSetStore = Ext.create('AM.store.icode4.tplfile.TplSetStore');
		tplSetStore.proxy.extraParams={searchCondition:{}};
		tplSetStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'tplfileTplSetPanel'
				    ,region: 'center'
				    ,itemId: 'tplSetGrid'
					,reference: 'tplSetGrid'
				    ,store: tplSetStore
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
		var panel = this.lookup('tplSetGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('tplSetGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var tplSetStore = Ext.create('AM.store.icode4.tplfile.TplSetStore');
		tplSetS.proxy.extraParams={searchCondition:{}};
		me.tplSetStore = tplSetStore;
		tplSetStore.load();
	}

});