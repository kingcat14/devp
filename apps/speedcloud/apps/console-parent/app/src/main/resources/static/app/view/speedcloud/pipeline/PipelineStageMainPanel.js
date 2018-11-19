Ext.define('AM.view.speedcloud.pipeline.PipelineStageMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineStageMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineStagePanel'
		,'AM.view.speedcloud.pipeline.PipelineStageController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '阶段'
	,store:null
	,controller: 'pipeline_PipelineStageController'
    ,initComponent: function() {
        var me = this;

		var pipelineStageStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStageStore');
		pipelineStageStore.proxy.extraParams={searchCondition:{}};
		pipelineStageStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineStagePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineStageGrid'
					,reference: 'pipelineStageGrid'
				    ,store: pipelineStageStore
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
		var panel = this.lookup('pipelineStageGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineStageGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineStageStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStageStore');
		pipelineStageStore.proxy.extraParams={searchCondition:{}};
		me.pipelineStageStore = pipelineStageStore;
		pipelineStageStore.load();
	}

});