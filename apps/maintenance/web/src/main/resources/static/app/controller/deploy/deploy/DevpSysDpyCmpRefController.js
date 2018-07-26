Ext.define('AM.controller.deploy.deploy.DevpSysDpyCmpRefController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDpyCmpRefPanel = center.child('deploy\\.deploy\\.DevpSysDpyCmpRefPanel');
        if(!devpSysDpyCmpRefPanel){
            devpSysDpyCmpRefPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyCmpRefPanel', {closable:true});

            var devpSysDpyCmpRefStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyCmpRefStore');
            devpSysDpyCmpRefStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyCmpRefPanel.setStore(devpSysDpyCmpRefStore);
            devpSysDpyCmpRefStore.load();

            center.add(devpSysDpyCmpRefPanel);
            center.setActiveTab(devpSysDpyCmpRefPanel);
        }
        center.setActiveTab(devpSysDpyCmpRefPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyCmpRefPanel = center.child('deploy\\.deploy\\.DevpSysDpyCmpRefMainPanel');
         if(!devpSysDpyCmpRefPanel){
             devpSysDpyCmpRefPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyCmpRefMainPanel',{closable:true});

             center.add(devpSysDpyCmpRefPanel);
             center.setActiveTab(devpSysDpyCmpRefPanel);
         }
         center.setActiveTab(devpSysDpyCmpRefPanel);
     }

})