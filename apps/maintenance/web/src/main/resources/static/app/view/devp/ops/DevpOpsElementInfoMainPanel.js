Ext.define('AM.view.devp.ops.DevpOpsElementInfoMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.ops.DevpOpsElementInfoMainPanel'
    ,requires: [
        'AM.view.devp.ops.DevpOpsElementInfoPanel'
		,'AM.view.devp.ops.DevpOpsElementInfoController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素扩充信息'
	,store:null
	,controller: 'ops_DevpOpsElementInfoController'
    ,initComponent: function() {
        var me = this;

		var devpOpsElementInfoStore = Ext.create('AM.store.devp.ops.DevpOpsElementInfoStore');
		devpOpsElementInfoStore.proxy.extraParams={searchCondition:{}};
		devpOpsElementInfoStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.ops.DevpOpsElementInfoPanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsElementInfoGrid'
					,reference: 'devpOpsElementInfoGrid'
				    ,store: devpOpsElementInfoStore
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
		var panel = this.lookup('devpOpsElementInfoGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsElementInfoGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsElementInfoStore = Ext.create('AM.store.devp.ops.DevpOpsElementInfoStore');
		devpOpsElementInfoS.proxy.extraParams={searchCondition:{}};
		me.devpOpsElementInfoStore = devpOpsElementInfoStore;
		devpOpsElementInfoStore.load();
	}

});