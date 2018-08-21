Ext.define('AM.controller.maintenance.hardware.MachineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var machinePanel = center.child('maintenance\\.hardware\\.MachinePanel');
        if(!machinePanel){
            machinePanel = Ext.create('AM.view.maintenance.hardware.MachinePanel', {closable:true});

            center.add(machinePanel);
            center.setActiveTab(machinePanel);
        }
        center.setActiveTab(machinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var machinePanel = center.child('maintenance\\.hardware\\.MachineMainPanel');
         if(!machinePanel){
             machinePanel = Ext.create('AM.view.maintenance.hardware.MachineMainPanel',{closable:true});

             center.add(machinePanel);
             center.setActiveTab(machinePanel);
         }
         center.setActiveTab(machinePanel);
     }

})