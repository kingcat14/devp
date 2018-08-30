Ext.define('AM.controller.speedcloud.env.MachineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var machinePanel = center.child('speedcloud\\.env\\.MachinePanel');
        if(!machinePanel){
            machinePanel = Ext.create('AM.view.speedcloud.env.MachinePanel', {closable:true});

            center.add(machinePanel);
            center.setActiveTab(machinePanel);
        }
        center.setActiveTab(machinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var machinePanel = center.child('speedcloud\\.env\\.MachineMainPanel');
         if(!machinePanel){
             machinePanel = Ext.create('AM.view.speedcloud.env.MachineMainPanel',{closable:true});

             center.add(machinePanel);
             center.setActiveTab(machinePanel);
         }
         center.setActiveTab(machinePanel);
     }

})