Ext.define('AM.controller.speedcloud.app.CodeRepertoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var codeRepertoryPanel = center.child('speedcloud\\.app\\.CodeRepertoryPanel');
        if(!codeRepertoryPanel){
            codeRepertoryPanel = Ext.create('AM.view.speedcloud.app.CodeRepertoryPanel', {closable:true});

            center.add(codeRepertoryPanel);
            center.setActiveTab(codeRepertoryPanel);
        }
        center.setActiveTab(codeRepertoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var codeRepertoryPanel = center.child('speedcloud\\.app\\.CodeRepertoryMainPanel');
         if(!codeRepertoryPanel){
             codeRepertoryPanel = Ext.create('AM.view.speedcloud.app.CodeRepertoryMainPanel',{closable:true});

             center.add(codeRepertoryPanel);
             center.setActiveTab(codeRepertoryPanel);
         }
         center.setActiveTab(codeRepertoryPanel);
     }

})