Ext.define('AM.controller.maintenance.hardware.NetworkDeviceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var networkDevicePanel = center.child('maintenance\\.hardware\\.NetworkDevicePanel');
        if(!networkDevicePanel){
            networkDevicePanel = Ext.create('AM.view.maintenance.hardware.NetworkDevicePanel', {closable:true});

            var networkDeviceStore = Ext.create('AM.store.maintenance.hardware.NetworkDeviceStore');
            networkDeviceStore.proxy.extraParams={searchCondition:{}};
            networkDevicePanel.setStore(networkDeviceStore);
            networkDeviceStore.load();

            center.add(networkDevicePanel);
            center.setActiveTab(networkDevicePanel);
        }
        center.setActiveTab(networkDevicePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var networkDevicePanel = center.child('maintenance\\.hardware\\.NetworkDeviceMainPanel');
         if(!networkDevicePanel){
             networkDevicePanel = Ext.create('AM.view.maintenance.hardware.NetworkDeviceMainPanel',{closable:true});

             center.add(networkDevicePanel);
             center.setActiveTab(networkDevicePanel);
         }
         center.setActiveTab(networkDevicePanel);
     }

})