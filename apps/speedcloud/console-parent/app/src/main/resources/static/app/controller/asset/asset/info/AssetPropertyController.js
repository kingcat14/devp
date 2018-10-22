Ext.define('AM.controller.asset.asset.info.AssetPropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetPropertyPanel = center.child('asset\\.asset\\.info\\.AssetPropertyPanel');
        if(!assetPropertyPanel){
            assetPropertyPanel = Ext.create('AM.view.asset.asset.info.AssetPropertyPanel', {closable:true});

            center.add(assetPropertyPanel);
            center.setActiveTab(assetPropertyPanel);
        }
        center.setActiveTab(assetPropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var assetPropertyPanel = center.child('asset\\.asset\\.info\\.AssetPropertyMainPanel');
         if(!assetPropertyPanel){
             assetPropertyPanel = Ext.create('AM.view.asset.asset.info.AssetPropertyMainPanel',{closable:true});

             center.add(assetPropertyPanel);
             center.setActiveTab(assetPropertyPanel);
         }
         center.setActiveTab(assetPropertyPanel);
     }

})