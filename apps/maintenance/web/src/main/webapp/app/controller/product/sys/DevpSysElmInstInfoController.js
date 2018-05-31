Ext.define('AM.controller.product.sys.DevpSysElmInstInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysElmInstInfoPanel = center.child('product\\.sys\\.DevpSysElmInstInfoPanel');
        if(!devpSysElmInstInfoPanel){
            devpSysElmInstInfoPanel = Ext.create('AM.view.product.sys.DevpSysElmInstInfoPanel', {closable:true});

            var devpSysElmInstInfoStore = Ext.create('AM.store.product.sys.DevpSysElmInstInfoStore');
            devpSysElmInstInfoStore.proxy.extraParams={searchCondition:{}};
            devpSysElmInstInfoPanel.setStore(devpSysElmInstInfoStore);
            devpSysElmInstInfoStore.load();

            center.add(devpSysElmInstInfoPanel);
            center.setActiveTab(devpSysElmInstInfoPanel);
        }
        center.setActiveTab(devpSysElmInstInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysElmInstInfoPanel = center.child('product\\.sys\\.DevpSysElmInstInfoMainPanel');
         if(!devpSysElmInstInfoPanel){
             devpSysElmInstInfoPanel = Ext.create('AM.view.product.sys.DevpSysElmInstInfoMainPanel',{closable:true});

             center.add(devpSysElmInstInfoPanel);
             center.setActiveTab(devpSysElmInstInfoPanel);
         }
         center.setActiveTab(devpSysElmInstInfoPanel);
     }

})