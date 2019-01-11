Ext.define('AM.controller.application.application.AppController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var appPanel = center.child('application\\.application\\.AppPanel');
        if(!appPanel){
            appPanel = Ext.create('AM.view.application.application.AppPanel', {closable:true});

            var appStore = Ext.create('AM.store.application.application.AppStore');
            appStore.proxy.extraParams={searchCondition:{}};
            appPanel.setStore(appStore);
            appStore.load();

            center.add(appPanel);
            center.setActiveTab(appPanel);
        }
        center.setActiveTab(appPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var appPanel = center.child('application\\.application\\.AppMainPanel');
         if(!appPanel){
             appPanel = Ext.create('AM.view.application.application.AppMainPanel',{closable:true});

             center.add(appPanel);
             center.setActiveTab(appPanel);
         }
         center.setActiveTab(appPanel);
     }

})