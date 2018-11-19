Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskController', {
    extend: 'Ext.app.Controller'
    , requires: ['AM.view.speedcloud.pipeline.task.PipelineTaskPanel']
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskPanel');
        if(!pipelineTaskPanel){
            pipelineTaskPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskPanel', {closable:true});

            center.add(pipelineTaskPanel);
            center.setActiveTab(pipelineTaskPanel);
        }
        center.setActiveTab(pipelineTaskPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskMainPanel');
         if(!pipelineTaskPanel){
             pipelineTaskPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskMainPanel',{closable:true});

             center.add(pipelineTaskPanel);
             center.setActiveTab(pipelineTaskPanel);
         }
         center.setActiveTab(pipelineTaskPanel);
     }

})