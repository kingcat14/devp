Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskActionTypePropertyController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskActionTypePropertyPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionTypePropertyPanel');
        if(!pipelineTaskActionTypePropertyPanel){
            pipelineTaskActionTypePropertyPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePropertyPanel', {closable:true});

            center.add(pipelineTaskActionTypePropertyPanel);
            center.setActiveTab(pipelineTaskActionTypePropertyPanel);
        }
        center.setActiveTab(pipelineTaskActionTypePropertyPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskActionTypePropertyPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskActionTypePropertyMainPanel');
         if(!pipelineTaskActionTypePropertyPanel){
             pipelineTaskActionTypePropertyPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionTypePropertyMainPanel',{closable:true});

             center.add(pipelineTaskActionTypePropertyPanel);
             center.setActiveTab(pipelineTaskActionTypePropertyPanel);
         }
         center.setActiveTab(pipelineTaskActionTypePropertyPanel);
     }

})