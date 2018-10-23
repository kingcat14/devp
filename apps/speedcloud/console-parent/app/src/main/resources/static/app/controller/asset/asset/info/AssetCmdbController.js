Ext.define('AM.controller.asset.asset.info.AssetCmdbController', {
    extend: 'Ext.app.Controller'
    , requires: [
        'AM.view.asset.asset.info.AssetCmdbPanel'
    ]
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetCmdbPanel = center.child('asset\\.asset\\.info\\.AssetCmdbPanel');
        if(!assetCmdbPanel){
            assetCmdbPanel = Ext.create('AM.view.asset.asset.info.AssetCmdbPanel', {closable:true});

            center.add(assetCmdbPanel);
            center.setActiveTab(assetCmdbPanel);
        }
        center.setActiveTab(assetCmdbPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetCmdbPanel = center.child('asset\\.asset\\.info\\.AssetCmdbMainPanel');
         if(!assetCmdbPanel){
             assetCmdbPanel = Ext.create('AM.view.asset.asset.info.AssetCmdbMainPanel',{closable:true});

             center.add(assetCmdbPanel);
             center.setActiveTab(assetCmdbPanel);
         }
         center.setActiveTab(assetCmdbPanel);
     }

})