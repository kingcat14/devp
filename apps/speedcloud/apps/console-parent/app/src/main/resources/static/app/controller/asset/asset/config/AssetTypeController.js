Ext.define('AM.controller.asset.asset.config.AssetTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        // this.initPanel(application);
        this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetTypePanel = center.child('asset\\.asset\\.config\\.AssetTypePanel');
        if(!assetTypePanel){
            assetTypePanel = Ext.create('AM.view.asset.asset.config.AssetTypePanel', {closable:true});

            center.add(assetTypePanel);
            center.setActiveTab(assetTypePanel);
        }
        center.setActiveTab(assetTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var assetTypePanel = center.child('asset\\.asset\\.config\\.AssetTypeMainPanel');
         if(!assetTypePanel){
             assetTypePanel = Ext.create('AM.view.asset.asset.config.AssetTypeMainPanel',{closable:true});

             center.add(assetTypePanel);
             center.setActiveTab(assetTypePanel);
         }
         center.setActiveTab(assetTypePanel);
     }

})