Ext.define('AM.view.devp.publish.DevpSysOpsPipeNodeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsPipeNodeMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsPipeNodePanel'
		,'AM.view.devp.publish.DevpSysOpsPipeNodeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '流水线执行节点'
	,store:null
	,controller: 'publish_DevpSysOpsPipeNodeController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsPipeNodeStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipeNodeStore');
		devpSysOpsPipeNodeStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsPipeNodeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsPipeNodePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsPipeNodeGrid'
					,reference: 'devpSysOpsPipeNodeGrid'
				    ,store: devpSysOpsPipeNodeStore
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
		var panel = this.lookup('devpSysOpsPipeNodeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsPipeNodeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsPipeNodeStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipeNodeStore');
		devpSysOpsPipeNodeStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsPipeNodeStore = devpSysOpsPipeNodeStore;
		devpSysOpsPipeNodeStore.load();
	}

});