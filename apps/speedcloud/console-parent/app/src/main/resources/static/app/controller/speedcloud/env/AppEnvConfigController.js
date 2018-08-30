Ext.define('AM.controller.speedcloud.env.AppEnvConfigController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var appEnvConfigPanel = center.child('speedcloud\\.env\\.AppEnvConfigPanel');
        if(!appEnvConfigPanel){
            appEnvConfigPanel = Ext.create('AM.view.speedcloud.env.AppEnvConfigPanel', {closable:true});

            center.add(appEnvConfigPanel);
            center.setActiveTab(appEnvConfigPanel);
        }
        center.setActiveTab(appEnvConfigPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var appEnvConfigPanel = center.child('speedcloud\\.env\\.AppEnvConfigMainPanel');
         if(!appEnvConfigPanel){
             appEnvConfigPanel = Ext.create('AM.view.speedcloud.env.AppEnvConfigMainPanel',{closable:true});

             center.add(appEnvConfigPanel);
             center.setActiveTab(appEnvConfigPanel);
         }
         center.setActiveTab(appEnvConfigPanel);
     }

})