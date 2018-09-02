Ext.define('AM.controller.speedcloud.pipeline.task.PipelineTaskParamController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskParamPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskParamPanel');
        if(!pipelineTaskParamPanel){
            pipelineTaskParamPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskParamPanel', {closable:true});

            center.add(pipelineTaskParamPanel);
            center.setActiveTab(pipelineTaskParamPanel);
        }
        center.setActiveTab(pipelineTaskParamPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskParamPanel = center.child('speedcloud\\.pipeline\\.task\\.PipelineTaskParamMainPanel');
         if(!pipelineTaskParamPanel){
             pipelineTaskParamPanel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskParamMainPanel',{closable:true});

             center.add(pipelineTaskParamPanel);
             center.setActiveTab(pipelineTaskParamPanel);
         }
         center.setActiveTab(pipelineTaskParamPanel);
     }

})