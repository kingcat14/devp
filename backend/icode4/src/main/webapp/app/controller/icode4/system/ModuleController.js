Ext.define('AM.controller.icode4.system.ModuleController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var modulePanel = center.child('icode4\\.system\\.ModulePanel');
        if(!modulePanel){
            modulePanel = Ext.create('AM.view.icode4.system.ModulePanel', {closable:true});

            var moduleStore = Ext.create('AM.store.icode4.system.ModuleStore');
            moduleStore.proxy.extraParams={searchCondition:{}};
            modulePanel.setStore(moduleStore);
            moduleStore.load();

            center.add(modulePanel);
            center.setActiveTab(modulePanel);
        }
        center.setActiveTab(modulePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var modulePanel = center.child('icode4\\.system\\.ModuleMainPanel');
         if(!modulePanel){
             modulePanel = Ext.create('AM.view.icode4.system.ModuleMainPanel',{closable:true});

             center.add(modulePanel);
             center.setActiveTab(modulePanel);
         }
         center.setActiveTab(modulePanel);
     }

})