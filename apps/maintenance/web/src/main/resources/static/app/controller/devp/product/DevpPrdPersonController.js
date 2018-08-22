Ext.define('AM.controller.devp.product.DevpPrdPersonController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpPrdPersonPanel = center.child('product\\.product\\.DevpPrdPersonPanel');
        if(!devpPrdPersonPanel){
            devpPrdPersonPanel = Ext.create('AM.view.devp.product.DevpPrdPersonPanel', {closable:true});

            var devpPrdPersonStore = Ext.create('AM.store.devp.product.DevpPrdPersonStore');
            devpPrdPersonStore.proxy.extraParams={searchCondition:{}};
            devpPrdPersonPanel.setStore(devpPrdPersonStore);
            devpPrdPersonStore.load();

            center.add(devpPrdPersonPanel);
            center.setActiveTab(devpPrdPersonPanel);
        }
        center.setActiveTab(devpPrdPersonPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpPrdPersonPanel = center.child('product\\.product\\.DevpPrdPersonMainPanel');
         if(!devpPrdPersonPanel){
             devpPrdPersonPanel = Ext.create('AM.view.devp.product.DevpPrdPersonMainPanel',{closable:true});

             center.add(devpPrdPersonPanel);
             center.setActiveTab(devpPrdPersonPanel);
         }
         center.setActiveTab(devpPrdPersonPanel);
     }

})