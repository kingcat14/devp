Ext.define('AM.view.speedcloud.project.ProjectMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.project.ProjectMainPanel'
    ,requires: [
        'AM.view.speedcloud.project.ProjectPanel'
		,'AM.view.speedcloud.project.ProjectController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品（项目）'
	,store:null
	,controller: 'project_ProjectController'
    ,initComponent: function() {
        var me = this;

		var projectStore = Ext.create('AM.store.speedcloud.project.ProjectStore');
		projectStore.proxy.extraParams={searchCondition:{}};
		projectStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.project.ProjectPanel'
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
		var projectStore = Ext.create('AM.store.speedcloud.project.ProjectStore');
		projectStore.proxy.extraParams={searchCondition:{}};
		me.projectStore = projectStore;
		projectStore.load();
	}

});