Ext.define('AM.controller.speedcloud.config.DevelopTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var developTypePanel = center.child('speedcloud\\.config\\.DevelopTypePanel');
        if(!developTypePanel){
            developTypePanel = Ext.create('AM.view.speedcloud.config.DevelopTypePanel', {closable:true});

            center.add(developTypePanel);
            center.setActiveTab(developTypePanel);
        }
        center.setActiveTab(developTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var developTypePanel = center.child('speedcloud\\.config\\.DevelopTypeMainPanel');
         if(!developTypePanel){
             developTypePanel = Ext.create('AM.view.speedcloud.config.DevelopTypeMainPanel',{closable:true});

             center.add(developTypePanel);
             center.setActiveTab(developTypePanel);
         }
         center.setActiveTab(developTypePanel);
     }

})