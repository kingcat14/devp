Ext.define('AM.view.devp.deploy.DevpSysDpySchemeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.deploy.DevpSysDpySchemeMainPanel'
    ,requires: [
        'AM.view.devp.deploy.DevpSysDpySchemePanel'
		,'AM.view.devp.deploy.DevpSysDpySchemeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品部署方案'
	,store:null
	,controller: 'deploy_DevpSysDpySchemeController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpySchemeStore = Ext.create('AM.store.devp.deploy.DevpSysDpySchemeStore');
		devpSysDpySchemeStore.proxy.extraParams={searchCondition:{}};
		devpSysDpySchemeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.deploy.DevpSysDpySchemePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDpySchemeGrid'
					,reference: 'devpSysDpySchemeGrid'
				    ,store: devpSysDpySchemeStore
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
		var panel = this.lookup('devpSysDpySchemeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDpySchemeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDpySchemeStore = Ext.create('AM.store.devp.deploy.DevpSysDpySchemeStore');
		devpSysDpySchemeS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpySchemeStore = devpSysDpySchemeStore;
		devpSysDpySchemeStore.load();
	}

});