Ext.define('AM.controller.devp.sys.DevpSysElementInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysElementInfoPanel = center.child('product\\.sys\\.DevpSysElementInfoPanel');
        if(!devpSysElementInfoPanel){
            devpSysElementInfoPanel = Ext.create('AM.view.devp.sys.DevpSysElementInfoPanel', {closable:true});

            var devpSysElementInfoStore = Ext.create('AM.store.devp.sys.DevpSysElementInfoStore');
            devpSysElementInfoStore.proxy.extraParams={searchCondition:{}};
            devpSysElementInfoPanel.setStore(devpSysElementInfoStore);
            devpSysElementInfoStore.load();

            center.add(devpSysElementInfoPanel);
            center.setActiveTab(devpSysElementInfoPanel);
        }
        center.setActiveTab(devpSysElementInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysElementInfoPanel = center.child('product\\.sys\\.DevpSysElementInfoMainPanel');
         if(!devpSysElementInfoPanel){
             devpSysElementInfoPanel = Ext.create('AM.view.devp.sys.DevpSysElementInfoMainPanel',{closable:true});

             center.add(devpSysElementInfoPanel);
             center.setActiveTab(devpSysElementInfoPanel);
         }
         center.setActiveTab(devpSysElementInfoPanel);
     }

})