Ext.define('AM.view.icode4.tplfile.TplCodeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.tplfile.TplCodeMainPanel'
    ,requires: [
        'AM.view.icode4.tplfile.TplCodePanel'
		,'AM.view.icode4.tplfile.TplCodeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '文件模板'
	,store:null
	,controller: 'tplfile_TplCodeController'
    ,initComponent: function() {
        var me = this;

		var tplCodeStore = Ext.create('AM.store.icode4.tplfile.TplCodeStore');
		tplCodeStore.proxy.extraParams={searchCondition:{}};
		tplCodeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'tplfileTplCodePanel'
				    ,region: 'center'
				    ,itemId: 'tplCodeGrid'
					,reference: 'tplCodeGrid'
				    ,store: tplCodeStore
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
		var panel = this.lookup('tplCodeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('tplCodeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var tplCodeStore = Ext.create('AM.store.icode4.tplfile.TplCodeStore');
		tplCodeS.proxy.extraParams={searchCondition:{}};
		me.tplCodeStore = tplCodeStore;
		tplCodeStore.load();
	}

});