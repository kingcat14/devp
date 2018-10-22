Ext.define('AM.controller.asset.asset.config.AssetCategoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetCategoryPanel = center.child('asset\\.asset\\.config\\.AssetCategoryPanel');
        if(!assetCategoryPanel){
            assetCategoryPanel = Ext.create('AM.view.asset.asset.config.AssetCategoryPanel', {closable:true});

            center.add(assetCategoryPanel);
            center.setActiveTab(assetCategoryPanel);
        }
        center.setActiveTab(assetCategoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetCategoryPanel = center.child('asset\\.asset\\.config\\.AssetCategoryMainPanel');
         if(!assetCategoryPanel){
             assetCategoryPanel = Ext.create('AM.view.asset.asset.config.AssetCategoryMainPanel',{closable:true});

             center.add(assetCategoryPanel);
             center.setActiveTab(assetCategoryPanel);
         }
         center.setActiveTab(assetCategoryPanel);
     }

})