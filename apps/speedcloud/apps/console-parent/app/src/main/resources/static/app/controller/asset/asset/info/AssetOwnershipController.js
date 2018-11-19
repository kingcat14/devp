Ext.define('AM.controller.asset.asset.info.AssetOwnershipController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetOwnershipPanel = center.child('asset\\.asset\\.info\\.AssetOwnershipPanel');
        if(!assetOwnershipPanel){
            assetOwnershipPanel = Ext.create('AM.view.asset.asset.info.AssetOwnershipPanel', {closable:true});

            center.add(assetOwnershipPanel);
            center.setActiveTab(assetOwnershipPanel);
        }
        center.setActiveTab(assetOwnershipPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetOwnershipPanel = center.child('asset\\.asset\\.info\\.AssetOwnershipMainPanel');
         if(!assetOwnershipPanel){
             assetOwnershipPanel = Ext.create('AM.view.asset.asset.info.AssetOwnershipMainPanel',{closable:true});

             center.add(assetOwnershipPanel);
             center.setActiveTab(assetOwnershipPanel);
         }
         center.setActiveTab(assetOwnershipPanel);
     }

})