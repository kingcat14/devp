/**
 * @class Ext.app.PortalPanel
 * @extends Ext.panel.Panel
 * A {@link Ext.panel.Panel Panel} class used for providing drag-drop-enabled portal layouts.
 */
Ext.define('AM.view.main.WelcomePortal', {
    extend: 'AM.view.portal.PortalPanel',
    
    initComponent : function() {
        var me = this;
        function getTools(){
            return [{
                xtype: 'tool',
                type: 'gear',
                handler: function(e, target, panelHeader, tool){
                    var portlet = panelHeader.ownerCt;
                    portlet.setLoading('Loading...');
                    Ext.defer(function() {
                        portlet.setLoading(false);
                    }, 2000);
                }
            }];
        }
        me.items = [{
            id: 'col-1',
            items: [
                    Ext.create('AM.view.portlet.UserPortlet')
                    ]
        	}
//         	,{
// 	            id: 'col-2',
// 	            items: [
// 	                   Ext.create('AM.view.portlet.WorkloadChartPortlet')
// //	                   ,Ext.create('AM.view.portlet.RequestDayCountChartPortlet')
// //	                   ,Ext.create('AM.view.portlet.RequestHourStatisticChartPortlet')
//
// 	            ]
//         	}
        	];
        
        this.callParent();

        
    },

    
});
