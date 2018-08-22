Ext.define('AM.controller.maintenance.asset.info.AssetCmdbController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var assetCmdbPanel = center.child('maintenance\\.asset\\.info\\.AssetCmdbPanel');
        if(!assetCmdbPanel){
            assetCmdbPanel = Ext.create('AM.view.maintenance.asset.info.AssetCmdbPanel', {closable:true});
            center.add(assetCmdbPanel);
            center.setActiveTab(assetCmdbPanel);
        }
        center.setActiveTab(assetCmdbPanel);
    }


})