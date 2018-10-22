Ext.define('AM.controller.asset.asset.config.AssetTypePropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetTypePropertyPanel = center.child('asset\\.asset\\.config\\.AssetTypePropertyPanel');
        if(!assetTypePropertyPanel){
            assetTypePropertyPanel = Ext.create('AM.view.asset.asset.config.AssetTypePropertyPanel', {closable:true});

            center.add(assetTypePropertyPanel);
            center.setActiveTab(assetTypePropertyPanel);
        }
        center.setActiveTab(assetTypePropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetTypePropertyPanel = center.child('asset\\.asset\\.config\\.AssetTypePropertyMainPanel');
         if(!assetTypePropertyPanel){
             assetTypePropertyPanel = Ext.create('AM.view.asset.asset.config.AssetTypePropertyMainPanel',{closable:true});

             center.add(assetTypePropertyPanel);
             center.setActiveTab(assetTypePropertyPanel);
         }
         center.setActiveTab(assetTypePropertyPanel);
     }

})