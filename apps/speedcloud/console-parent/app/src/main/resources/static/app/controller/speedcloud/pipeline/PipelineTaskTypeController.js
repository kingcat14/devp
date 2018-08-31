Ext.define('AM.controller.speedcloud.pipeline.PipelineTaskTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskTypePanel = center.child('speedcloud\\.pipeline\\.PipelineTaskTypePanel');
        if(!pipelineTaskTypePanel){
            pipelineTaskTypePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskTypePanel', {closable:true});

            center.add(pipelineTaskTypePanel);
            center.setActiveTab(pipelineTaskTypePanel);
        }
        center.setActiveTab(pipelineTaskTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskTypePanel = center.child('speedcloud\\.pipeline\\.PipelineTaskTypeMainPanel');
         if(!pipelineTaskTypePanel){
             pipelineTaskTypePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskTypeMainPanel',{closable:true});

             center.add(pipelineTaskTypePanel);
             center.setActiveTab(pipelineTaskTypePanel);
         }
         center.setActiveTab(pipelineTaskTypePanel);
     }

})