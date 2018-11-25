Ext.define('AM.controller.icode.domain.PropertyTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var propertyTypePanel = center.child('icode\\.domain\\.PropertyTypePanel');
        if(!propertyTypePanel){
            propertyTypePanel = Ext.create('AM.view.icode.domain.PropertyTypePanel', {closable:true});

            center.add(propertyTypePanel);
            center.setActiveTab(propertyTypePanel);
        }
        center.setActiveTab(propertyTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var propertyTypePanel = center.child('icode\\.domain\\.PropertyTypeMainPanel');
         if(!propertyTypePanel){
             propertyTypePanel = Ext.create('AM.view.icode.domain.PropertyTypeMainPanel',{closable:true});

             center.add(propertyTypePanel);
             center.setActiveTab(propertyTypePanel);
         }
         center.setActiveTab(propertyTypePanel);
     }

})