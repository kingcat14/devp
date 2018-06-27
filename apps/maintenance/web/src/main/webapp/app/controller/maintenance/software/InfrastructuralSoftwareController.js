Ext.define('AM.controller.maintenance.software.InfrastructuralSoftwareController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var infrastructuralSoftwarePanel = center.child('maintenance\\.software\\.InfrastructuralSoftwarePanel');
        if(!infrastructuralSoftwarePanel){
            infrastructuralSoftwarePanel = Ext.create('AM.view.maintenance.software.InfrastructuralSoftwarePanel', {closable:true});

            var infrastructuralSoftwareStore = Ext.create('AM.store.maintenance.software.InfrastructuralSoftwareStore');
            infrastructuralSoftwareStore.proxy.extraParams={searchCondition:{}};
            infrastructuralSoftwarePanel.setStore(infrastructuralSoftwareStore);
            infrastructuralSoftwareStore.load();

            center.add(infrastructuralSoftwarePanel);
            center.setActiveTab(infrastructuralSoftwarePanel);
        }
        center.setActiveTab(infrastructuralSoftwarePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var infrastructuralSoftwarePanel = center.child('maintenance\\.software\\.InfrastructuralSoftwareMainPanel');
         if(!infrastructuralSoftwarePanel){
             infrastructuralSoftwarePanel = Ext.create('AM.view.maintenance.software.InfrastructuralSoftwareMainPanel',{closable:true});

             center.add(infrastructuralSoftwarePanel);
             center.setActiveTab(infrastructuralSoftwarePanel);
         }
         center.setActiveTab(infrastructuralSoftwarePanel);
     }

})