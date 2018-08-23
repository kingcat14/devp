Ext.define('AM.view.devp.publish.DevpSysOpsPipeCmpMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsPipeCmpMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsPipeCmpPanel'
		,'AM.view.devp.publish.DevpSysOpsPipeCmpController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品运维流水线对应的组件'
	,store:null
	,controller: 'publish_DevpSysOpsPipeCmpController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsPipeCmpStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipeCmpStore');
		devpSysOpsPipeCmpStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsPipeCmpStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsPipeCmpPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsPipeCmpGrid'
					,reference: 'devpSysOpsPipeCmpGrid'
				    ,store: devpSysOpsPipeCmpStore
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
		var panel = this.lookup('devpSysOpsPipeCmpGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsPipeCmpGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsPipeCmpStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipeCmpStore');
		devpSysOpsPipeCmpStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsPipeCmpStore = devpSysOpsPipeCmpStore;
		devpSysOpsPipeCmpStore.load();
	}

});