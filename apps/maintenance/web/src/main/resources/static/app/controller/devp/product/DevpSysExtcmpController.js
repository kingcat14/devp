Ext.define('AM.controller.devp.product.DevpSysExtcmpController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysExtcmpPanel = center.child('product\\.product\\.DevpSysExtcmpPanel');
        if(!devpSysExtcmpPanel){
            devpSysExtcmpPanel = Ext.create('AM.view.devp.product.DevpSysExtcmpPanel', {closable:true});

            var devpSysExtcmpStore = Ext.create('AM.store.devp.product.DevpSysExtcmpStore');
            devpSysExtcmpStore.proxy.extraParams={searchCondition:{}};
            devpSysExtcmpPanel.setStore(devpSysExtcmpStore);
            devpSysExtcmpStore.load();

            center.add(devpSysExtcmpPanel);
            center.setActiveTab(devpSysExtcmpPanel);
        }
        center.setActiveTab(devpSysExtcmpPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysExtcmpPanel = center.child('product\\.product\\.DevpSysExtcmpMainPanel');
         if(!devpSysExtcmpPanel){
             devpSysExtcmpPanel = Ext.create('AM.view.devp.product.DevpSysExtcmpMainPanel',{closable:true});

             center.add(devpSysExtcmpPanel);
             center.setActiveTab(devpSysExtcmpPanel);
         }
         center.setActiveTab(devpSysExtcmpPanel);
     }

})