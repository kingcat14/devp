Ext.define('AM.controller.monitor.app.ApplicationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var applicationPanel = center.child('monitor\\.app\\.ApplicationPanel');
        if(!applicationPanel){
            applicationPanel = Ext.create('AM.view.monitor.app.ApplicationPanel', {closable:true});

            center.add(applicationPanel);
            center.setActiveTab(applicationPanel);
        }
        center.setActiveTab(applicationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var applicationPanel = center.child('monitor\\.app\\.ApplicationMainPanel');
         if(!applicationPanel){
             applicationPanel = Ext.create('AM.view.monitor.app.ApplicationMainPanel',{closable:true});

             center.add(applicationPanel);
             center.setActiveTab(applicationPanel);
         }
         center.setActiveTab(applicationPanel);
     }

})