Ext.define('AM.view.deploy.ops.DevpOpsCiGroupMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.ops.DevpOpsCiGroupMainPanel'
    ,requires: [
        'AM.view.deploy.ops.DevpOpsCiGroupPanel'
		,'AM.view.deploy.ops.DevpOpsCiGroupController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产项目分组映射'
	,store:null
	,controller: 'ops_DevpOpsCiGroupController'
    ,initComponent: function() {
        var me = this;

		var devpOpsCiGroupStore = Ext.create('AM.store.deploy.ops.DevpOpsCiGroupStore');
		devpOpsCiGroupStore.proxy.extraParams={searchCondition:{}};
		devpOpsCiGroupStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.ops.DevpOpsCiGroupPanel'
				    ,region: 'center'
				    ,itemId: 'devpOpsCiGroupGrid'
					,reference: 'devpOpsCiGroupGrid'
				    ,store: devpOpsCiGroupStore
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
		var panel = this.lookup('devpOpsCiGroupGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpOpsCiGroupGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpOpsCiGroupStore = Ext.create('AM.store.deploy.ops.DevpOpsCiGroupStore');
		devpOpsCiGroupS.proxy.extraParams={searchCondition:{}};
		me.devpOpsCiGroupStore = devpOpsCiGroupStore;
		devpOpsCiGroupStore.load();
	}

});