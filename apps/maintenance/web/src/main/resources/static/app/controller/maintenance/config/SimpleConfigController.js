Ext.define('AM.controller.maintenance.config.SimpleConfigController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var simpleConfigPanel = center.child('maintenance\\.config\\.SimpleConfigPanel');
        if(!simpleConfigPanel){
            simpleConfigPanel = Ext.create('AM.view.maintenance.config.SimpleConfigPanel', {closable:true});

            var simpleConfigStore = Ext.create('AM.store.maintenance.config.SimpleConfigStore');
            simpleConfigStore.proxy.extraParams={searchCondition:{}};
            simpleConfigPanel.setStore(simpleConfigStore);
            simpleConfigStore.load();

            center.add(simpleConfigPanel);
            center.setActiveTab(simpleConfigPanel);
        }
        center.setActiveTab(simpleConfigPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var simpleConfigPanel = center.child('maintenance\\.config\\.SimpleConfigMainPanel');
         if(!simpleConfigPanel){
             simpleConfigPanel = Ext.create('AM.view.maintenance.config.SimpleConfigMainPanel',{closable:true});

             center.add(simpleConfigPanel);
             center.setActiveTab(simpleConfigPanel);
         }
         center.setActiveTab(simpleConfigPanel);
     }

})