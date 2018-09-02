Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionTypeMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePanel'
		,'AM.view.speedcloud.pipeline.task.PipelineTaskActionTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '操作类型'
	,store:null
	,controller: 'pipeline.task_PipelineTaskActionTypeController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskActionTypeStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionTypeStore');
		pipelineTaskActionTypeStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskActionTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.task.PipelineTaskActionTypePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskActionTypeGrid'
					,reference: 'pipelineTaskActionTypeGrid'
				    ,store: pipelineTaskActionTypeStore
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
		var panel = this.lookup('pipelineTaskActionTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskActionTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskActionTypeStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionTypeStore');
		pipelineTaskActionTypeStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskActionTypeStore = pipelineTaskActionTypeStore;
		pipelineTaskActionTypeStore.load();
	}

});