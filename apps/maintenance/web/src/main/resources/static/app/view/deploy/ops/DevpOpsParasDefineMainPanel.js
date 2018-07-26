Ext.define('AM.view.deploy.ops.DevpOpsParasDefineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.ops.DevpOpsParasDefineMainPanel'
    ,requires: [
        'AM.view.deploy.ops.DevpOpsParasDefinePanel'
		,'AM.view.deploy.ops.DevpOpsParasDefineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运维元素扩充信息'
	,store:null
	,controller: 'ops_DevpOpsParasDefineController'
    ,initComponent: function() {
        var me = this;

		var devpOpsParasDefineStore = Ext.create('AM.store.deploy.ops.DevpOpsParasDefineStore');
		devpOpsParasDefineStore.proxy.extraParams={searchCondition:{}};
		devpOpsParasDefineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.ops.DevpOpsParasDefinePanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsParasDefineGrid'
					,reference: 'devpOpsParasDefineGrid'
				    ,store: devpOpsParasDefineStore
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
		var panel = this.lookup('devpOpsParasDefineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsParasDefineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsParasDefineStore = Ext.create('AM.store.deploy.ops.DevpOpsParasDefineStore');
		devpOpsParasDefineS.proxy.extraParams={searchCondition:{}};
		me.devpOpsParasDefineStore = devpOpsParasDefineStore;
		devpOpsParasDefineStore.load();
	}

});