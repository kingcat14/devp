Ext.define('AM.view.application.framework.MainContentPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.mainContentPanel',
	requires:[
        'AM.view.application.dashboard.Dashboard'
	    //,'AM.view.maintenance.asset.info.AssetCmdbPanel'

	]
	,defaults: {
		//bodyPadding: 10,
		scrollable: true,
		closable: true,
		//border: false
	}
    ,referenceHolder:true
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            listeners: {
                beforerender: {
                    fn: me.onTabpanelBeforeRender,
                    scope: me
                }
                ,createMainTabPanel: {fn:me.createMainTabPanel, scope:me }
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

	    // var portal = Ext.create('Ext.panel.Panel',{ title:'欢迎使用', html:"欢迎使用", closable:false, bodyCls: 'app-dashboard', iconAlign:'right', iconCls:'bullet_red' });
	    // this.insert(0,portal);
	    // this.setActiveTab(0);


	    var dashboard = Ext.create("AM.view.application.dashboard.Dashboard",{
			closable:false
	    })
	    this.insert(0,dashboard);
	    this.setActiveTab(0);

	    for(var i in Ext.dashboard){
            var panel = Ext.create(Ext.dashboard[i],{
                    closable:true
                })
            console.log(i)
            //this.insert(1,panel);
            this.add(panel)

        }
        if(Ext.dashboard.length > 0){
            this.setActiveTab(1);
        }

        // var assetCmdbPanel = Ext.create("AM.view.maintenance.asset.info.AssetCmdbPanel",{
        //     closable:true
        // })
        // this.insert(1,assetCmdbPanel);
        // this.setActiveTab(1);


    }
    ,createMainTabPanel: function(srcView, tabConfig){
        if(!tabConfig.reference){
            Ext.MessageBox.alert('创建tab页失败', '页面配置必须包含reference属性');
            return;
        }

        var panel = this.lookup(tabConfig.reference);

        if(!panel){
            panel = this.add(tabConfig)
        }

        this.setActiveTab(panel);
    }
});