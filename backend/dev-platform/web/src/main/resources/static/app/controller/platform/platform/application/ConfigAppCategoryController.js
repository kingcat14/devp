Ext.define('AM.controller.platform.platform.application.ConfigAppCategoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var configAppCategoryPanel = center.child('platform\\.platform\\.application\\.ConfigAppCategoryPanel');
        if(!configAppCategoryPanel){
            configAppCategoryPanel = Ext.create('AM.view.platform.platform.application.ConfigAppCategoryPanel', {closable:true});

            var configAppCategoryStore = Ext.create('AM.store.platform.platform.application.ConfigAppCategoryStore');
            configAppCategoryStore.proxy.extraParams={searchCondition:{}};
            configAppCategoryPanel.setStore(configAppCategoryStore);
            configAppCategoryStore.load();

            center.add(configAppCategoryPanel);
            center.setActiveTab(configAppCategoryPanel);
        }
        center.setActiveTab(configAppCategoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var configAppCategoryPanel = center.child('platform\\.platform\\.application\\.ConfigAppCategoryMainPanel');
         if(!configAppCategoryPanel){
             configAppCategoryPanel = Ext.create('AM.view.platform.platform.application.ConfigAppCategoryMainPanel',{closable:true});

             center.add(configAppCategoryPanel);
             center.setActiveTab(configAppCategoryPanel);
         }
         center.setActiveTab(configAppCategoryPanel);
     }

})