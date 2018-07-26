Ext.define('AM.controller.deploy.deploy.DevpSysDpyResourcesController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDpyResourcesPanel = center.child('deploy\\.deploy\\.DevpSysDpyResourcesPanel');
        if(!devpSysDpyResourcesPanel){
            devpSysDpyResourcesPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyResourcesPanel', {closable:true});

            var devpSysDpyResourcesStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResourcesStore');
            devpSysDpyResourcesStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyResourcesPanel.setStore(devpSysDpyResourcesStore);
            devpSysDpyResourcesStore.load();

            center.add(devpSysDpyResourcesPanel);
            center.setActiveTab(devpSysDpyResourcesPanel);
        }
        center.setActiveTab(devpSysDpyResourcesPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourcesPanel = center.child('deploy\\.deploy\\.DevpSysDpyResourcesMainPanel');
         if(!devpSysDpyResourcesPanel){
             devpSysDpyResourcesPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyResourcesMainPanel',{closable:true});

             center.add(devpSysDpyResourcesPanel);
             center.setActiveTab(devpSysDpyResourcesPanel);
         }
         center.setActiveTab(devpSysDpyResourcesPanel);
     }

})