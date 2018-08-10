Ext.define('AM.controller.platform.platform.tenant.TenantTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var tenantTypePanel = center.child('platform\\.platform\\.tenant\\.TenantTypePanel');
        if(!tenantTypePanel){
            tenantTypePanel = Ext.create('AM.view.platform.platform.tenant.TenantTypePanel', {closable:true});

            var tenantTypeStore = Ext.create('AM.store.platform.platform.tenant.TenantTypeStore');
            tenantTypeStore.proxy.extraParams={searchCondition:{}};
            tenantTypePanel.setStore(tenantTypeStore);
            tenantTypeStore.load();

            center.add(tenantTypePanel);
            center.setActiveTab(tenantTypePanel);
        }
        center.setActiveTab(tenantTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var tenantTypePanel = center.child('platform\\.platform\\.tenant\\.TenantTypeMainPanel');
         if(!tenantTypePanel){
             tenantTypePanel = Ext.create('AM.view.platform.platform.tenant.TenantTypeMainPanel',{closable:true});

             center.add(tenantTypePanel);
             center.setActiveTab(tenantTypePanel);
         }
         center.setActiveTab(tenantTypePanel);
     }

})