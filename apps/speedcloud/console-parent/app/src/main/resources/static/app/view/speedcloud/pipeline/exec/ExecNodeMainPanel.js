Ext.define('AM.view.speedcloud.pipeline.exec.ExecNodeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.ExecNodeMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.ExecNodePanel'
		,'AM.view.speedcloud.pipeline.exec.ExecNodeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行实例节点'
	,store:null
	,controller: 'pipeline.exec_ExecNodeController'
    ,initComponent: function() {
        var me = this;

		var execNodeStore = Ext.create('AM.store.speedcloud.pipeline.exec.ExecNodeStore');
		execNodeStore.proxy.extraParams={searchCondition:{}};
		execNodeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.ExecNodePanel'
				    ,region: 'center'
				    ,itemId: 'execNodeGrid'
					,reference: 'execNodeGrid'
				    ,store: execNodeStore
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
		var panel = this.lookup('execNodeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('execNodeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var execNodeStore = Ext.create('AM.store.speedcloud.pipeline.exec.ExecNodeStore');
		execNodeStore.proxy.extraParams={searchCondition:{}};
		me.execNodeStore = execNodeStore;
		execNodeStore.load();
	}

});