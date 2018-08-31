Ext.define('AM.controller.speedcloud.pipeline.PipelineTaskController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskPanel = center.child('speedcloud\\.pipeline\\.PipelineTaskPanel');
        if(!pipelineTaskPanel){
            pipelineTaskPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskPanel', {closable:true});

            center.add(pipelineTaskPanel);
            center.setActiveTab(pipelineTaskPanel);
        }
        center.setActiveTab(pipelineTaskPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskPanel = center.child('speedcloud\\.pipeline\\.PipelineTaskMainPanel');
         if(!pipelineTaskPanel){
             pipelineTaskPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineTaskMainPanel',{closable:true});

             center.add(pipelineTaskPanel);
             center.setActiveTab(pipelineTaskPanel);
         }
         center.setActiveTab(pipelineTaskPanel);
     }

})