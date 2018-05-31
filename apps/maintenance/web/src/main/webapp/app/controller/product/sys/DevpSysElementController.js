Ext.define('AM.controller.product.sys.DevpSysElementController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysElementPanel = center.child('product\\.sys\\.DevpSysElementPanel');
        if(!devpSysElementPanel){
            devpSysElementPanel = Ext.create('AM.view.product.sys.DevpSysElementPanel', {closable:true});

            var devpSysElementStore = Ext.create('AM.store.product.sys.DevpSysElementStore');
            devpSysElementStore.proxy.extraParams={searchCondition:{}};
            devpSysElementPanel.setStore(devpSysElementStore);
            devpSysElementStore.load();

            center.add(devpSysElementPanel);
            center.setActiveTab(devpSysElementPanel);
        }
        center.setActiveTab(devpSysElementPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysElementPanel = center.child('product\\.sys\\.DevpSysElementMainPanel');
         if(!devpSysElementPanel){
             devpSysElementPanel = Ext.create('AM.view.product.sys.DevpSysElementMainPanel',{closable:true});

             center.add(devpSysElementPanel);
             center.setActiveTab(devpSysElementPanel);
         }
         center.setActiveTab(devpSysElementPanel);
     }

})