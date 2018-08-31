Ext.define('AM.view.speedcloud.pipeline.PipelineTaskMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineTaskMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineTaskPanel'
		,'AM.view.speedcloud.pipeline.PipelineTaskController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '任务'
	,store:null
	,controller: 'pipeline_PipelineTaskController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskStore');
		pipelineTaskStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineTaskPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskGrid'
					,reference: 'pipelineTaskGrid'
				    ,store: pipelineTaskStore
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
		var panel = this.lookup('pipelineTaskGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskStore');
		pipelineTaskStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskStore = pipelineTaskStore;
		pipelineTaskStore.load();
	}

});