Ext.define('AM.controller.speedcloud.app.AppDevelopConfigController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var appDevelopConfigPanel = center.child('speedcloud\\.app\\.AppDevelopConfigPanel');
        if(!appDevelopConfigPanel){
            appDevelopConfigPanel = Ext.create('AM.view.speedcloud.app.AppDevelopConfigPanel', {closable:true});

            center.add(appDevelopConfigPanel);
            center.setActiveTab(appDevelopConfigPanel);
        }
        center.setActiveTab(appDevelopConfigPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var appDevelopConfigPanel = center.child('speedcloud\\.app\\.AppDevelopConfigMainPanel');
         if(!appDevelopConfigPanel){
             appDevelopConfigPanel = Ext.create('AM.view.speedcloud.app.AppDevelopConfigMainPanel',{closable:true});

             center.add(appDevelopConfigPanel);
             center.setActiveTab(appDevelopConfigPanel);
         }
         center.setActiveTab(appDevelopConfigPanel);
     }

})