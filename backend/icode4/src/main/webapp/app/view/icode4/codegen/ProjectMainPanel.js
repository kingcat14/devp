Ext.define('AM.view.icode4.codegen.ProjectMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'icode4.codegen.ProjectMainPanel'
    ,requires: [
        'AM.view.icode4.codegen.ProjectPanel'
		,'AM.view.icode4.codegen.ProjectController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '工程'
	,store:null
	,controller: 'codegen_ProjectController'
    ,initComponent: function() {
        var me = this;

		var projectStore = Ext.create('AM.store.icode4.codegen.ProjectStore');
		projectStore.proxy.extraParams={searchCondition:{}};
		projectStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'codegenProjectPanel'
				    ,region: 'center'
				    ,itemId: 'projectGrid'
					,reference: 'projectGrid'
				    ,store: projectStore
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
		var panel = this.lookup('projectGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('projectGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var projectStore = Ext.create('AM.store.icode4.codegen.ProjectStore');
		projectS.proxy.extraParams={searchCondition:{}};
		me.projectStore = projectStore;
		projectStore.load();
	}

});