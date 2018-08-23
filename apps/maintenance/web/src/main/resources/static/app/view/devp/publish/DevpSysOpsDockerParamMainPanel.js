Ext.define('AM.view.devp.publish.DevpSysOpsDockerParamMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsDockerParamMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsDockerParamPanel'
		,'AM.view.devp.publish.DevpSysOpsDockerParamController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署容器参数定义'
	,store:null
	,controller: 'publish_DevpSysOpsDockerParamController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsDockerParamStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerParamStore');
		devpSysOpsDockerParamStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsDockerParamStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsDockerParamPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsDockerParamGrid'
					,reference: 'devpSysOpsDockerParamGrid'
				    ,store: devpSysOpsDockerParamStore
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
		var panel = this.lookup('devpSysOpsDockerParamGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsDockerParamGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsDockerParamStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerParamStore');
		devpSysOpsDockerParamStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsDockerParamStore = devpSysOpsDockerParamStore;
		devpSysOpsDockerParamStore.load();
	}

});