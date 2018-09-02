Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskActionController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskActionPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionPanel');
        if(!pipelineTaskActionPanel){
            pipelineTaskActionPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionPanel', {closable:true});

            center.add(pipelineTaskActionPanel);
            center.setActiveTab(pipelineTaskActionPanel);
        }
        center.setActiveTab(pipelineTaskActionPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskActionPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionMainPanel');
         if(!pipelineTaskActionPanel){
             pipelineTaskActionPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionMainPanel',{closable:true});

             center.add(pipelineTaskActionPanel);
             center.setActiveTab(pipelineTaskActionPanel);
         }
         center.setActiveTab(pipelineTaskActionPanel);
     }

})