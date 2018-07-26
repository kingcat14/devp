Ext.define('AM.view.deploy.deploy.DevpSysDpyResourcesMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.deploy.DevpSysDpyResourcesMainPanel'
    ,requires: [
        'AM.view.deploy.deploy.DevpSysDpyResourcesPanel'
		,'AM.view.deploy.deploy.DevpSysDpyResourcesController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署关联资源定义'
	,store:null
	,controller: 'deploy_DevpSysDpyResourcesController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpyResourcesStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResourcesStore');
		devpSysDpyResourcesStore.proxy.extraParams={searchCondition:{}};
		devpSysDpyResourcesStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.deploy.DevpSysDpyResourcesPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDpyResourcesGrid'
					,reference: 'devpSysDpyResourcesGrid'
				    ,store: devpSysDpyResourcesStore
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
		var panel = this.lookup('devpSysDpyResourcesGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDpyResourcesGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDpyResourcesStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResourcesStore');
		devpSysDpyResourcesS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpyResourcesStore = devpSysDpyResourcesStore;
		devpSysDpyResourcesStore.load();
	}

});