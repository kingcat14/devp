Ext.define('AM.controller.speedcloud.app.CodeRepositoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var codeRepositoryPanel = center.child('speedcloud\\.app\\.CodeRepositoryPanel');
        if(!codeRepositoryPanel){
            codeRepositoryPanel = Ext.create('AM.view.speedcloud.app.CodeRepositoryPanel', {closable:true});

            center.add(codeRepositoryPanel);
            center.setActiveTab(codeRepositoryPanel);
        }
        center.setActiveTab(codeRepositoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var codeRepositoryPanel = center.child('speedcloud\\.app\\.CodeRepositoryMainPanel');
         if(!codeRepositoryPanel){
             codeRepositoryPanel = Ext.create('AM.view.speedcloud.app.CodeRepositoryMainPanel',{closable:true});

             center.add(codeRepositoryPanel);
             center.setActiveTab(codeRepositoryPanel);
         }
         center.setActiveTab(codeRepositoryPanel);
     }

})