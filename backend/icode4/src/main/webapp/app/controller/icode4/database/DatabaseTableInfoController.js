Ext.define('AM.controller.icode4.database.DatabaseTableInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var databaseTableInfoPanel = center.child('icode4\\.database\\.DatabaseTableInfoPanel');
        if(!databaseTableInfoPanel){
            databaseTableInfoPanel = Ext.create('AM.view.icode4.database.DatabaseTableInfoPanel', {closable:true});

            var databaseTableInfoStore = Ext.create('AM.store.icode4.database.DatabaseTableInfoStore');
            databaseTableInfoStore.proxy.extraParams={searchCondition:{}};
            databaseTableInfoPanel.setStore(databaseTableInfoStore);
            databaseTableInfoStore.load();

            center.add(databaseTableInfoPanel);
            center.setActiveTab(databaseTableInfoPanel);
        }
        center.setActiveTab(databaseTableInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var databaseTableInfoPanel = center.child('icode4\\.database\\.DatabaseTableInfoMainPanel');
         if(!databaseTableInfoPanel){
             databaseTableInfoPanel = Ext.create('AM.view.icode4.database.DatabaseTableInfoMainPanel',{closable:true});

             center.add(databaseTableInfoPanel);
             center.setActiveTab(databaseTableInfoPanel);
         }
         center.setActiveTab(databaseTableInfoPanel);
     }

})