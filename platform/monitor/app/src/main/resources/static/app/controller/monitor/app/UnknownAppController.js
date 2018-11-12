Ext.define('AM.controller.monitor.app.UnknownAppController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var unknownAppPanel = center.child('monitor\\.app\\.UnknownAppPanel');
        if(!unknownAppPanel){
            unknownAppPanel = Ext.create('AM.view.monitor.app.UnknownAppPanel', {closable:true});

            center.add(unknownAppPanel);
            center.setActiveTab(unknownAppPanel);
        }
        center.setActiveTab(unknownAppPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var unknownAppPanel = center.child('monitor\\.app\\.UnknownAppMainPanel');
         if(!unknownAppPanel){
             unknownAppPanel = Ext.create('AM.view.monitor.app.UnknownAppMainPanel',{closable:true});

             center.add(unknownAppPanel);
             center.setActiveTab(unknownAppPanel);
         }
         center.setActiveTab(unknownAppPanel);
     }

})