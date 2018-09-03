Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodeMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodePanel'
		,'AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行实例节点'
	,store:null
	,controller: 'pipeline.exec_PipelineExecInstanceNodeController'
    ,initComponent: function() {
        var me = this;

		var pipelineExecInstanceNodeStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeStore');
		pipelineExecInstanceNodeStore.proxy.extraParams={searchCondition:{}};
		pipelineExecInstanceNodeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.PipelineExecInstanceNodePanel'
				    ,region: 'center'
				    ,itemId: 'pipelineExecInstanceNodeGrid'
					,reference: 'pipelineExecInstanceNodeGrid'
				    ,store: pipelineExecInstanceNodeStore
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
		var panel = this.lookup('pipelineExecInstanceNodeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('pipelineExecInstanceNodeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var pipelineExecInstanceNodeStore = Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceNodeStore');
		pipelineExecInstanceNodeStore.proxy.extraParams={searchCondition:{}};
		me.pipelineExecInstanceNodeStore = pipelineExecInstanceNodeStore;
		pipelineExecInstanceNodeStore.load();
	}

});