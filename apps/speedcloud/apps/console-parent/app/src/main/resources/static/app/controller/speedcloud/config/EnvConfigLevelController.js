Ext.define('AM.controller.speedcloud.config.EnvConfigLevelController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var envConfigLevelPanel = center.child('speedcloud\\.config\\.EnvConfigLevelPanel');
        if(!envConfigLevelPanel){
            envConfigLevelPanel = Ext.create('AM.view.speedcloud.config.EnvConfigLevelPanel', {closable:true});

            center.add(envConfigLevelPanel);
            center.setActiveTab(envConfigLevelPanel);
        }
        center.setActiveTab(envConfigLevelPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var envConfigLevelPanel = center.child('speedcloud\\.config\\.EnvConfigLevelMainPanel');
         if(!envConfigLevelPanel){
             envConfigLevelPanel = Ext.create('AM.view.speedcloud.config.EnvConfigLevelMainPanel',{closable:true});

             center.add(envConfigLevelPanel);
             center.setActiveTab(envConfigLevelPanel);
         }
         center.setActiveTab(envConfigLevelPanel);
     }

})