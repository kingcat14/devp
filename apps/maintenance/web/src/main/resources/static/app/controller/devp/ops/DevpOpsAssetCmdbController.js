Ext.define('AM.controller.devp.ops.DevpOpsAssetCmdbController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpOpsAssetCmdbPanel = center.child('deploy\\.ops\\.DevpOpsAssetCmdbPanel');
        if(!devpOpsAssetCmdbPanel){
            devpOpsAssetCmdbPanel = Ext.create('AM.view.devp.ops.DevpOpsAssetCmdbPanel', {closable:true});

            var devpOpsAssetCmdbStore = Ext.create('AM.store.devp.ops.DevpOpsAssetCmdbStore');
            devpOpsAssetCmdbStore.proxy.extraParams={searchCondition:{}};
            devpOpsAssetCmdbPanel.setStore(devpOpsAssetCmdbStore);
            devpOpsAssetCmdbStore.load();

            center.add(devpOpsAssetCmdbPanel);
            center.setActiveTab(devpOpsAssetCmdbPanel);
        }
        center.setActiveTab(devpOpsAssetCmdbPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpOpsAssetCmdbPanel = center.child('deploy\\.ops\\.DevpOpsAssetCmdbMainPanel');
         if(!devpOpsAssetCmdbPanel){
             devpOpsAssetCmdbPanel = Ext.create('AM.view.devp.ops.DevpOpsAssetCmdbMainPanel',{closable:true});

             center.add(devpOpsAssetCmdbPanel);
             center.setActiveTab(devpOpsAssetCmdbPanel);
         }
         center.setActiveTab(devpOpsAssetCmdbPanel);
     }

})