Ext.define('AM.view.devp.sys.DevpSysCmpModuleMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.sys.DevpSysCmpModuleMainPanel'
    ,requires: [
        'AM.view.devp.sys.DevpSysCmpModulePanel'
		,'AM.view.devp.sys.DevpSysCmpModuleController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '组件对应模块'
	,store:null
	,controller: 'sys_DevpSysCmpModuleController'
    ,initComponent: function() {
        var me = this;

		var devpSysCmpModuleStore = Ext.create('AM.store.devp.sys.DevpSysCmpModuleStore');
		devpSysCmpModuleStore.proxy.extraParams={searchCondition:{}};
		devpSysCmpModuleStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.sys.DevpSysCmpModulePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysCmpModuleGrid'
					,reference: 'devpSysCmpModuleGrid'
				    ,store: devpSysCmpModuleStore
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
		var panel = this.lookup('devpSysCmpModuleGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysCmpModuleGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysCmpModuleStore = Ext.create('AM.store.devp.sys.DevpSysCmpModuleStore');
		devpSysCmpModuleS.proxy.extraParams={searchCondition:{}};
		me.devpSysCmpModuleStore = devpSysCmpModuleStore;
		devpSysCmpModuleStore.load();
	}

});