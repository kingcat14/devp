Ext.define('AM.view.common.SimpleConfigTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'common.SimpleConfigTypeMainPanel'
    ,requires: [
        'AM.view.common.SimpleConfigTypePanel'
		,'AM.view.common.SimpleConfigPanel'
		,'AM.view.common.SimpleConfigTypeController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: '通用配置类型'
	,store:null
	,controller: 'common_SimpleConfigTypeController'
    ,initComponent: function() {
        var me = this;

		var simpleConfigTypeStore = Ext.create('AM.store.common.SimpleConfigTypeStore');
		simpleConfigTypeStore.proxy.extraParams={searchCondition:{}};
		simpleConfigTypeStore.load();

	    Ext.apply(me, {
		    items: [
			    {
				    xtype: 'common.SimpleConfigTypePanel'
				    ,region: 'center'
				    ,itemId: 'simpleConfigTypeGrid'
					,reference: 'simpleConfigTypeGrid'
					,title:'配置类型'
				    ,store: simpleConfigTypeStore
					,listeners: {
						itemdblclick: 'onMainPanelRowClick'
					}
			    }
			    ,{
		    		xtype: 'common.SimpleConfigPanel'
					,region: 'east'
                    ,reference: 'simpleConfigPanel'
                    ,split:true
                    ,collapsed: true
                    ,collapsible: true
					,width:'50%'
                    ,title:'配置项'
					,store:Ext.create('AM.store.common.SimpleConfigStore', {pageSize:1000})

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
		var simpleConfigTypeStore = Ext.create('AM.store.common.SimpleConfigTypeStore');
		simpleConfigTypeStore.proxy.extraParams={searchCondition:{}};
		me.simpleConfigTypeStore = simpleConfigTypeStore;
		simpleConfigTypeStore.load();
	}

});