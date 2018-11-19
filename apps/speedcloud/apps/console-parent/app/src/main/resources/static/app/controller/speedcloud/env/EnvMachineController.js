Ext.define('AM.controller.speedcloud.env.EnvMachineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var envMachinePanel = center.child('speedcloud\\.env\\.EnvMachinePanel');
        if(!envMachinePanel){
            envMachinePanel = Ext.create('AM.view.speedcloud.env.EnvMachinePanel', {closable:true});

            center.add(envMachinePanel);
            center.setActiveTab(envMachinePanel);
        }
        center.setActiveTab(envMachinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var envMachinePanel = center.child('speedcloud\\.env\\.EnvMachineMainPanel');
         if(!envMachinePanel){
             envMachinePanel = Ext.create('AM.view.speedcloud.env.EnvMachineMainPanel',{closable:true});

             center.add(envMachinePanel);
             center.setActiveTab(envMachinePanel);
         }
         center.setActiveTab(envMachinePanel);
     }

})