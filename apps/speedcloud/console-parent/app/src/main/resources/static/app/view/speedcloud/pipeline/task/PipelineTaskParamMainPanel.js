Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskParamMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskParamMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskParamPanel'
		,'AM.view.speedcloud.pipeline.task.PipelineTaskParamController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '任务参数'
	,store:null
	,controller: 'pipeline.task_PipelineTaskParamController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskParamStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskParamStore');
		pipelineTaskParamStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskParamStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.task.PipelineTaskParamPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskParamGrid'
					,reference: 'pipelineTaskParamGrid'
				    ,store: pipelineTaskParamStore
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
		var panel = this.lookup('pipelineTaskParamGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskParamGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskParamStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskParamStore');
		pipelineTaskParamStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskParamStore = pipelineTaskParamStore;
		pipelineTaskParamStore.load();
	}

});