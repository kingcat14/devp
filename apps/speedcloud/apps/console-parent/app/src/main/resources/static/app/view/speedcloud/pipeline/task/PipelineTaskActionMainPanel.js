Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskActionPanel'
		,'AM.view.speedcloud.pipeline.task.PipelineTaskActionController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '操作'
	,store:null
	,controller: 'pipeline.task_PipelineTaskActionController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskActionStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionStore');
		pipelineTaskActionStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskActionStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.task.PipelineTaskActionPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskActionGrid'
					,reference: 'pipelineTaskActionGrid'
				    ,store: pipelineTaskActionStore
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
		var panel = this.lookup('pipelineTaskActionGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskActionGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskActionStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionStore');
		pipelineTaskActionStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskActionStore = pipelineTaskActionStore;
		pipelineTaskActionStore.load();
	}

});