Ext.define('AM.controller.icode4.system.ModelTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var modelTypePanel = center.child('icode4\\.system\\.ModelTypePanel');
        if(!modelTypePanel){
            modelTypePanel = Ext.create('AM.view.icode4.system.ModelTypePanel', {closable:true});

            var modelTypeStore = Ext.create('AM.store.icode4.system.ModelTypeStore');
            modelTypeStore.proxy.extraParams={searchCondition:{}};
            modelTypePanel.setStore(modelTypeStore);
            modelTypeStore.load();

            center.add(modelTypePanel);
            center.setActiveTab(modelTypePanel);
        }
        center.setActiveTab(modelTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var modelTypePanel = center.child('icode4\\.system\\.ModelTypeMainPanel');
         if(!modelTypePanel){
             modelTypePanel = Ext.create('AM.view.icode4.system.ModelTypeMainPanel',{closable:true});

             center.add(modelTypePanel);
             center.setActiveTab(modelTypePanel);
         }
         center.setActiveTab(modelTypePanel);
     }

})