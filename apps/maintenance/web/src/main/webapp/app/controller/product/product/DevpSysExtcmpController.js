Ext.define('AM.controller.product.product.DevpSysExtcmpController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysExtcmpPanel = center.child('product\\.product\\.DevpSysExtcmpPanel');
        if(!devpSysExtcmpPanel){
            devpSysExtcmpPanel = Ext.create('AM.view.product.product.DevpSysExtcmpPanel', {closable:true});

            var devpSysExtcmpStore = Ext.create('AM.store.product.product.DevpSysExtcmpStore');
            devpSysExtcmpStore.proxy.extraParams={searchCondition:{}};
            devpSysExtcmpPanel.setStore(devpSysExtcmpStore);
            devpSysExtcmpStore.load();

            center.add(devpSysExtcmpPanel);
            center.setActiveTab(devpSysExtcmpPanel);
        }
        center.setActiveTab(devpSysExtcmpPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysExtcmpPanel = center.child('product\\.product\\.DevpSysExtcmpMainPanel');
         if(!devpSysExtcmpPanel){
             devpSysExtcmpPanel = Ext.create('AM.view.product.product.DevpSysExtcmpMainPanel',{closable:true});

             center.add(devpSysExtcmpPanel);
             center.setActiveTab(devpSysExtcmpPanel);
         }
         center.setActiveTab(devpSysExtcmpPanel);
     }

})