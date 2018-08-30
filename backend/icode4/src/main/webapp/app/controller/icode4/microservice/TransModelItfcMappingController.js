Ext.define('AM.controller.icode4.microservice.TransModelItfcMappingController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var transModelItfcMappingPanel = center.child('icode4\\.microservice\\.TransModelItfcMappingPanel');
        if(!transModelItfcMappingPanel){
            transModelItfcMappingPanel = Ext.create('AM.view.icode4.microservice.TransModelItfcMappingPanel', {closable:true});

            var transModelItfcMappingStore = Ext.create('AM.store.icode4.microservice.TransModelItfcMappingStore');
            transModelItfcMappingStore.proxy.extraParams={searchCondition:{}};
            transModelItfcMappingPanel.setStore(transModelItfcMappingStore);
            transModelItfcMappingStore.load();

            center.add(transModelItfcMappingPanel);
            center.setActiveTab(transModelItfcMappingPanel);
        }
        center.setActiveTab(transModelItfcMappingPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var transModelItfcMappingPanel = center.child('icode4\\.microservice\\.TransModelItfcMappingMainPanel');
         if(!transModelItfcMappingPanel){
             transModelItfcMappingPanel = Ext.create('AM.view.icode4.microservice.TransModelItfcMappingMainPanel',{closable:true});

             center.add(transModelItfcMappingPanel);
             center.setActiveTab(transModelItfcMappingPanel);
         }
         center.setActiveTab(transModelItfcMappingPanel);
     }

})