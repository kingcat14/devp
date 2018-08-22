Ext.define('AM.view.application.common.SimpleConfigTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'application.common.SimpleConfigTypeMainPanel'
    ,requires: [
        'AM.view.application.common.SimpleConfigTypePanel'
		,'AM.view.application.common.SimpleConfigPanel'
		,'AM.view.application.common.SimpleConfigTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '通用配置类型'
	,store:null
	,controller: 'common_SimpleConfigTypeController'
    ,initComponent: function() {
        var me = this;

		var simpleConfigTypeStore = Ext.create('AM.store.application.common.SimpleConfigTypeStore');
		simpleConfigTypeStore.proxy.extraParams={searchCondition:{}};
		simpleConfigTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'application.common.SimpleConfigTypePanel'
				    ,region: 'center'
				    ,itemId: 'simpleConfigTypeGrid'
					,reference: 'simpleConfigTypeGrid'
				    ,store: simpleConfigTypeStore
					,listeners: {
						itemdblclick: 'onMainPanelRowClick'
					}
			    }
			    ,{
		    		xtype: 'application.common.SimpleConfigPanel'
					,region: 'south'
                    ,reference: 'simpleConfigPanel'
                    ,split:true
                    ,collapsed: true
                    ,collapsible: true
					,height:'50%'
                    ,title:'配置项'
					,store:Ext.create('AM.store.application.common.SimpleConfigStore', {pageSize:1000})

				}
			]
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
		var panel = this.lookup('simpleConfigTypeGrid');
		panel.onBeforeShow();
	}
	,onPanelBeforeHide: function(abstractcomponent, options) {
		var panel = this.lookup('simpleConfigTypeGrid');
		panel.onPanelBeforeHide();
	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var simpleConfigTypeStore = Ext.create('AM.store.application.common.SimpleConfigTypeStore');
		simpleConfigTypeStore.proxy.extraParams={searchCondition:{}};
		me.simpleConfigTypeStore = simpleConfigTypeStore;
		simpleConfigTypeStore.load();
	}

});