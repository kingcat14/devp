Ext.define('AM.view.devp.deploy.DevpSysDpyInstSchemeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.deploy.DevpSysDpyInstSchemeMainPanel'
    ,requires: [
        'AM.view.devp.deploy.DevpSysDpyInstSchemePanel'
		,'AM.view.devp.deploy.DevpSysDpyInstSchemeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资源实例所属的部署方案'
	,store:null
	,controller: 'deploy_DevpSysDpyInstSchemeController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpyInstSchemeStore = Ext.create('AM.store.devp.deploy.DevpSysDpyInstSchemeStore');
		devpSysDpyInstSchemeStore.proxy.extraParams={searchCondition:{}};
		devpSysDpyInstSchemeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.deploy.DevpSysDpyInstSchemePanel'
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
		var devpSysDpyInstSchemeStore = Ext.create('AM.store.devp.deploy.DevpSysDpyInstSchemeStore');
		devpSysDpyInstSchemeS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpyInstSchemeStore = devpSysDpyInstSchemeStore;
		devpSysDpyInstSchemeStore.load();
	}

});