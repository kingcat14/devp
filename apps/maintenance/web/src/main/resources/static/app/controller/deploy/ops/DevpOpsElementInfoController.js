Ext.define('AM.controller.deploy.ops.DevpOpsElementInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpOpsElementInfoPanel = center.child('deploy\\.ops\\.DevpOpsElementInfoPanel');
        if(!devpOpsElementInfoPanel){
            devpOpsElementInfoPanel = Ext.create('AM.view.deploy.ops.DevpOpsElementInfoPanel', {closable:true});

            var devpOpsElementInfoStore = Ext.create('AM.store.deploy.ops.DevpOpsElementInfoStore');
            devpOpsElementInfoStore.proxy.extraParams={searchCondition:{}};
            devpOpsElementInfoPanel.setStore(devpOpsElementInfoStore);
            devpOpsElementInfoStore.load();

            center.add(devpOpsElementInfoPanel);
            center.setActiveTab(devpOpsElementInfoPanel);
        }
        center.setActiveTab(devpOpsElementInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpOpsElementInfoPanel = center.child('deploy\\.ops\\.DevpOpsElementInfoMainPanel');
         if(!devpOpsElementInfoPanel){
             devpOpsElementInfoPanel = Ext.create('AM.view.deploy.ops.DevpOpsElementInfoMainPanel',{closable:true});

             center.add(devpOpsElementInfoPanel);
             center.setActiveTab(devpOpsElementInfoPanel);
         }
         center.setActiveTab(devpOpsElementInfoPanel);
     }

})