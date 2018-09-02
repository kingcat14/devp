Ext.define('AM.controller.speedcloud.pipeline.PipelineCodeRepositoryRelationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineCodeRepositoryRelationPanel = center.child('speedcloud\\.pipeline\\.PipelineCodeRepositoryRelationPanel');
        if(!pipelineCodeRepositoryRelationPanel){
            pipelineCodeRepositoryRelationPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationPanel', {closable:true});

            center.add(pipelineCodeRepositoryRelationPanel);
            center.setActiveTab(pipelineCodeRepositoryRelationPanel);
        }
        center.setActiveTab(pipelineCodeRepositoryRelationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineCodeRepositoryRelationPanel = center.child('speedcloud\\.pipeline\\.PipelineCodeRepositoryRelationMainPanel');
         if(!pipelineCodeRepositoryRelationPanel){
             pipelineCodeRepositoryRelationPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationMainPanel',{closable:true});

             center.add(pipelineCodeRepositoryRelationPanel);
             center.setActiveTab(pipelineCodeRepositoryRelationPanel);
         }
         center.setActiveTab(pipelineCodeRepositoryRelationPanel);
     }

})