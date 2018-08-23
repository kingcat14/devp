Ext.define('AM.view.devp.publish.DevpSysOpsTaskDeployMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsTaskDeployMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsTaskDeployPanel'
		,'AM.view.devp.publish.DevpSysOpsTaskDeployController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '组件部署设置'
	,store:null
	,controller: 'publish_DevpSysOpsTaskDeployController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsTaskDeployStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskDeployStore');
		devpSysOpsTaskDeployStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsTaskDeployStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsTaskDeployPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsTaskDeployGrid'
					,reference: 'devpSysOpsTaskDeployGrid'
				    ,store: devpSysOpsTaskDeployStore
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
		var panel = this.lookup('devpSysOpsTaskDeployGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsTaskDeployGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsTaskDeployStore = Ext.create('AM.store.devp.publish.DevpSysOpsTaskDeployStore');
		devpSysOpsTaskDeployStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsTaskDeployStore = devpSysOpsTaskDeployStore;
		devpSysOpsTaskDeployStore.load();
	}

});