Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsPanel'
		,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行实例节点参数'
	,store:null
	,controller: 'pipeline.exec_PipelineExecInstanceNodeParamsController'
    ,initComponent: function() {
        var me = this;

		var pipelineExecInstanceNodeParamsStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsStore');
		pipelineExecInstanceNodeParamsStore.proxy.extraParams={searchCondition:{}};
		pipelineExecInstanceNodeParamsStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineExecInstanceNodeParamsGrid'
					,reference: 'pipelineExecInstanceNodeParamsGrid'
				    ,store: pipelineExecInstanceNodeParamsStore
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
		var panel = this.lookup('pipelineExecInstanceNodeParamsGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineExecInstanceNodeParamsGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineExecInstanceNodeParamsStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamsStore');
		pipelineExecInstanceNodeParamsStore.proxy.extraParams={searchCondition:{}};
		me.pipelineExecInstanceNodeParamsStore = pipelineExecInstanceNodeParamsStore;
		pipelineExecInstanceNodeParamsStore.load();
	}

});