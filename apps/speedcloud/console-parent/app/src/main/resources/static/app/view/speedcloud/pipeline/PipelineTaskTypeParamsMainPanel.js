Ext.define('AM.view.speedcloud.pipeline.PipelineTaskTypeParamsMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.PipelineTaskTypeParamsMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.PipelineTaskTypeParamsPanel'
		,'AM.view.speedcloud.pipeline.PipelineTaskTypeParamsController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '任务类型参数定义'
	,store:null
	,controller: 'pipeline_PipelineTaskTypeParamsController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskTypeParamsStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskTypeParamsStore');
		pipelineTaskTypeParamsStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskTypeParamsStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.PipelineTaskTypeParamsPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskTypeParamsGrid'
					,reference: 'pipelineTaskTypeParamsGrid'
				    ,store: pipelineTaskTypeParamsStore
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
		var panel = this.lookup('pipelineTaskTypeParamsGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskTypeParamsGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskTypeParamsStore = Ext.create('AM.store.speedcloud.pipeline.PipelineTaskTypeParamsStore');
		pipelineTaskTypeParamsStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskTypeParamsStore = pipelineTaskTypeParamsStore;
		pipelineTaskTypeParamsStore.load();
	}

});