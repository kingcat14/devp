Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecInstancePanel'
		,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行计划'
	,store:null
	,controller: 'pipeline.exec_PipelineExecInstanceController'
    ,initComponent: function() {
        var me = this;

		var pipelineExecInstanceStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceStore');
		pipelineExecInstanceStore.proxy.extraParams={searchCondition:{}};
		pipelineExecInstanceStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.PipelineExecInstancePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineExecInstanceGrid'
					,reference: 'pipelineExecInstanceGrid'
				    ,store: pipelineExecInstanceStore
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
		var panel = this.lookup('pipelineExecInstanceGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineExecInstanceGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineExecInstanceStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceStore');
		pipelineExecInstanceStore.proxy.extraParams={searchCondition:{}};
		me.pipelineExecInstanceStore = pipelineExecInstanceStore;
		pipelineExecInstanceStore.load();
	}

});