
Ext.define('AM.view.main.MainContentPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.mainContentPanel',

    frame: false,
	defaults: {
		//bodyPadding: 10,
		scrollable: true,
		closable: true,
		//border: false
	},

    initComponent: function() {
        var me = this;

        Ext.apply(me, {
            listeners: {
                beforerender: {
                    fn: me.onTabpanelBeforeRender,
                    scope: me
                }
            }
        });

        me.callParent(arguments);
    },

    onTabpanelBeforeRender: function(abstractcomponent, options) {
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

        // var portal = Ext.create('AM.view.user.List');
        // this.insert(0,portal);
        // this.setActiveTab(0);
    }

});