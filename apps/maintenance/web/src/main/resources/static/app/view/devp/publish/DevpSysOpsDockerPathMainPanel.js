Ext.define('AM.view.devp.publish.DevpSysOpsDockerPathMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsDockerPathMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsDockerPathPanel'
		,'AM.view.devp.publish.DevpSysOpsDockerPathController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '存储路径定义'
	,store:null
	,controller: 'publish_DevpSysOpsDockerPathController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsDockerPathStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerPathStore');
		devpSysOpsDockerPathStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsDockerPathStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsDockerPathPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsDockerPathGrid'
					,reference: 'devpSysOpsDockerPathGrid'
				    ,store: devpSysOpsDockerPathStore
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
		var panel = this.lookup('devpSysOpsDockerPathGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsDockerPathGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsDockerPathStore = Ext.create('AM.store.devp.publish.DevpSysOpsDockerPathStore');
		devpSysOpsDockerPathStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsDockerPathStore = devpSysOpsDockerPathStore;
		devpSysOpsDockerPathStore.load();
	}

});