Ext.define('AM.controller.deploy.hardware.MachineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var machinePanel = center.child('deploy\\.hardware\\.MachinePanel');
        if(!machinePanel){
            machinePanel = Ext.create('AM.view.deploy.hardware.MachinePanel', {closable:true});

            var machineStore = Ext.create('AM.store.deploy.hardware.MachineStore');
            machineStore.proxy.extraParams={searchCondition:{}};
            machinePanel.setStore(machineStore);
            machineStore.load();

            center.add(machinePanel);
            center.setActiveTab(machinePanel);
        }
        center.setActiveTab(machinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var machinePanel = center.child('deploy\\.hardware\\.MachineMainPanel');
         if(!machinePanel){
             machinePanel = Ext.create('AM.view.deploy.hardware.MachineMainPanel',{closable:true});

             center.add(machinePanel);
             center.setActiveTab(machinePanel);
         }
         center.setActiveTab(machinePanel);
     }

})