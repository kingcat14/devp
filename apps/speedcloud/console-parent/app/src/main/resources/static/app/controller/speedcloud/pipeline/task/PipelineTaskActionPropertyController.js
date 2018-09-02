Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskActionPropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskActionPropertyPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionPropertyPanel');
        if(!pipelineTaskActionPropertyPanel){
            pipelineTaskActionPropertyPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertyPanel', {closable:true});

            center.add(pipelineTaskActionPropertyPanel);
            center.setActiveTab(pipelineTaskActionPropertyPanel);
        }
        center.setActiveTab(pipelineTaskActionPropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskActionPropertyPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionPropertyMainPanel');
         if(!pipelineTaskActionPropertyPanel){
             pipelineTaskActionPropertyPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionPropertyMainPanel',{closable:true});

             center.add(pipelineTaskActionPropertyPanel);
             center.setActiveTab(pipelineTaskActionPropertyPanel);
         }
         center.setActiveTab(pipelineTaskActionPropertyPanel);
     }

})