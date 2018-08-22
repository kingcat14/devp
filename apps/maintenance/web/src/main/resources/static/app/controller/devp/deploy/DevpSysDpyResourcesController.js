Ext.define('AM.controller.devp.deploy.DevpSysDpyResourcesController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourcesPanel = center.child('deploy\\.deploy\\.DevpSysDpyResourcesPanel');
        if(!devpSysDpyResourcesPanel){
            devpSysDpyResourcesPanel = Ext.create('AM.view.devp.deploy.DevpSysDpyResourcesPanel', {closable:true});

            var devpSysDpyResourcesStore = Ext.create('AM.store.devp.deploy.DevpSysDpyResourcesStore');
            devpSysDpyResourcesStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyResourcesPanel.setStore(devpSysDpyResourcesStore);
            devpSysDpyResourcesStore.load();

            center.add(devpSysDpyResourcesPanel);
            center.setActiveTab(devpSysDpyResourcesPanel);
        }
        center.setActiveTab(devpSysDpyResourcesPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysDpyResourcesPanel = center.child('deploy\\.deploy\\.DevpSysDpyResourcesMainPanel');
         if(!devpSysDpyResourcesPanel){
             devpSysDpyResourcesPanel = Ext.create('AM.view.devp.deploy.DevpSysDpyResourcesMainPanel',{closable:true});

             center.add(devpSysDpyResourcesPanel);
             center.setActiveTab(devpSysDpyResourcesPanel);
         }
         center.setActiveTab(devpSysDpyResourcesPanel);
     }

})