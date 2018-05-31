Ext.define('AM.view.product.sys.DevpSysElmConnectorMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'product.sys.DevpSysElmConnectorMainPanel'
    ,requires: [
        'AM.view.product.sys.DevpSysElmConnectorPanel'
		,'AM.view.product.sys.DevpSysElmConnectorController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '系统元素间关系'
	,store:null
	,controller: 'sys_DevpSysElmConnectorController'
    ,initComponent: function() {
        var me = this;

		var devpSysElmConnectorStore = Ext.create('AM.store.product.sys.DevpSysElmConnectorStore');
		devpSysElmConnectorStore.proxy.extraParams={searchCondition:{}};
		devpSysElmConnectorStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'sysDevpSysElmConnectorPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysElmConnectorGrid'
					,reference: 'devpSysElmConnectorGrid'
				    ,store: devpSysElmConnectorStore
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
		var panel = this.lookup('devpSysElmConnectorGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysElmConnectorGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysElmConnectorStore = Ext.create('AM.store.product.sys.DevpSysElmConnectorStore');
		devpSysElmConnectorS.proxy.extraParams={searchCondition:{}};
		me.devpSysElmConnectorStore = devpSysElmConnectorStore;
		devpSysElmConnectorStore.load();
	}

});