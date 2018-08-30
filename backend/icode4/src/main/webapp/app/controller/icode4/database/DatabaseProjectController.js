Ext.define('AM.controller.icode4.database.DatabaseProjectController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var databaseProjectPanel = center.child('icode4\\.database\\.DatabaseProjectPanel');
        if(!databaseProjectPanel){
            databaseProjectPanel = Ext.create('AM.view.icode4.database.DatabaseProjectPanel', {closable:true});

            var databaseProjectStore = Ext.create('AM.store.icode4.database.DatabaseProjectStore');
            databaseProjectStore.proxy.extraParams={searchCondition:{}};
            databaseProjectPanel.setStore(databaseProjectStore);
            databaseProjectStore.load();

            center.add(databaseProjectPanel);
            center.setActiveTab(databaseProjectPanel);
        }
        center.setActiveTab(databaseProjectPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var databaseProjectPanel = center.child('icode4\\.database\\.DatabaseProjectMainPanel');
         if(!databaseProjectPanel){
             databaseProjectPanel = Ext.create('AM.view.icode4.database.DatabaseProjectMainPanel',{closable:true});

             center.add(databaseProjectPanel);
             center.setActiveTab(databaseProjectPanel);
         }
         center.setActiveTab(databaseProjectPanel);
     }

})