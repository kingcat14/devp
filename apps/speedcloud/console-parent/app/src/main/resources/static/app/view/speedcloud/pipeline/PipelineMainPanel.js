Ext.define('AM.view.speedcloud.pipeline.PipelineMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelinePanel'
		,'AM.view.speedcloud.pipeline.PipelineController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '流水线'
	,store:null
	,controller: 'pipeline_PipelineController'
    ,initComponent: function() {
        var me = this;

		var pipelineStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStore');
		pipelineStore.proxy.extraParams={searchCondition:{}};
		pipelineStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelinePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineGrid'
					,reference: 'pipelineGrid'
				    ,store: pipelineStore
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
		var panel = this.lookup('pipelineGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStore');
		pipelineStore.proxy.extraParams={searchCondition:{}};
		me.pipelineStore = pipelineStore;
		pipelineStore.load();
	}

});