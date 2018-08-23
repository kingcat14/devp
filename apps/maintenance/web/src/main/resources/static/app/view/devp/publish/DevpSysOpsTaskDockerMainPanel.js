Ext.define('AM.view.devp.publish.DevpSysOpsTaskDockerMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskDockerMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskDockerPanel'
		,'AM.view.devp.publish.DevpSysOpsTaskDockerController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署容器'
	,store:null
	,controller: 'publish_DevpSysOpsTaskDockerController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskDockerStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskDockerStore');
		devpSysOpsTaskDockerStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskDockerStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskDockerPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskDockerGrid'
					,reference: 'devpSysOpsTaskDockerGrid'
				    ,store: devpSysOpsTaskDockerStore
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
		var panel = this.lookup('devpSysOpsTaskDockerGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskDockerGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskDockerStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskDockerStore');
		devpSysOpsTaskDockerStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskDockerStore = devpSysOpsTaskDockerStore;
		devpSysOpsTaskDockerStore.load();
	}

});