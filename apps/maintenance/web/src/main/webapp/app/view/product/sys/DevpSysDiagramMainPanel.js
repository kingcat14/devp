Ext.define('AM.view.product.sys.DevpSysDiagramMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysDiagramMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysDiagramPanel'
		,'AM.view.product.sys.DevpSysDiagramController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: 'UML图'
	,store:null
	,controller: 'sys_DevpSysDiagramController'
    ,initComponent: function() {
        var me = this;

		var devpSysDiagramStore = Ext.create('AM.store.product.sys.DevpSysDiagramStore');
		devpSysDiagramStore.proxy.extraParams={searchCondition:{}};
		devpSysDiagramStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysDiagramPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDiagramGrid'
					,reference: 'devpSysDiagramGrid'
				    ,store: devpSysDiagramStore
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
		var panel = this.lookup('devpSysDiagramGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDiagramGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDiagramStore = Ext.create('AM.store.product.sys.DevpSysDiagramStore');
		devpSysDiagramS.proxy.extraParams={searchCondition:{}};
		me.devpSysDiagramStore = devpSysDiagramStore;
		devpSysDiagramStore.load();
	}

});