Ext.define('AM.controller.product.sys.DevpSysCmpController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysCmpPanel = center.child('product\\.sys\\.DevpSysCmpPanel');
        if(!devpSysCmpPanel){
            devpSysCmpPanel = Ext.create('AM.view.product.sys.DevpSysCmpPanel', {closable:true});

            var devpSysCmpStore = Ext.create('AM.store.product.sys.DevpSysCmpStore');
            devpSysCmpStore.proxy.extraParams={searchCondition:{}};
            devpSysCmpPanel.setStore(devpSysCmpStore);
            devpSysCmpStore.load();

            center.add(devpSysCmpPanel);
            center.setActiveTab(devpSysCmpPanel);
        }
        center.setActiveTab(devpSysCmpPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysCmpPanel = center.child('product\\.sys\\.DevpSysCmpMainPanel');
         if(!devpSysCmpPanel){
             devpSysCmpPanel = Ext.create('AM.view.product.sys.DevpSysCmpMainPanel',{closable:true});

             center.add(devpSysCmpPanel);
             center.setActiveTab(devpSysCmpPanel);
         }
         center.setActiveTab(devpSysCmpPanel);
     }

})