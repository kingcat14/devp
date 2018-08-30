Ext.define('AM.controller.icode4.system.AmountRelationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var amountRelationPanel = center.child('icode4\\.system\\.AmountRelationPanel');
        if(!amountRelationPanel){
            amountRelationPanel = Ext.create('AM.view.icode4.system.AmountRelationPanel', {closable:true});

            var amountRelationStore = Ext.create('AM.store.icode4.system.AmountRelationStore');
            amountRelationStore.proxy.extraParams={searchCondition:{}};
            amountRelationPanel.setStore(amountRelationStore);
            amountRelationStore.load();

            center.add(amountRelationPanel);
            center.setActiveTab(amountRelationPanel);
        }
        center.setActiveTab(amountRelationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var amountRelationPanel = center.child('icode4\\.system\\.AmountRelationMainPanel');
         if(!amountRelationPanel){
             amountRelationPanel = Ext.create('AM.view.icode4.system.AmountRelationMainPanel',{closable:true});

             center.add(amountRelationPanel);
             center.setActiveTab(amountRelationPanel);
         }
         center.setActiveTab(amountRelationPanel);
     }

})