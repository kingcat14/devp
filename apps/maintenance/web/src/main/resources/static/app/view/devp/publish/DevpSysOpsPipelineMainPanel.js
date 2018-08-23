Ext.define('AM.view.devp.publish.DevpSysOpsPipelineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'devp.publish.DevpSysOpsPipelineMainPanel'
    ,requires: [
        'AM.view.devp.publish.DevpSysOpsPipelinePanel'
		,'AM.view.devp.publish.DevpSysOpsPipelineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '产品运维流水线'
	,store:null
	,controller: 'publish_DevpSysOpsPipelineController'
    ,initComponent: function() {
        var me = this;

		var devpSysOpsPipelineStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipelineStore');
		devpSysOpsPipelineStore.proxy.extraParams={searchCondition:{}};
		devpSysOpsPipelineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'devp.publish.DevpSysOpsPipelinePanel'
				    ,region: 'center'
				    ,itemId: 'devpSysOpsPipelineGrid'
					,reference: 'devpSysOpsPipelineGrid'
				    ,store: devpSysOpsPipelineStore
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
		var panel = this.lookup('devpSysOpsPipelineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysOpsPipelineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysOpsPipelineStore = Ext.create('AM.store.devp.publish.DevpSysOpsPipelineStore');
		devpSysOpsPipelineStore.proxy.extraParams={searchCondition:{}};
		me.devpSysOpsPipelineStore = devpSysOpsPipelineStore;
		devpSysOpsPipelineStore.load();
	}

});