Ext.define('AM.controller.speedcloud.app.SecurityConfigController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var securityConfigPanel = center.child('speedcloud\\.app\\.SecurityConfigPanel');
        if(!securityConfigPanel){
            securityConfigPanel = Ext.create('AM.view.speedcloud.app.SecurityConfigPanel', {closable:true});

            center.add(securityConfigPanel);
            center.setActiveTab(securityConfigPanel);
        }
        center.setActiveTab(securityConfigPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var securityConfigPanel = center.child('speedcloud\\.app\\.SecurityConfigMainPanel');
         if(!securityConfigPanel){
             securityConfigPanel = Ext.create('AM.view.speedcloud.app.SecurityConfigMainPanel',{closable:true});

             center.add(securityConfigPanel);
             center.setActiveTab(securityConfigPanel);
         }
         center.setActiveTab(securityConfigPanel);
     }

})