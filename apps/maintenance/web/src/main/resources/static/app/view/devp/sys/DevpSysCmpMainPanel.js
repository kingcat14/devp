Ext.define('AM.view.devp.sys.DevpSysCmpMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.sys.DevpSysCmpMainPanel'
    ,requires: [
        'AM.view.devp.sys.DevpSysCmpPanel'
		,'AM.view.devp.sys.DevpSysCmpController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统组件'
	,store:null
	,controller: 'sys_DevpSysCmpController'
    ,initComponent: function() {
        var me = this;

		var devpSysCmpStore = Ext.create('AM.store.devp.sys.DevpSysCmpStore');
		devpSysCmpStore.proxy.extraParams={searchCondition:{}};
		devpSysCmpStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.sys.DevpSysCmpPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysCmpGrid'
					,reference: 'devpSysCmpGrid'
				    ,store: devpSysCmpStore
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
		var panel = this.lookup('devpSysCmpGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysCmpGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysCmpStore = Ext.create('AM.store.devp.sys.DevpSysCmpStore');
		devpSysCmpS.proxy.extraParams={searchCondition:{}};
		me.devpSysCmpStore = devpSysCmpStore;
		devpSysCmpStore.load();
	}

});