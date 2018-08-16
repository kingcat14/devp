Ext.define('AM.controller.maintenance.asset.info.AssetCategoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var assetCategoryPanel = center.child('maintenance\\.asset\\.info\\.AssetCategoryPanel');
        if(!assetCategoryPanel){
            assetCategoryPanel = Ext.create('AM.view.maintenance.asset.info.AssetCategoryPanel', {closable:true});

            var assetCategoryStore = Ext.create('AM.store.maintenance.asset.info.AssetCategoryStore');
            assetCategoryStore.proxy.extraParams={searchCondition:{}};
            assetCategoryPanel.setStore(assetCategoryStore);
            assetCategoryStore.load();

            center.add(assetCategoryPanel);
            center.setActiveTab(assetCategoryPanel);
        }
        center.setActiveTab(assetCategoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetCategoryPanel = center.child('maintenance\\.asset\\.info\\.AssetCategoryMainPanel');
         if(!assetCategoryPanel){
             assetCategoryPanel = Ext.create('AM.view.maintenance.asset.info.AssetCategoryMainPanel',{closable:true});

             center.add(assetCategoryPanel);
             center.setActiveTab(assetCategoryPanel);
         }
         center.setActiveTab(assetCategoryPanel);
     }

})