Ext.define('AM.controller.speedcloud.deployscheme.ResourceTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourceTypePanel = center.child('speedcloud\\.deployscheme\\.ResourceTypePanel');
        if(!resourceTypePanel){
            resourceTypePanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceTypePanel', {closable:true});

            center.add(resourceTypePanel);
            center.setActiveTab(resourceTypePanel);
        }
        center.setActiveTab(resourceTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourceTypePanel = center.child('speedcloud\\.deployscheme\\.ResourceTypeMainPanel');
         if(!resourceTypePanel){
             resourceTypePanel = Ext.create('AM.view.speedcloud.deployScheme.ResourceTypeMainPanel',{closable:true});

             center.add(resourceTypePanel);
             center.setActiveTab(resourceTypePanel);
         }
         center.setActiveTab(resourceTypePanel);
     }

})