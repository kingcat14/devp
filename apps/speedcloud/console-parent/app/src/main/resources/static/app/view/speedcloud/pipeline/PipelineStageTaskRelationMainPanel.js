Ext.define('AM.view.speedcloud.pipeline.PipelineStageTaskRelationMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineStageTaskRelationMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineStageTaskRelationPanel'
		,'AM.view.speedcloud.pipeline.PipelineStageTaskRelationController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '阶段任务关联'
	,store:null
	,controller: 'pipeline_PipelineStageTaskRelationController'
    ,initComponent: function() {
        var me = this;

		var pipelineStageTaskRelationStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStageTaskRelationStore');
		pipelineStageTaskRelationStore.proxy.extraParams={searchCondition:{}};
		pipelineStageTaskRelationStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineStageTaskRelationPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineStageTaskRelationGrid'
					,reference: 'pipelineStageTaskRelationGrid'
				    ,store: pipelineStageTaskRelationStore
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
		var panel = this.lookup('pipelineStageTaskRelationGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineStageTaskRelationGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineStageTaskRelationStore = Ext.create('AM.store.speedcloud.pipeline.PipelineStageTaskRelationStore');
		pipelineStageTaskRelationStore.proxy.extraParams={searchCondition:{}};
		me.pipelineStageTaskRelationStore = pipelineStageTaskRelationStore;
		pipelineStageTaskRelationStore.load();
	}

});