Ext.define('AM.view.devp.publish.DevpSysOpsTaskCompileMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskCompileMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskCompilePanel'
		,'AM.view.devp.publish.DevpSysOpsTaskCompileController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '编译设置'
	,store:null
	,controller: 'publish_DevpSysOpsTaskCompileController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskCompileStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskCompileStore');
		devpSysOpsTaskCompileStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskCompileStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskCompilePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskCompileGrid'
					,reference: 'devpSysOpsTaskCompileGrid'
				    ,store: devpSysOpsTaskCompileStore
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
		var panel = this.lookup('devpSysOpsTaskCompileGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskCompileGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskCompileStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskCompileStore');
		devpSysOpsTaskCompileStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskCompileStore = devpSysOpsTaskCompileStore;
		devpSysOpsTaskCompileStore.load();
	}

});