Ext.define('AM.controller.platform.platform.application.ApplicationPasswordController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var applicationPasswordPanel = center.child('platform\\.platform\\.application\\.ApplicationPasswordPanel');
        if(!applicationPasswordPanel){
            applicationPasswordPanel = Ext.create('AM.view.platform.platform.application.ApplicationPasswordPanel', {closable:true});

            var applicationPasswordStore = Ext.create('AM.store.platform.platform.application.ApplicationPasswordStore');
            applicationPasswordStore.proxy.extraParams={searchCondition:{}};
            applicationPasswordPanel.setStore(applicationPasswordStore);
            applicationPasswordStore.load();

            center.add(applicationPasswordPanel);
            center.setActiveTab(applicationPasswordPanel);
        }
        center.setActiveTab(applicationPasswordPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var applicationPasswordPanel = center.child('platform\\.platform\\.application\\.ApplicationPasswordMainPanel');
         if(!applicationPasswordPanel){
             applicationPasswordPanel = Ext.create('AM.view.platform.platform.application.ApplicationPasswordMainPanel',{closable:true});

             center.add(applicationPasswordPanel);
             center.setActiveTab(applicationPasswordPanel);
         }
         center.setActiveTab(applicationPasswordPanel);
     }

})