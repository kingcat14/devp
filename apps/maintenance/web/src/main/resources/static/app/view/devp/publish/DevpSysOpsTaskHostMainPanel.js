Ext.define('AM.view.devp.publish.DevpSysOpsTaskHostMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskHostMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskHostPanel'
		,'AM.view.devp.publish.DevpSysOpsTaskHostController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署主机节点'
	,store:null
	,controller: 'publish_DevpSysOpsTaskHostController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskHostStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskHostStore');
		devpSysOpsTaskHostStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskHostStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskHostPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskHostGrid'
					,reference: 'devpSysOpsTaskHostGrid'
				    ,store: devpSysOpsTaskHostStore
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
		var panel = this.lookup('devpSysOpsTaskHostGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskHostGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskHostStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskHostStore');
		devpSysOpsTaskHostStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskHostStore = devpSysOpsTaskHostStore;
		devpSysOpsTaskHostStore.load();
	}

});