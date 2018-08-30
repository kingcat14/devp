Ext.define('AM.controller.icode4.microservice.TransModelPropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var transModelPropertyPanel = center.child('icode4\\.microservice\\.TransModelPropertyPanel');
        if(!transModelPropertyPanel){
            transModelPropertyPanel = Ext.create('AM.view.icode4.microservice.TransModelPropertyPanel', {closable:true});

            var transModelPropertyStore = Ext.create('AM.store.icode4.microservice.TransModelPropertyStore');
            transModelPropertyStore.proxy.extraParams={searchCondition:{}};
            transModelPropertyPanel.setStore(transModelPropertyStore);
            transModelPropertyStore.load();

            center.add(transModelPropertyPanel);
            center.setActiveTab(transModelPropertyPanel);
        }
        center.setActiveTab(transModelPropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var transModelPropertyPanel = center.child('icode4\\.microservice\\.TransModelPropertyMainPanel');
         if(!transModelPropertyPanel){
             transModelPropertyPanel = Ext.create('AM.view.icode4.microservice.TransModelPropertyMainPanel',{closable:true});

             center.add(transModelPropertyPanel);
             center.setActiveTab(transModelPropertyPanel);
         }
         center.setActiveTab(transModelPropertyPanel);
     }

})