Ext.define('AM.controller.maintenance.software.BusinessSoftwareController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var businessSoftwarePanel = center.child('maintenance\\.software\\.BusinessSoftwarePanel');
        if(!businessSoftwarePanel){
            businessSoftwarePanel = Ext.create('AM.view.maintenance.software.BusinessSoftwarePanel', {closable:true});

            var businessSoftwareStore = Ext.create('AM.store.maintenance.software.BusinessSoftwareStore');
            businessSoftwareStore.proxy.extraParams={searchCondition:{}};
            businessSoftwarePanel.setStore(businessSoftwareStore);
            businessSoftwareStore.load();

            center.add(businessSoftwarePanel);
            center.setActiveTab(businessSoftwarePanel);
        }
        center.setActiveTab(businessSoftwarePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var businessSoftwarePanel = center.child('maintenance\\.software\\.BusinessSoftwareMainPanel');
         if(!businessSoftwarePanel){
             businessSoftwarePanel = Ext.create('AM.view.maintenance.software.BusinessSoftwareMainPanel',{closable:true});

             center.add(businessSoftwarePanel);
             center.setActiveTab(businessSoftwarePanel);
         }
         center.setActiveTab(businessSoftwarePanel);
     }

})