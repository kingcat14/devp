Ext.define('AM.controller.maintenance.rdc.config.DevelopTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var developTypePanel = center.child('maintenance\\.rdc\\.config\\.DevelopTypePanel');
        if(!developTypePanel){
            developTypePanel = Ext.create('AM.view.maintenance.rdc.config.DevelopTypePanel', {closable:true});

            var developTypeStore = Ext.create('AM.store.maintenance.rdc.config.DevelopTypeStore');
            developTypeStore.proxy.extraParams={searchCondition:{}};
            developTypePanel.setStore(developTypeStore);
            developTypeStore.load();

            center.add(developTypePanel);
            center.setActiveTab(developTypePanel);
        }
        center.setActiveTab(developTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var developTypePanel = center.child('maintenance\\.rdc\\.config\\.DevelopTypeMainPanel');
         if(!developTypePanel){
             developTypePanel = Ext.create('AM.view.maintenance.rdc.config.DevelopTypeMainPanel',{closable:true});

             center.add(developTypePanel);
             center.setActiveTab(developTypePanel);
         }
         center.setActiveTab(developTypePanel);
     }

})