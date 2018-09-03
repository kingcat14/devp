Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamPanel'
		,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行实例节点参数'
	,store:null
	,controller: 'pipeline.exec_PipelineExecInstanceNodeParamController'
    ,initComponent: function() {
        var me = this;

		var pipelineExecInstanceNodeParamStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamStore');
		pipelineExecInstanceNodeParamStore.proxy.extraParams={searchCondition:{}};
		pipelineExecInstanceNodeParamStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeParamPanel'
				    ,region: 'center'
				    ,itemId: 'pipelineExecInstanceNodeParamGrid'
					,reference: 'pipelineExecInstanceNodeParamGrid'
				    ,store: pipelineExecInstanceNodeParamStore
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
		var panel = this.lookup('pipelineExecInstanceNodeParamGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineExecInstanceNodeParamGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineExecInstanceNodeParamStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamStore');
		pipelineExecInstanceNodeParamStore.proxy.extraParams={searchCondition:{}};
		me.pipelineExecInstanceNodeParamStore = pipelineExecInstanceNodeParamStore;
		pipelineExecInstanceNodeParamStore.load();
	}

});