Ext.define('AM.controller.icode4.system.DomainModelController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var domainModelPanel = center.child('icode4\\.system\\.DomainModelPanel');
        if(!domainModelPanel){
            domainModelPanel = Ext.create('AM.view.icode4.system.DomainModelPanel', {closable:true});

            var domainModelStore = Ext.create('AM.store.icode4.system.DomainModelStore');
            domainModelStore.proxy.extraParams={searchCondition:{}};
            domainModelPanel.setStore(domainModelStore);
            domainModelStore.load();

            center.add(domainModelPanel);
            center.setActiveTab(domainModelPanel);
        }
        center.setActiveTab(domainModelPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var domainModelPanel = center.child('icode4\\.system\\.DomainModelMainPanel');
         if(!domainModelPanel){
             domainModelPanel = Ext.create('AM.view.icode4.system.DomainModelMainPanel',{closable:true});

             center.add(domainModelPanel);
             center.setActiveTab(domainModelPanel);
         }
         center.setActiveTab(domainModelPanel);
     }

})