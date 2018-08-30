Ext.define('AM.controller.icode4.system.PropertyTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var propertyTypePanel = center.child('icode4\\.system\\.PropertyTypePanel');
        if(!propertyTypePanel){
            propertyTypePanel = Ext.create('AM.view.icode4.system.PropertyTypePanel', {closable:true});

            var propertyTypeStore = Ext.create('AM.store.icode4.system.PropertyTypeStore');
            propertyTypeStore.proxy.extraParams={searchCondition:{}};
            propertyTypePanel.setStore(propertyTypeStore);
            propertyTypeStore.load();

            center.add(propertyTypePanel);
            center.setActiveTab(propertyTypePanel);
        }
        center.setActiveTab(propertyTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var propertyTypePanel = center.child('icode4\\.system\\.PropertyTypeMainPanel');
         if(!propertyTypePanel){
             propertyTypePanel = Ext.create('AM.view.icode4.system.PropertyTypeMainPanel',{closable:true});

             center.add(propertyTypePanel);
             center.setActiveTab(propertyTypePanel);
         }
         center.setActiveTab(propertyTypePanel);
     }

})