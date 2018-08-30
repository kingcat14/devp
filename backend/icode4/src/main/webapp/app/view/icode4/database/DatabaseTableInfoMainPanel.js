Ext.define('AM.view.icode4.database.DatabaseTableInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.database.DatabaseTableInfoMainPanel'
    ,requires: [
        'AM.view.icode4.database.DatabaseTableInfoPanel'
		,'AM.view.icode4.database.DatabaseTableInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '数据库表信息'
	,store:null
	,controller: 'database_DatabaseTableInfoController'
    ,initComponent: function() {
        var me = this;

		var databaseTableInfoStore = Ext.create('AM.store.icode4.database.DatabaseTableInfoStore');
		databaseTableInfoStore.proxy.extraParams={searchCondition:{}};
		databaseTableInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'databaseDatabaseTableInfoPanel'
				    ,region: 'center'
				    ,itemId: 'databaseTableInfoGrid'
					,reference: 'databaseTableInfoGrid'
				    ,store: databaseTableInfoStore
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
		var panel = this.lookup('databaseTableInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('databaseTableInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var databaseTableInfoStore = Ext.create('AM.store.icode4.database.DatabaseTableInfoStore');
		databaseTableInfoS.proxy.extraParams={searchCondition:{}};
		me.databaseTableInfoStore = databaseTableInfoStore;
		databaseTableInfoStore.load();
	}

});