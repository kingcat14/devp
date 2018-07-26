Ext.define('AM.view.deploy.deploy.DevpSysDpyResInstMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'deploy.deploy.DevpSysDpyResInstMainPanel'
    ,requires: [
        'AM.view.deploy.deploy.DevpSysDpyResInstPanel'
		,'AM.view.deploy.deploy.DevpSysDpyResInstController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '部署关联资源实例定义'
	,store:null
	,controller: 'deploy_DevpSysDpyResInstController'
    ,initComponent: function() {
        var me = this;

		var devpSysDpyResInstStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResInstStore');
		devpSysDpyResInstStore.proxy.extraParams={searchCondition:{}};
		devpSysDpyResInstStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'deploy.deploy.DevpSysDpyResInstPanel'
				    ,region: 'center'
				    ,itemId: 'devpSysDpyResInstGrid'
					,reference: 'devpSysDpyResInstGrid'
				    ,store: devpSysDpyResInstStore
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
		var panel = this.lookup('devpSysDpyResInstGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('devpSysDpyResInstGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var devpSysDpyResInstStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResInstStore');
		devpSysDpyResInstS.proxy.extraParams={searchCondition:{}};
		me.devpSysDpyResInstStore = devpSysDpyResInstStore;
		devpSysDpyResInstStore.load();
	}

});