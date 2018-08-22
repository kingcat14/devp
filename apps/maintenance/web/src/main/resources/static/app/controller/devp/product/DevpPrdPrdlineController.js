Ext.define('AM.controller.devp.product.DevpPrdPrdlineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpPrdPrdlinePanel = center.child('product\\.product\\.DevpPrdPrdlinePanel');
        if(!devpPrdPrdlinePanel){
            devpPrdPrdlinePanel = Ext.create('AM.view.devp.product.DevpPrdPrdlinePanel', {closable:true});

            var devpPrdPrdlineStore = Ext.create('AM.store.devp.product.DevpPrdPrdlineStore');
            devpPrdPrdlineStore.proxy.extraParams={searchCondition:{}};
            devpPrdPrdlinePanel.setStore(devpPrdPrdlineStore);
            devpPrdPrdlineStore.load();

            center.add(devpPrdPrdlinePanel);
            center.setActiveTab(devpPrdPrdlinePanel);
        }
        center.setActiveTab(devpPrdPrdlinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpPrdPrdlinePanel = center.child('product\\.product\\.DevpPrdPrdlineMainPanel');
         if(!devpPrdPrdlinePanel){
             devpPrdPrdlinePanel = Ext.create('AM.view.devp.product.DevpPrdPrdlineMainPanel',{closable:true});

             center.add(devpPrdPrdlinePanel);
             center.setActiveTab(devpPrdPrdlinePanel);
         }
         center.setActiveTab(devpPrdPrdlinePanel);
     }

})