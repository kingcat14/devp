Ext.define('AM.view.deploy.deploy.DevpSysDpyInstSchemeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.deploy.DevpSysDpyInstSchemeMainPanel'
    ,requires: [
        'AM.view.deploy.deploy.DevpSysDpyInstSchemePanel'
		,'AM.view.deploy.deploy.DevpSysDpyInstSchemeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资源实例所属的部署方案'
	,store:null
	,controller: 'deploy_DevpSysDpyInstSchemeController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpyInstSchemeStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyInstSchemeStore');
		devpSysDpyInstSchemeStore.proxy.extraParams={searchCondition:{}};
		devpSysDpyInstSchemeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.deploy.DevpSysDpyInstSchemePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDpyInstSchemeGrid'
					,reference: 'devpSysDpyInstSchemeGrid'
				    ,store: devpSysDpyInstSchemeStore
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
		var panel = this.lookup('devpSysDpyInstSchemeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDpyInstSchemeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDpyInstSchemeStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyInstSchemeStore');
		devpSysDpyInstSchemeS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpyInstSchemeStore = devpSysDpyInstSchemeStore;
		devpSysDpyInstSchemeStore.load();
	}

});