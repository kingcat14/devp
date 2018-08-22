Ext.define('AM.controller.maintenance.asset.info.AssetTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        // this.initPanel(application);
        this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetTypePanel = center.child('maintenance\\.asset\\.info\\.AssetTypePanel');
        if(!assetTypePanel){
            assetTypePanel = Ext.create('AM.view.maintenance.asset.info.AssetTypePanel', {closable:true});

            var assetTypeStore = Ext.create('AM.store.maintenance.asset.info.AssetTypeStore');
            assetTypeStore.proxy.extraParams={searchCondition:{}};
            assetTypePanel.setStore(assetTypeStore);
            assetTypeStore.load();

            center.add(assetTypePanel);
            center.setActiveTab(assetTypePanel);
        }
        center.setActiveTab(assetTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var assetTypePanel = center.child('maintenance\\.asset\\.info\\.AssetTypeMainPanel');
         if(!assetTypePanel){
             assetTypePanel = Ext.create('AM.view.maintenance.asset.info.AssetTypeMainPanel',{closable:true});

             center.add(assetTypePanel);
             center.setActiveTab(assetTypePanel);
         }
         center.setActiveTab(assetTypePanel);
     }

})