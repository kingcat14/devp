Ext.define('AM.controller.icode4.microservice.MicroServiceItfcParametersController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var microServiceItfcParametersPanel = center.child('icode4\\.microservice\\.MicroServiceItfcParametersPanel');
        if(!microServiceItfcParametersPanel){
            microServiceItfcParametersPanel = Ext.create('AM.view.icode4.microservice.MicroServiceItfcParametersPanel', {closable:true});

            var microServiceItfcParametersStore = Ext.create('AM.store.icode4.microservice.MicroServiceItfcParametersStore');
            microServiceItfcParametersStore.proxy.extraParams={searchCondition:{}};
            microServiceItfcParametersPanel.setStore(microServiceItfcParametersStore);
            microServiceItfcParametersStore.load();

            center.add(microServiceItfcParametersPanel);
            center.setActiveTab(microServiceItfcParametersPanel);
        }
        center.setActiveTab(microServiceItfcParametersPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var microServiceItfcParametersPanel = center.child('icode4\\.microservice\\.MicroServiceItfcParametersMainPanel');
         if(!microServiceItfcParametersPanel){
             microServiceItfcParametersPanel = Ext.create('AM.view.icode4.microservice.MicroServiceItfcParametersMainPanel',{closable:true});

             center.add(microServiceItfcParametersPanel);
             center.setActiveTab(microServiceItfcParametersPanel);
         }
         center.setActiveTab(microServiceItfcParametersPanel);
     }

})