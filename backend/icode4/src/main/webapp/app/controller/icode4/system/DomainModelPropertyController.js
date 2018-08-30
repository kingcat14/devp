Ext.define('AM.controller.icode4.system.DomainModelPropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var domainModelPropertyPanel = center.child('icode4\\.system\\.DomainModelPropertyPanel');
        if(!domainModelPropertyPanel){
            domainModelPropertyPanel = Ext.create('AM.view.icode4.system.DomainModelPropertyPanel', {closable:true});

            var domainModelPropertyStore = Ext.create('AM.store.icode4.system.DomainModelPropertyStore');
            domainModelPropertyStore.proxy.extraParams={searchCondition:{}};
            domainModelPropertyPanel.setStore(domainModelPropertyStore);
            domainModelPropertyStore.load();

            center.add(domainModelPropertyPanel);
            center.setActiveTab(domainModelPropertyPanel);
        }
        center.setActiveTab(domainModelPropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var domainModelPropertyPanel = center.child('icode4\\.system\\.DomainModelPropertyMainPanel');
         if(!domainModelPropertyPanel){
             domainModelPropertyPanel = Ext.create('AM.view.icode4.system.DomainModelPropertyMainPanel',{closable:true});

             center.add(domainModelPropertyPanel);
             center.setActiveTab(domainModelPropertyPanel);
         }
         center.setActiveTab(domainModelPropertyPanel);
     }

})