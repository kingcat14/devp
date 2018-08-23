Ext.define('AM.view.devp.publish.DevpSysOpsDockerAccessMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsDockerAccessMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsDockerAccessPanel'
		,'AM.view.devp.publish.DevpSysOpsDockerAccessController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署容器访问参数'
	,store:null
	,controller: 'publish_DevpSysOpsDockerAccessController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsDockerAccessStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerAccessStore');
		devpSysOpsDockerAccessStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsDockerAccessStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsDockerAccessPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsDockerAccessGrid'
					,reference: 'devpSysOpsDockerAccessGrid'
				    ,store: devpSysOpsDockerAccessStore
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
		var panel = this.lookup('devpSysOpsDockerAccessGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsDockerAccessGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsDockerAccessStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerAccessStore');
		devpSysOpsDockerAccessStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsDockerAccessStore = devpSysOpsDockerAccessStore;
		devpSysOpsDockerAccessStore.load();
	}

});