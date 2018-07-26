Ext.define('AM.controller.product.product.DevpPrdLinePrdController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpPrdLinePrdPanel = center.child('product\\.product\\.DevpPrdLinePrdPanel');
        if(!devpPrdLinePrdPanel){
            devpPrdLinePrdPanel = Ext.create('AM.view.product.product.DevpPrdLinePrdPanel', {closable:true});

            var devpPrdLinePrdStore = Ext.create('AM.store.product.product.DevpPrdLinePrdStore');
            devpPrdLinePrdStore.proxy.extraParams={searchCondition:{}};
            devpPrdLinePrdPanel.setStore(devpPrdLinePrdStore);
            devpPrdLinePrdStore.load();

            center.add(devpPrdLinePrdPanel);
            center.setActiveTab(devpPrdLinePrdPanel);
        }
        center.setActiveTab(devpPrdLinePrdPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpPrdLinePrdPanel = center.child('product\\.product\\.DevpPrdLinePrdMainPanel');
         if(!devpPrdLinePrdPanel){
             devpPrdLinePrdPanel = Ext.create('AM.view.product.product.DevpPrdLinePrdMainPanel',{closable:true});

             center.add(devpPrdLinePrdPanel);
             center.setActiveTab(devpPrdLinePrdPanel);
         }
         center.setActiveTab(devpPrdLinePrdPanel);
     }

})