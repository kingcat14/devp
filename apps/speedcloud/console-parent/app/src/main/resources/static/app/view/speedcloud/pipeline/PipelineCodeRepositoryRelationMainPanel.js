Ext.define('AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineCodeRepositoryRelationMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationPanel'
		,'AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '流水线代码库关联'
	,store:null
	,controller: 'pipeline_PipelineCodeRepositoryRelationController'
    ,initComponent: function() {
        var me = this;

		var pipelineCodeRepositoryRelationStore = Ext.create('AM.store.speedcloud.pipeline.PipelineCodeRepositoryRelationStore');
		pipelineCodeRepositoryRelationStore.proxy.extraParams={searchCondition:{}};
		pipelineCodeRepositoryRelationStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineCodeRepositoryRelationPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineCodeRepositoryRelationGrid'
					,reference: 'pipelineCodeRepositoryRelationGrid'
				    ,store: pipelineCodeRepositoryRelationStore
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
		var panel = this.lookup('pipelineCodeRepositoryRelationGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineCodeRepositoryRelationGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineCodeRepositoryRelationStore = Ext.create('AM.store.speedcloud.pipeline.PipelineCodeRepositoryRelationStore');
		pipelineCodeRepositoryRelationStore.proxy.extraParams={searchCondition:{}};
		me.pipelineCodeRepositoryRelationStore = pipelineCodeRepositoryRelationStore;
		pipelineCodeRepositoryRelationStore.load();
	}

});