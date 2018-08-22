Ext.define('AM.view.maintenance.asset.info.AssetCmdbPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.asset.info.AssetCmdbPanel'
    ,requires: [
        'AM.view.maintenance.hardware.MachinePanel'
        ,'AM.view.maintenance.hardware.NetworkDevicePanel'
        ,'AM.view.maintenance.software.BusinessSoftwarePanel'
        ,'AM.view.maintenance.software.InfrastructuralSoftwarePanel'
        ,'AM.view.maintenance.software.SoftwareLicensePanel'
		,'AM.model.maintenance.asset.info.AssetTypeTreeNode'
        ,'AM.view.maintenance.asset.info.AssetCmdbController'
    ]
	,layout: {
		type: 'border'
	}
    ,title: 'IT资产管理'
	,bodyPadding:10
	,bodyCls: 'app-dashboard'
	,controller: 'maintenance.asset.info.AssetCmdbController'
    ,initComponent: function() {
        var me = this;

        var assetTreeStore = Ext.create('Ext.data.TreeStore', {autoLoad:false, model:'AM.model.maintenance.asset.info.AssetTypeTreeNode', nodeParam:'id'});

		// var machineStore = Ext.create('AM.store.maintenance.hardware.MachineStore');
		// machineStore.proxy.extraParams={searchCondition:{}};
		// machineStore.load();

	    Ext.apply(me, {
		    items: [
                {
                    xtype: 'treepanel'
                    ,title: '资产分类'
                    ,collapsible:true
                    ,region: 'west'
                    ,width: '30%'
                    ,frame: true
                    ,split: true
                    ,reference: 'assetTypeTree'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,store: assetTreeStore
                    ,columns:[
                        {
                            xtype: 'treecolumn'
                            ,dataIndex: 'name'
                            ,flex: 1
                            ,sortable: true
                            ,renderer: function(value, metaData, record) {
                                return record.get('name')+'('+record.get('code')+')'
                            }
                        }
                    ]
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'arrow_refresh',
                            handler: 'loadTypeTree'
                        }
                    ]
                    ,listeners: {
                        itemclick: 'onTreepanelItemClick'
                        ,afterrender: 'loadTypeTree'
                    }

                }
                ,{
		            xtype:'panel'
                    ,region: 'center'
                    ,layout: 'card'
                    ,reference: 'CENTER_REGION'
                    ,items:[
                        {
                            xtype: 'maintenance.hardware.MachinePanel'
                            ,itemId: 'ASSET_HOST_GRID'
                            ,frame: true
                            ,reference: 'ASSET_HOST_GRID'
                            // ,store: machineStore
                        }
                        ,{
                            xtype: 'maintenance.hardware.NetworkDevicePanel'
                            ,itemId: 'ASSET_NETWORK_GRID'
                            ,frame: true
                            ,reference: 'ASSET_NETWORK_GRID'
                        }
                        ,{
                            xtype: 'maintenance.software.BusinessSoftwarePanel'
                            ,itemId: 'ASSET_BIZ_SW_GRID'
                            ,frame: true
                            ,reference: 'ASSET_BIZ_SW_GRID'
                        }
                        ,{
                            xtype: 'maintenance.software.InfrastructuralSoftwarePanel'
                            ,itemId: 'ASSET_INFRA_SW_GRID'
                            ,frame: true
                            ,reference: 'ASSET_INFRA_SW_GRID'
                        }
                        ,{
                            xtype: 'maintenance.software.SoftwareLicensePanel'
                            ,itemId: 'ASSET_SVC_LIC_GRID'
                            ,frame: true
                            ,reference: 'ASSET_SVC_LIC_GRID'
                        }
                        , {
		                    xtype:'panel'
                            ,itemId: 'OTHER_GRID'
                            ,frame: true
                            ,reference: 'OTHER_GRID'
                            ,html:'暂时不支持'
                        }
                    ]
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

	}
	,onPanelBeforeHide: function(abstractcomponent, options) {

	}
	,onMainItemDblClick: function(tablepanel, record, item, index, e, options) {

		var me = this;

	}
	,initStore: function(){
		var me = this;
		var machineStore = Ext.create('AM.store.maintenance.hardware.MachineStore');
		machineStore.proxy.extraParams = {searchCondition:{}};
		me.machineStore = machineStore;
		machineStore.load();
	}

});