Ext.define('AM.view.icode4.database.DatabaseProjectMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.database.DatabaseProjectMainPanel'
    ,requires: [
        'AM.view.icode4.database.DatabaseProjectPanel'
		,'AM.view.icode4.database.DatabaseProjectController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '数据库项目'
	,store:null
	,controller: 'database_DatabaseProjectController'
    ,initComponent: function() {
        var me = this;

		var databaseProjectStore = Ext.create('AM.store.icode4.database.DatabaseProjectStore');
		databaseProjectStore.proxy.extraParams={searchCondition:{}};
		databaseProjectStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'databaseDatabaseProjectPanel'
				    ,region: 'center'
				    ,itemId: 'databaseProjectGrid'
					,reference: 'databaseProjectGrid'
				    ,store: databaseProjectStore
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
		var panel = this.lookup('databaseProjectGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('databaseProjectGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var databaseProjectStore = Ext.create('AM.store.icode4.database.DatabaseProjectStore');
		databaseProjectS.proxy.extraParams={searchCondition:{}};
		me.databaseProjectStore = databaseProjectStore;
		databaseProjectStore.load();
	}

});