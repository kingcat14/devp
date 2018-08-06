Ext.define('AM.controller.maintenance.rdc.config.EnvConfigLevelController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var envConfigLevelPanel = center.child('maintenance\\.rdc\\.config\\.EnvConfigLevelPanel');
        if(!envConfigLevelPanel){
            envConfigLevelPanel = Ext.create('AM.view.maintenance.rdc.config.EnvConfigLevelPanel', {closable:true});

            var envConfigLevelStore = Ext.create('AM.store.maintenance.rdc.config.EnvConfigLevelStore');
            envConfigLevelStore.proxy.extraParams={searchCondition:{}};
            envConfigLevelPanel.setStore(envConfigLevelStore);
            envConfigLevelStore.load();

            center.add(envConfigLevelPanel);
            center.setActiveTab(envConfigLevelPanel);
        }
        center.setActiveTab(envConfigLevelPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var envConfigLevelPanel = center.child('maintenance\\.rdc\\.config\\.EnvConfigLevelMainPanel');
         if(!envConfigLevelPanel){
             envConfigLevelPanel = Ext.create('AM.view.maintenance.rdc.config.EnvConfigLevelMainPanel',{closable:true});

             center.add(envConfigLevelPanel);
             center.setActiveTab(envConfigLevelPanel);
         }
         center.setActiveTab(envConfigLevelPanel);
     }

})