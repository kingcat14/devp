Ext.define('AM.view.deploy.deploy.DevpSysDpyCmpRefMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.deploy.DevpSysDpyCmpRefMainPanel'
    ,requires: [
        'AM.view.deploy.deploy.DevpSysDpyCmpRefPanel'
		,'AM.view.deploy.deploy.DevpSysDpyCmpRefController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素间关系定义'
	,store:null
	,controller: 'deploy_DevpSysDpyCmpRefController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpyCmpRefStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyCmpRefStore');
		devpSysDpyCmpRefStore.proxy.extraParams={searchCondition:{}};
		devpSysDpyCmpRefStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.deploy.DevpSysDpyCmpRefPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDpyCmpRefGrid'
					,reference: 'devpSysDpyCmpRefGrid'
				    ,store: devpSysDpyCmpRefStore
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
		var panel = this.lookup('devpSysDpyCmpRefGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDpyCmpRefGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDpyCmpRefStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyCmpRefStore');
		devpSysDpyCmpRefS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpyCmpRefStore = devpSysDpyCmpRefStore;
		devpSysDpyCmpRefStore.load();
	}

});