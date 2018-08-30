Ext.define('AM.controller.speedcloud.project.ProjectController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var projectPanel = center.child('speedcloud\\.project\\.ProjectPanel');
        if(!projectPanel){
            projectPanel = Ext.create('AM.view.speedcloud.project.ProjectPanel', {closable:true});

            center.add(projectPanel);
            center.setActiveTab(projectPanel);
        }
        center.setActiveTab(projectPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var projectPanel = center.child('speedcloud\\.project\\.ProjectMainPanel');
         if(!projectPanel){
             projectPanel = Ext.create('AM.view.speedcloud.project.ProjectMainPanel',{closable:true});

             center.add(projectPanel);
             center.setActiveTab(projectPanel);
         }
         center.setActiveTab(projectPanel);
     }

})