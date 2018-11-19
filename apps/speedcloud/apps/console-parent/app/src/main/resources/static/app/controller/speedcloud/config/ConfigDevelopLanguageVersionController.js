Ext.define('AM.controller.speedcloud.config.ConfigDevelopLanguageVersionController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var configDevelopLanguageVersionPanel = center.child('speedcloud\\.config\\.ConfigDevelopLanguageVersionPanel');
        if(!configDevelopLanguageVersionPanel){
            configDevelopLanguageVersionPanel = Ext.create('AM.view.speedcloud.config.ConfigDevelopLanguageVersionPanel', {closable:true});

            center.add(configDevelopLanguageVersionPanel);
            center.setActiveTab(configDevelopLanguageVersionPanel);
        }
        center.setActiveTab(configDevelopLanguageVersionPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var configDevelopLanguageVersionPanel = center.child('speedcloud\\.config\\.ConfigDevelopLanguageVersionMainPanel');
         if(!configDevelopLanguageVersionPanel){
             configDevelopLanguageVersionPanel = Ext.create('AM.view.speedcloud.config.ConfigDevelopLanguageVersionMainPanel',{closable:true});

             center.add(configDevelopLanguageVersionPanel);
             center.setActiveTab(configDevelopLanguageVersionPanel);
         }
         center.setActiveTab(configDevelopLanguageVersionPanel);
     }

})