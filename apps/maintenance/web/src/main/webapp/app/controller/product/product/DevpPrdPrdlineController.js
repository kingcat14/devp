Ext.define('AM.controller.product.product.DevpPrdPrdlineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpPrdPrdlinePanel = center.child('product\\.product\\.DevpPrdPrdlinePanel');
        if(!devpPrdPrdlinePanel){
            devpPrdPrdlinePanel = Ext.create('AM.view.product.product.DevpPrdPrdlinePanel', {closable:true});

            var devpPrdPrdlineStore = Ext.create('AM.store.product.product.DevpPrdPrdlineStore');
            devpPrdPrdlineStore.proxy.extraParams={searchCondition:{}};
            devpPrdPrdlinePanel.setStore(devpPrdPrdlineStore);
            devpPrdPrdlineStore.load();

            center.add(devpPrdPrdlinePanel);
            center.setActiveTab(devpPrdPrdlinePanel);
        }
        center.setActiveTab(devpPrdPrdlinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpPrdPrdlinePanel = center.child('product\\.product\\.DevpPrdPrdlineMainPanel');
         if(!devpPrdPrdlinePanel){
             devpPrdPrdlinePanel = Ext.create('AM.view.product.product.DevpPrdPrdlineMainPanel',{closable:true});

             center.add(devpPrdPrdlinePanel);
             center.setActiveTab(devpPrdPrdlinePanel);
         }
         center.setActiveTab(devpPrdPrdlinePanel);
     }

})