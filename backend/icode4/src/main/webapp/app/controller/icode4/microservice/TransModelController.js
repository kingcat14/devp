Ext.define('AM.controller.icode4.microservice.TransModelController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var transModelPanel = center.child('icode4\\.microservice\\.TransModelPanel');
        if(!transModelPanel){
            transModelPanel = Ext.create('AM.view.icode4.microservice.TransModelPanel', {closable:true});

            var transModelStore = Ext.create('AM.store.icode4.microservice.TransModelStore');
            transModelStore.proxy.extraParams={searchCondition:{}};
            transModelPanel.setStore(transModelStore);
            transModelStore.load();

            center.add(transModelPanel);
            center.setActiveTab(transModelPanel);
        }
        center.setActiveTab(transModelPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var transModelPanel = center.child('icode4\\.microservice\\.TransModelMainPanel');
         if(!transModelPanel){
             transModelPanel = Ext.create('AM.view.icode4.microservice.TransModelMainPanel',{closable:true});

             center.add(transModelPanel);
             center.setActiveTab(transModelPanel);
         }
         center.setActiveTab(transModelPanel);
     }

})