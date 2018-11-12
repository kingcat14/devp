Ext.define('AM.controller.monitor.app.ApplicationInstanceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var applicationInstancePanel = center.child('monitor\\.app\\.ApplicationInstancePanel');
        if(!applicationInstancePanel){
            applicationInstancePanel = Ext.create('AM.view.monitor.app.ApplicationInstancePanel', {closable:true});

            center.add(applicationInstancePanel);
            center.setActiveTab(applicationInstancePanel);
        }
        center.setActiveTab(applicationInstancePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var applicationInstancePanel = center.child('monitor\\.app\\.ApplicationInstanceMainPanel');
         if(!applicationInstancePanel){
             applicationInstancePanel = Ext.create('AM.view.monitor.app.ApplicationInstanceMainPanel',{closable:true});

             center.add(applicationInstancePanel);
             center.setActiveTab(applicationInstancePanel);
         }
         center.setActiveTab(applicationInstancePanel);
     }

})