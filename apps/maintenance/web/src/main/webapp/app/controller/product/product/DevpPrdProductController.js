Ext.define('AM.controller.product.product.DevpPrdProductController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpPrdProductPanel = center.child('product\\.product\\.DevpPrdProductPanel');
        if(!devpPrdProductPanel){
            devpPrdProductPanel = Ext.create('AM.view.product.product.DevpPrdProductPanel', {closable:true});

            var devpPrdProductStore = Ext.create('AM.store.product.product.DevpPrdProductStore');
            devpPrdProductStore.proxy.extraParams={searchCondition:{}};
            devpPrdProductPanel.setStore(devpPrdProductStore);
            devpPrdProductStore.load();

            center.add(devpPrdProductPanel);
            center.setActiveTab(devpPrdProductPanel);
        }
        center.setActiveTab(devpPrdProductPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpPrdProductPanel = center.child('product\\.product\\.DevpPrdProductMainPanel');
         if(!devpPrdProductPanel){
             devpPrdProductPanel = Ext.create('AM.view.product.product.DevpPrdProductMainPanel',{closable:true});

             center.add(devpPrdProductPanel);
             center.setActiveTab(devpPrdProductPanel);
         }
         center.setActiveTab(devpPrdProductPanel);
     }

})