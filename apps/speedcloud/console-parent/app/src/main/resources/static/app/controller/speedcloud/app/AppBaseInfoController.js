Ext.define('AM.controller.speedcloud.app.AppBaseInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var appBaseInfoPanel = center.child('speedcloud\\.app\\.AppBaseInfoPanel');
        if(!appBaseInfoPanel){
            appBaseInfoPanel = Ext.create('AM.view.speedcloud.app.AppBaseInfoPanel', {closable:true});

            center.add(appBaseInfoPanel);
            center.setActiveTab(appBaseInfoPanel);
        }
        center.setActiveTab(appBaseInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var appBaseInfoPanel = center.child('speedcloud\\.app\\.AppBaseInfoMainPanel');
         if(!appBaseInfoPanel){
             appBaseInfoPanel = Ext.create('AM.view.speedcloud.app.AppBaseInfoMainPanel',{closable:true});

             center.add(appBaseInfoPanel);
             center.setActiveTab(appBaseInfoPanel);
         }
         center.setActiveTab(appBaseInfoPanel);
     }

})