Ext.define('AM.controller.devp.ops.DevpOpsAssetGroupController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpOpsAssetGroupPanel = center.child('deploy\\.ops\\.DevpOpsAssetGroupPanel');
        if(!devpOpsAssetGroupPanel){
            devpOpsAssetGroupPanel = Ext.create('AM.view.devp.ops.DevpOpsAssetGroupPanel', {closable:true});

            var devpOpsAssetGroupStore = Ext.create('AM.store.devp.ops.DevpOpsAssetGroupStore');
            devpOpsAssetGroupStore.proxy.extraParams={searchCondition:{}};
            devpOpsAssetGroupPanel.setStore(devpOpsAssetGroupStore);
            devpOpsAssetGroupStore.load();

            center.add(devpOpsAssetGroupPanel);
            center.setActiveTab(devpOpsAssetGroupPanel);
        }
        center.setActiveTab(devpOpsAssetGroupPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpOpsAssetGroupPanel = center.child('deploy\\.ops\\.DevpOpsAssetGroupMainPanel');
         if(!devpOpsAssetGroupPanel){
             devpOpsAssetGroupPanel = Ext.create('AM.view.devp.ops.DevpOpsAssetGroupMainPanel',{closable:true});

             center.add(devpOpsAssetGroupPanel);
             center.setActiveTab(devpOpsAssetGroupPanel);
         }
         center.setActiveTab(devpOpsAssetGroupPanel);
     }

})