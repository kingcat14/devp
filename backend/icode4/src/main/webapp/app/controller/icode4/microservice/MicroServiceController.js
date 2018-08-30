Ext.define('AM.controller.icode4.microservice.MicroServiceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var microServicePanel = center.child('icode4\\.microservice\\.MicroServicePanel');
        if(!microServicePanel){
            microServicePanel = Ext.create('AM.view.icode4.microservice.MicroServicePanel', {closable:true});

            var microServiceStore = Ext.create('AM.store.icode4.microservice.MicroServiceStore');
            microServiceStore.proxy.extraParams={searchCondition:{}};
            microServicePanel.setStore(microServiceStore);
            microServiceStore.load();

            center.add(microServicePanel);
            center.setActiveTab(microServicePanel);
        }
        center.setActiveTab(microServicePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var microServicePanel = center.child('icode4\\.microservice\\.MicroServiceMainPanel');
         if(!microServicePanel){
             microServicePanel = Ext.create('AM.view.icode4.microservice.MicroServiceMainPanel',{closable:true});

             center.add(microServicePanel);
             center.setActiveTab(microServicePanel);
         }
         center.setActiveTab(microServicePanel);
     }

})