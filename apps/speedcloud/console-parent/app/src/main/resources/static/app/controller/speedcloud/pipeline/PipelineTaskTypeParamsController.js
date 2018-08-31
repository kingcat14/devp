Ext.define('AM.controller.speedcloud.pipeline.PipelineTaskTypeParamsController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskTypeParamsPanel = center.child('speedcloud\\.pipeline\\.PipelineTaskTypeParamsPanel');
        if(!pipelineTaskTypeParamsPanel){
            pipelineTaskTypeParamsPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskTypeParamsPanel', {closable:true});

            center.add(pipelineTaskTypeParamsPanel);
            center.setActiveTab(pipelineTaskTypeParamsPanel);
        }
        center.setActiveTab(pipelineTaskTypeParamsPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskTypeParamsPanel = center.child('speedcloud\\.pipeline\\.PipelineTaskTypeParamsMainPanel');
         if(!pipelineTaskTypeParamsPanel){
             pipelineTaskTypeParamsPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskTypeParamsMainPanel',{closable:true});

             center.add(pipelineTaskTypeParamsPanel);
             center.setActiveTab(pipelineTaskTypeParamsPanel);
         }
         center.setActiveTab(pipelineTaskTypeParamsPanel);
     }

})