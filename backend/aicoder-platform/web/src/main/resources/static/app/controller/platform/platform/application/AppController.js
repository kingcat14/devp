Ext.define('AM.controller.platform.platform.application.AppController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var appPanel = center.child('platform\\.platform\\.application\\.AppPanel');
        if(!appPanel){
            appPanel = Ext.create('AM.view.platform.platform.application.AppPanel', {closable:true});

            var appStore = Ext.create('AM.store.platform.platform.application.AppStore');
            appStore.proxy.extraParams={searchCondition:{}};
            appPanel.setStore(appStore);
            appStore.load();

            center.add(appPanel);
            center.setActiveTab(appPanel);
        }
        center.setActiveTab(appPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var appPanel = center.child('platform\\.platform\\.application\\.AppMainPanel');
         if(!appPanel){
             appPanel = Ext.create('AM.view.platform.platform.application.AppMainPanel',{closable:true});

             center.add(appPanel);
             center.setActiveTab(appPanel);
         }
         center.setActiveTab(appPanel);
     }

})