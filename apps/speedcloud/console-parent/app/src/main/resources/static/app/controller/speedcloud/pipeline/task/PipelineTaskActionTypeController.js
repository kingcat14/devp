Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskActionTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskActionTypePanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionTypePanel');
        if(!pipelineTaskActionTypePanel){
            pipelineTaskActionTypePanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePanel', {closable:true});

            center.add(pipelineTaskActionTypePanel);
            center.setActiveTab(pipelineTaskActionTypePanel);
        }
        center.setActiveTab(pipelineTaskActionTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskActionTypePanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionTypeMainPanel');
         if(!pipelineTaskActionTypePanel){
             pipelineTaskActionTypePanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypeMainPanel',{closable:true});

             center.add(pipelineTaskActionTypePanel);
             center.setActiveTab(pipelineTaskActionTypePanel);
         }
         center.setActiveTab(pipelineTaskActionTypePanel);
     }

})