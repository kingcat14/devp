Ext.define('AM.view.devp.ops.DevpOpsCiGroupMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.ops.DevpOpsCiGroupMainPanel'
    ,requires: [
        'AM.view.devp.ops.DevpOpsCiGroupPanel'
		,'AM.view.devp.ops.DevpOpsCiGroupController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '资产项目分组映射'
	,store:null
	,controller: 'ops_DevpOpsCiGroupController'
    ,initComponent: function() {
        var me = this;

		var devpOpsCiGroupStore = Ext.create('AM.store.devp.ops.DevpOpsCiGroupStore');
		devpOpsCiGroupStore.proxy.extraParams={searchCondition:{}};
		devpOpsCiGroupStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.ops.DevpOpsCiGroupPanel'
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
		var devpOpsCiGroupStore = Ext.create('AM.store.devp.ops.DevpOpsCiGroupStore');
		devpOpsCiGroupS.proxy.extraParams={searchCondition:{}};
		me.devpOpsCiGroupStore = devpOpsCiGroupStore;
		devpOpsCiGroupStore.load();
	}

});