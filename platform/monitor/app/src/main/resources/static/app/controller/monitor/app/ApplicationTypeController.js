Ext.define('AM.controller.monitor.app.ApplicationTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var applicationTypePanel = center.child('monitor\\.app\\.ApplicationTypePanel');
        if(!applicationTypePanel){
            applicationTypePanel = Ext.create('AM.view.monitor.app.ApplicationTypePanel', {closable:true});

            center.add(applicationTypePanel);
            center.setActiveTab(applicationTypePanel);
        }
        center.setActiveTab(applicationTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var applicationTypePanel = center.child('monitor\\.app\\.ApplicationTypeMainPanel');
         if(!applicationTypePanel){
             applicationTypePanel = Ext.create('AM.view.monitor.app.ApplicationTypeMainPanel',{closable:true});

             center.add(applicationTypePanel);
             center.setActiveTab(applicationTypePanel);
         }
         center.setActiveTab(applicationTypePanel);
     }

})