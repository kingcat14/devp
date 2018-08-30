Ext.define('AM.controller.speedcloud.config.ConfigDevelopLanguageController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var configDevelopLanguagePanel = center.child('speedcloud\\.config\\.ConfigDevelopLanguagePanel');
        if(!configDevelopLanguagePanel){
            configDevelopLanguagePanel = Ext.create('AM.view.speedcloud.config.ConfigDevelopLanguagePanel', {closable:true});

            center.add(configDevelopLanguagePanel);
            center.setActiveTab(configDevelopLanguagePanel);
        }
        center.setActiveTab(configDevelopLanguagePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var configDevelopLanguagePanel = center.child('speedcloud\\.config\\.ConfigDevelopLanguageMainPanel');
         if(!configDevelopLanguagePanel){
             configDevelopLanguagePanel = Ext.create('AM.view.speedcloud.config.ConfigDevelopLanguageMainPanel',{closable:true});

             center.add(configDevelopLanguagePanel);
             center.setActiveTab(configDevelopLanguagePanel);
         }
         center.setActiveTab(configDevelopLanguagePanel);
     }

})