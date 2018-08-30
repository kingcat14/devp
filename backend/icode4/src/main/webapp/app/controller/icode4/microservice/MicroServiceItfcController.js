Ext.define('AM.controller.icode4.microservice.MicroServiceItfcController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var microServiceItfcPanel = center.child('icode4\\.microservice\\.MicroServiceItfcPanel');
        if(!microServiceItfcPanel){
            microServiceItfcPanel = Ext.create('AM.view.icode4.microservice.MicroServiceItfcPanel', {closable:true});

            var microServiceItfcStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcStore');
            microServiceItfcStore.proxy.extraParams={searchCondition:{}};
            microServiceItfcPanel.setStore(microServiceItfcStore);
            microServiceItfcStore.load();

            center.add(microServiceItfcPanel);
            center.setActiveTab(microServiceItfcPanel);
        }
        center.setActiveTab(microServiceItfcPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var microServiceItfcPanel = center.child('icode4\\.microservice\\.MicroServiceItfcMainPanel');
         if(!microServiceItfcPanel){
             microServiceItfcPanel = Ext.create('AM.view.icode4.microservice.MicroServiceItfcMainPanel',{closable:true});

             center.add(microServiceItfcPanel);
             center.setActiveTab(microServiceItfcPanel);
         }
         center.setActiveTab(microServiceItfcPanel);
     }

})