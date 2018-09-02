Ext.define('AM.view.speedcloud.pipeline.exec.ExecMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.exec.ExecMainPanel'
    ,requires: [
        'AM.view.speedcloud.pipeline.exec.ExecPanel'
		,'AM.view.speedcloud.pipeline.exec.ExecController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '运行实例'
	,store:null
	,controller: 'pipeline.exec_ExecController'
    ,initComponent: function() {
        var me = this;

		var execStore = Ext.create('AM.store.speedcloud.pipeline.exec.ExecStore');
		execStore.proxy.extraParams={searchCondition:{}};
		execStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'speedcloud.pipeline.exec.ExecPanel'
				    ,region: 'center'
				    ,itemId: 'execGrid'
					,reference: 'execGrid'
				    ,store: execStore
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
		var panel = this.lookup('execGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('execGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var execStore = Ext.create('AM.store.speedcloud.pipeline.exec.ExecStore');
		execStore.proxy.extraParams={searchCondition:{}};
		me.execStore = execStore;
		execStore.load();
	}

});