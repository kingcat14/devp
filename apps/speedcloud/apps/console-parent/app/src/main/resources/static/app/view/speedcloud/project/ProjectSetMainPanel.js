Ext.define('AM.view.speedcloud.project.ProjectSetMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.project.ProjectSetMainPanel'
    ,requires: [
        'AM.view.speedcloud.project.ProjectSetPanel'
		,'AM.view.speedcloud.project.ProjectSetController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品线（项目集）'
	,store:null
	,controller: 'project_ProjectSetController'
    ,initComponent: function() {
        var me = this;

		var projectSetStore = Ext.create('AM.store.speedcloud.project.ProjectSetStore');
		projectSetStore.proxy.extraParams={searchCondition:{}};
		projectSetStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.project.ProjectSetPanel'
				    ,region: 'center'
				    ,itemId: 'projectSetGrid'
					,reference: 'projectSetGrid'
				    ,store: projectSetStore
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
		var panel = this.lookup('projectSetGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('projectSetGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var projectSetStore = Ext.create('AM.store.speedcloud.project.ProjectSetStore');
		projectSetStore.proxy.extraParams={searchCondition:{}};
		me.projectSetStore = projectSetStore;
		projectSetStore.load();
	}

});