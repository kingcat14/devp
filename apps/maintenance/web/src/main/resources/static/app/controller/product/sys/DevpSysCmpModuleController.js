Ext.define('AM.controller.product.sys.DevpSysCmpModuleController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysCmpModulePanel = center.child('product\\.sys\\.DevpSysCmpModulePanel');
        if(!devpSysCmpModulePanel){
            devpSysCmpModulePanel = Ext.create('AM.view.product.sys.DevpSysCmpModulePanel', {closable:true});

            var devpSysCmpModuleStore = Ext.create('AM.store.product.sys.DevpSysCmpModuleStore');
            devpSysCmpModuleStore.proxy.extraParams={searchCondition:{}};
            devpSysCmpModulePanel.setStore(devpSysCmpModuleStore);
            devpSysCmpModuleStore.load();

            center.add(devpSysCmpModulePanel);
            center.setActiveTab(devpSysCmpModulePanel);
        }
        center.setActiveTab(devpSysCmpModulePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysCmpModulePanel = center.child('product\\.sys\\.DevpSysCmpModuleMainPanel');
         if(!devpSysCmpModulePanel){
             devpSysCmpModulePanel = Ext.create('AM.view.product.sys.DevpSysCmpModuleMainPanel',{closable:true});

             center.add(devpSysCmpModulePanel);
             center.setActiveTab(devpSysCmpModulePanel);
         }
         center.setActiveTab(devpSysCmpModulePanel);
     }

})