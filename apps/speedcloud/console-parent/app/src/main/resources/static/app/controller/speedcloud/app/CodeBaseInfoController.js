Ext.define('AM.controller.speedcloud.app.CodeBaseInfoController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var codeBaseInfoPanel = center.child('speedcloud\\.app\\.CodeBaseInfoPanel');
        if(!codeBaseInfoPanel){
            codeBaseInfoPanel = Ext.create('AM.view.speedcloud.app.CodeBaseInfoPanel', {closable:true});

            center.add(codeBaseInfoPanel);
            center.setActiveTab(codeBaseInfoPanel);
        }
        center.setActiveTab(codeBaseInfoPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var codeBaseInfoPanel = center.child('speedcloud\\.app\\.CodeBaseInfoMainPanel');
         if(!codeBaseInfoPanel){
             codeBaseInfoPanel = Ext.create('AM.view.speedcloud.app.CodeBaseInfoMainPanel',{closable:true});

             center.add(codeBaseInfoPanel);
             center.setActiveTab(codeBaseInfoPanel);
         }
         center.setActiveTab(codeBaseInfoPanel);
     }

})