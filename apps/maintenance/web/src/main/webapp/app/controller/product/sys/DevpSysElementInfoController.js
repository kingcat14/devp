Ext.define('AM.controller.product.sys.DevpSysElementInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysElementInfoPanel = center.child('product\\.sys\\.DevpSysElementInfoPanel');
        if(!devpSysElementInfoPanel){
            devpSysElementInfoPanel = Ext.create('AM.view.product.sys.DevpSysElementInfoPanel', {closable:true});

            var devpSysElementInfoStore = Ext.create('AM.store.product.sys.DevpSysElementInfoStore');
            devpSysElementInfoStore.proxy.extraParams={searchCondition:{}};
            devpSysElementInfoPanel.setStore(devpSysElementInfoStore);
            devpSysElementInfoStore.load();

            center.add(devpSysElementInfoPanel);
            center.setActiveTab(devpSysElementInfoPanel);
        }
        center.setActiveTab(devpSysElementInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysElementInfoPanel = center.child('product\\.sys\\.DevpSysElementInfoMainPanel');
         if(!devpSysElementInfoPanel){
             devpSysElementInfoPanel = Ext.create('AM.view.product.sys.DevpSysElementInfoMainPanel',{closable:true});

             center.add(devpSysElementInfoPanel);
             center.setActiveTab(devpSysElementInfoPanel);
         }
         center.setActiveTab(devpSysElementInfoPanel);
     }

})