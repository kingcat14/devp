Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePropertyMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionTypePropertyMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePropertyPanel'
		,'AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePropertyController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '操作类型属性定义'
	,store:null
	,controller: 'pipeline.task_PipelineTaskActionTypePropertyController'
    ,initComponent: function() {
        var me = this;

		var pipelineTaskActionTypePropertyStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionTypePropertyStore');
		pipelineTaskActionTypePropertyStore.proxy.extraParams={searchCondition:{}};
		pipelineTaskActionTypePropertyStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.task.PipelineTaskActionTypePropertyPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineTaskActionTypePropertyGrid'
					,reference: 'pipelineTaskActionTypePropertyGrid'
				    ,store: pipelineTaskActionTypePropertyStore
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
		var panel = this.lookup('pipelineTaskActionTypePropertyGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineTaskActionTypePropertyGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineTaskActionTypePropertyStore = Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionTypePropertyStore');
		pipelineTaskActionTypePropertyStore.proxy.extraParams={searchCondition:{}};
		me.pipelineTaskActionTypePropertyStore = pipelineTaskActionTypePropertyStore;
		pipelineTaskActionTypePropertyStore.load();
	}

});