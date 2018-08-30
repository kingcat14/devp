Ext.define('AM.controller.icode4.view.ViewModelPropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var viewModelPropertyPanel = center.child('icode4\\.view\\.ViewModelPropertyPanel');
        if(!viewModelPropertyPanel){
            viewModelPropertyPanel = Ext.create('AM.view.icode4.view.ViewModelPropertyPanel', {closable:true});

            var viewModelPropertyStore = Ext.create('AM.store.icode4.view.ViewModelPropertyStore');
            viewModelPropertyStore.proxy.extraParams={searchCondition:{}};
            viewModelPropertyPanel.setStore(viewModelPropertyStore);
            viewModelPropertyStore.load();

            center.add(viewModelPropertyPanel);
            center.setActiveTab(viewModelPropertyPanel);
        }
        center.setActiveTab(viewModelPropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var viewModelPropertyPanel = center.child('icode4\\.view\\.ViewModelPropertyMainPanel');
         if(!viewModelPropertyPanel){
             viewModelPropertyPanel = Ext.create('AM.view.icode4.view.ViewModelPropertyMainPanel',{closable:true});

             center.add(viewModelPropertyPanel);
             center.setActiveTab(viewModelPropertyPanel);
         }
         center.setActiveTab(viewModelPropertyPanel);
     }

})