Ext.define('AM.controller.icode4.view.ViewModelController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var viewModelPanel = center.child('icode4\\.view\\.ViewModelPanel');
        if(!viewModelPanel){
            viewModelPanel = Ext.create('AM.view.icode4.view.ViewModelPanel', {closable:true});

            var viewModelStore = Ext.create('AM.store.icode4.view.ViewModelStore');
            viewModelStore.proxy.extraParams={searchCondition:{}};
            viewModelPanel.setStore(viewModelStore);
            viewModelStore.load();

            center.add(viewModelPanel);
            center.setActiveTab(viewModelPanel);
        }
        center.setActiveTab(viewModelPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var viewModelPanel = center.child('icode4\\.view\\.ViewModelMainPanel');
         if(!viewModelPanel){
             viewModelPanel = Ext.create('AM.view.icode4.view.ViewModelMainPanel',{closable:true});

             center.add(viewModelPanel);
             center.setActiveTab(viewModelPanel);
         }
         center.setActiveTab(viewModelPanel);
     }

})