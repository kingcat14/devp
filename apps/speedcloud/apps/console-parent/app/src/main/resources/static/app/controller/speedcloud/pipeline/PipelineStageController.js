Ext.define('AM.controller.speedcloud.pipeline.PipelineStageController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineStagePanel = center.child('speedcloud\\.pipeline\\.PipelineStagePanel');
        if(!pipelineStagePanel){
            pipelineStagePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStagePanel', {closable:true});

            center.add(pipelineStagePanel);
            center.setActiveTab(pipelineStagePanel);
        }
        center.setActiveTab(pipelineStagePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineStagePanel = center.child('speedcloud\\.pipeline\\.PipelineStageMainPanel');
         if(!pipelineStagePanel){
             pipelineStagePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStageMainPanel',{closable:true});

             center.add(pipelineStagePanel);
             center.setActiveTab(pipelineStagePanel);
         }
         center.setActiveTab(pipelineStagePanel);
     }

})