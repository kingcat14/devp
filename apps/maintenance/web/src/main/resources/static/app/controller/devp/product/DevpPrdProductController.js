Ext.define('AM.controller.devp.product.DevpPrdProductController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpPrdProductPanel = center.child('product\\.product\\.DevpPrdProductPanel');
        if(!devpPrdProductPanel){
            devpPrdProductPanel = Ext.create('AM.view.devp.product.DevpPrdProductPanel', {closable:true});

            var devpPrdProductStore = Ext.create('AM.store.devp.product.DevpPrdProductStore');
            devpPrdProductStore.proxy.extraParams={searchCondition:{}};
            devpPrdProductPanel.setStore(devpPrdProductStore);
            devpPrdProductStore.load();

            center.add(devpPrdProductPanel);
            center.setActiveTab(devpPrdProductPanel);
        }
        center.setActiveTab(devpPrdProductPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpPrdProductPanel = center.child('product\\.product\\.DevpPrdProductMainPanel');
         if(!devpPrdProductPanel){
             devpPrdProductPanel = Ext.create('AM.view.devp.product.DevpPrdProductMainPanel',{closable:true});

             center.add(devpPrdProductPanel);
             center.setActiveTab(devpPrdProductPanel);
         }
         center.setActiveTab(devpPrdProductPanel);
     }

})