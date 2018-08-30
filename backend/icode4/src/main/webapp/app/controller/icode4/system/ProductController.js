Ext.define('AM.controller.icode4.system.ProductController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var productPanel = center.child('icode4\\.system\\.ProductPanel');
        if(!productPanel){
            productPanel = Ext.create('AM.view.icode4.system.ProductPanel', {closable:true});

            var productStore = Ext.create('AM.store.icode4.system.ProductStore');
            productStore.proxy.extraParams={searchCondition:{}};
            productPanel.setStore(productStore);
            productStore.load();

            center.add(productPanel);
            center.setActiveTab(productPanel);
        }
        center.setActiveTab(productPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var productPanel = center.child('icode4\\.system\\.ProductMainPanel');
         if(!productPanel){
             productPanel = Ext.create('AM.view.icode4.system.ProductMainPanel',{closable:true});

             center.add(productPanel);
             center.setActiveTab(productPanel);
         }
         center.setActiveTab(productPanel);
     }

})