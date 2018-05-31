Ext.define('AM.controller.product.sys.DevpSysElmInstanceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysElmInstancePanel = center.child('product\\.sys\\.DevpSysElmInstancePanel');
        if(!devpSysElmInstancePanel){
            devpSysElmInstancePanel = Ext.create('AM.view.product.sys.DevpSysElmInstancePanel', {closable:true});

            var devpSysElmInstanceStore = Ext.create('AM.store.product.sys.DevpSysElmInstanceStore');
            devpSysElmInstanceStore.proxy.extraParams={searchCondition:{}};
            devpSysElmInstancePanel.setStore(devpSysElmInstanceStore);
            devpSysElmInstanceStore.load();

            center.add(devpSysElmInstancePanel);
            center.setActiveTab(devpSysElmInstancePanel);
        }
        center.setActiveTab(devpSysElmInstancePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysElmInstancePanel = center.child('product\\.sys\\.DevpSysElmInstanceMainPanel');
         if(!devpSysElmInstancePanel){
             devpSysElmInstancePanel = Ext.create('AM.view.product.sys.DevpSysElmInstanceMainPanel',{closable:true});

             center.add(devpSysElmInstancePanel);
             center.setActiveTab(devpSysElmInstancePanel);
         }
         center.setActiveTab(devpSysElmInstancePanel);
     }

})