Ext.define('AM.controller.product.sys.DevpSysElmConnectorController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysElmConnectorPanel = center.child('product\\.sys\\.DevpSysElmConnectorPanel');
        if(!devpSysElmConnectorPanel){
            devpSysElmConnectorPanel = Ext.create('AM.view.product.sys.DevpSysElmConnectorPanel', {closable:true});

            var devpSysElmConnectorStore = Ext.create('AM.store.product.sys.DevpSysElmConnectorStore');
            devpSysElmConnectorStore.proxy.extraParams={searchCondition:{}};
            devpSysElmConnectorPanel.setStore(devpSysElmConnectorStore);
            devpSysElmConnectorStore.load();

            center.add(devpSysElmConnectorPanel);
            center.setActiveTab(devpSysElmConnectorPanel);
        }
        center.setActiveTab(devpSysElmConnectorPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysElmConnectorPanel = center.child('product\\.sys\\.DevpSysElmConnectorMainPanel');
         if(!devpSysElmConnectorPanel){
             devpSysElmConnectorPanel = Ext.create('AM.view.product.sys.DevpSysElmConnectorMainPanel',{closable:true});

             center.add(devpSysElmConnectorPanel);
             center.setActiveTab(devpSysElmConnectorPanel);
         }
         center.setActiveTab(devpSysElmConnectorPanel);
     }

})