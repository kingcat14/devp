Ext.define('AM.controller.speedcloud.pipeline.PipelineStageNodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineStageNodePanel = center.child('speedcloud\\.pipeline\\.PipelineStageNodePanel');
        if(!pipelineStageNodePanel){
            pipelineStageNodePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStageNodePanel', {closable:true});

            center.add(pipelineStageNodePanel);
            center.setActiveTab(pipelineStageNodePanel);
        }
        center.setActiveTab(pipelineStageNodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineStageNodePanel = center.child('speedcloud\\.pipeline\\.PipelineStageNodeMainPanel');
         if(!pipelineStageNodePanel){
             pipelineStageNodePanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStageNodeMainPanel',{closable:true});

             center.add(pipelineStageNodePanel);
             center.setActiveTab(pipelineStageNodePanel);
         }
         center.setActiveTab(pipelineStageNodePanel);
     }

})