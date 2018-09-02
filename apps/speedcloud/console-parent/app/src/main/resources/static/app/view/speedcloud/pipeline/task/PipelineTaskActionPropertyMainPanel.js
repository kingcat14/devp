Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertyMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionPropertyMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertyPanel'
		,'AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertyController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '操作属性'
	,store:null
	,controller: 'pipeline.task_PipelineTaskActionPropertyController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskActionPropertyStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionPropertyStore');
		pipelineTaskActionPropertyStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskActionPropertyStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.task.PipelineTaskActionPropertyPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskActionPropertyGrid'
					,reference: 'pipelineTaskActionPropertyGrid'
				    ,store: pipelineTaskActionPropertyStore
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
		var panel = this.lookup('pipelineTaskActionPropertyGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskActionPropertyGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskActionPropertyStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionPropertyStore');
		pipelineTaskActionPropertyStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskActionPropertyStore = pipelineTaskActionPropertyStore;
		pipelineTaskActionPropertyStore.load();
	}

});