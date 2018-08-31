Ext.define('AM.controller.speedcloud.pipeline.PipelineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelinePanel = center.child('speedcloud\\.pipeline\\.PipelinePanel');
        if(!pipelinePanel){
            pipelinePanel = Ext.create('AM.view.speedcloud.pipeline.PipelinePanel', {closable:true});

            center.add(pipelinePanel);
            center.setActiveTab(pipelinePanel);
        }
        center.setActiveTab(pipelinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelinePanel = center.child('speedcloud\\.pipeline\\.PipelineMainPanel');
         if(!pipelinePanel){
             pipelinePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineMainPanel',{closable:true});

             center.add(pipelinePanel);
             center.setActiveTab(pipelinePanel);
         }
         center.setActiveTab(pipelinePanel);
     }

})