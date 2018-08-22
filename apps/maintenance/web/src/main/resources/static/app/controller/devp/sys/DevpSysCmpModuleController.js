Ext.define('AM.controller.devp.sys.DevpSysCmpModuleController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysCmpModulePanel = center.child('product\\.sys\\.DevpSysCmpModulePanel');
        if(!devpSysCmpModulePanel){
            devpSysCmpModulePanel = Ext.create('AM.view.devp.sys.DevpSysCmpModulePanel', {closable:true});

            var devpSysCmpModuleStore = Ext.create('AM.store.devp.sys.DevpSysCmpModuleStore');
            devpSysCmpModuleStore.proxy.extraParams={searchCondition:{}};
            devpSysCmpModulePanel.setStore(devpSysCmpModuleStore);
            devpSysCmpModuleStore.load();

            center.add(devpSysCmpModulePanel);
            center.setActiveTab(devpSysCmpModulePanel);
        }
        center.setActiveTab(devpSysCmpModulePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysCmpModulePanel = center.child('product\\.sys\\.DevpSysCmpModuleMainPanel');
         if(!devpSysCmpModulePanel){
             devpSysCmpModulePanel = Ext.create('AM.view.devp.sys.DevpSysCmpModuleMainPanel',{closable:true});

             center.add(devpSysCmpModulePanel);
             center.setActiveTab(devpSysCmpModulePanel);
         }
         center.setActiveTab(devpSysCmpModulePanel);
     }

})