Ext.define('AM.controller.speedcloud.deployscheme.ResourcePropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourcePropertyPanel = center.child('speedcloud\\.deployscheme\\.ResourcePropertyPanel');
        if(!resourcePropertyPanel){
            resourcePropertyPanel = Ext.create('AM.view.speedcloud.deployscheme.ResourcePropertyPanel', {closable:true});

            center.add(resourcePropertyPanel);
            center.setActiveTab(resourcePropertyPanel);
        }
        center.setActiveTab(resourcePropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourcePropertyPanel = center.child('speedcloud\\.deployscheme\\.ResourcePropertyMainPanel');
         if(!resourcePropertyPanel){
             resourcePropertyPanel = Ext.create('AM.view.speedcloud.deployScheme.ResourcePropertyMainPanel',{closable:true});

             center.add(resourcePropertyPanel);
             center.setActiveTab(resourcePropertyPanel);
         }
         center.setActiveTab(resourcePropertyPanel);
     }

})