Ext.define('AM.view.devp.publish.DevpSysOpsTaskConfigMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskConfigMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskConfigPanel'
		,'AM.view.devp.publish.DevpSysOpsTaskConfigController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '配置文件设置'
	,store:null
	,controller: 'publish_DevpSysOpsTaskConfigController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskConfigStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskConfigStore');
		devpSysOpsTaskConfigStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskConfigStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskConfigPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskConfigGrid'
					,reference: 'devpSysOpsTaskConfigGrid'
				    ,store: devpSysOpsTaskConfigStore
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
		var panel = this.lookup('devpSysOpsTaskConfigGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskConfigGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskConfigStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskConfigStore');
		devpSysOpsTaskConfigStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskConfigStore = devpSysOpsTaskConfigStore;
		devpSysOpsTaskConfigStore.load();
	}

});