Ext.define('AM.controller.speedcloud.project.ProjectSetController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var projectSetPanel = center.child('speedcloud\\.project\\.ProjectSetPanel');
        if(!projectSetPanel){
            projectSetPanel = Ext.create('AM.view.speedcloud.project.ProjectSetPanel', {closable:true});

            center.add(projectSetPanel);
            center.setActiveTab(projectSetPanel);
        }
        center.setActiveTab(projectSetPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var projectSetPanel = center.child('speedcloud\\.project\\.ProjectSetMainPanel');
         if(!projectSetPanel){
             projectSetPanel = Ext.create('AM.view.speedcloud.project.ProjectSetMainPanel',{closable:true});

             center.add(projectSetPanel);
             center.setActiveTab(projectSetPanel);
         }
         center.setActiveTab(projectSetPanel);
     }

})