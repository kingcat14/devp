Ext.define('AM.controller.speedcloud.deployscheme.ResourceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourcePanel = center.child('speedcloud\\.deployscheme\\.ResourcePanel');
        if(!resourcePanel){
            resourcePanel = Ext.create('AM.view.speedcloud.deployscheme.ResourcePanel', {closable:true});

            center.add(resourcePanel);
            center.setActiveTab(resourcePanel);
        }
        center.setActiveTab(resourcePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourcePanel = center.child('speedcloud\\.deployscheme\\.ResourceMainPanel');
         if(!resourcePanel){
             resourcePanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceMainPanel',{closable:true});

             center.add(resourcePanel);
             center.setActiveTab(resourcePanel);
         }
         center.setActiveTab(resourcePanel);
     }

})