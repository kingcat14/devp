Ext.define('AM.controller.platform.platform.tenant.TenantController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        // this.initPanel(application);
        this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var tenantPanel = center.child('platform\\.platform\\.tenant\\.TenantPanel');
        if(!tenantPanel){
            tenantPanel = Ext.create('AM.view.platform.platform.tenant.TenantPanel', {closable:true});

            var tenantStore = Ext.create('AM.store.platform.platform.tenant.TenantStore');
            tenantStore.proxy.extraParams={searchCondition:{}};
            tenantPanel.setStore(tenantStore);
            tenantStore.load();

            center.add(tenantPanel);
            center.setActiveTab(tenantPanel);
        }
        center.setActiveTab(tenantPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var tenantPanel = center.child('platform\\.platform\\.tenant\\.TenantMainPanel');
         if(!tenantPanel){
             tenantPanel = Ext.create('AM.view.platform.platform.tenant.TenantMainPanel',{closable:true});

             center.add(tenantPanel);
             center.setActiveTab(tenantPanel);
         }
         center.setActiveTab(tenantPanel);
     }

})