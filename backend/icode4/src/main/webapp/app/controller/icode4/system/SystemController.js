Ext.define('AM.controller.icode4.system.SystemController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var systemPanel = center.child('icode4\\.system\\.SystemPanel');
        if(!systemPanel){
            systemPanel = Ext.create('AM.view.icode4.system.SystemPanel', {closable:true});

            var systemStore = Ext.create('AM.store.icode4.system.SystemStore');
            systemStore.proxy.extraParams={searchCondition:{}};
            systemPanel.setStore(systemStore);
            systemStore.load();

            center.add(systemPanel);
            center.setActiveTab(systemPanel);
        }
        center.setActiveTab(systemPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var systemPanel = center.child('icode4\\.system\\.SystemMainPanel');
         if(!systemPanel){
             systemPanel = Ext.create('AM.view.icode4.system.SystemMainPanel',{closable:true});

             center.add(systemPanel);
             center.setActiveTab(systemPanel);
         }
         center.setActiveTab(systemPanel);
     }

})