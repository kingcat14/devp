Ext.define('AM.view.speedcloud.pipeline.PipelineTaskTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineTaskTypeMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineTaskTypePanel'
		,'AM.view.speedcloud.pipeline.PipelineTaskTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '任务类型'
	,store:null
	,controller: 'pipeline_PipelineTaskTypeController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskTypeStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskTypeStore');
		pipelineTaskTypeStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineTaskTypePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskTypeGrid'
					,reference: 'pipelineTaskTypeGrid'
				    ,store: pipelineTaskTypeStore
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
		var panel = this.lookup('pipelineTaskTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskTypeStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskTypeStore');
		pipelineTaskTypeStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskTypeStore = pipelineTaskTypeStore;
		pipelineTaskTypeStore.load();
	}

});