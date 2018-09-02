Ext.define('AM.controller.speedcloud.pipeline.PipelineStageTaskRelationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineStageTaskRelationPanel = center.child('speedcloud\\.pipeline\\.PipelineStageTaskRelationPanel');
        if(!pipelineStageTaskRelationPanel){
            pipelineStageTaskRelationPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStageTaskRelationPanel', {closable:true});

            center.add(pipelineStageTaskRelationPanel);
            center.setActiveTab(pipelineStageTaskRelationPanel);
        }
        center.setActiveTab(pipelineStageTaskRelationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineStageTaskRelationPanel = center.child('speedcloud\\.pipeline\\.PipelineStageTaskRelationMainPanel');
         if(!pipelineStageTaskRelationPanel){
             pipelineStageTaskRelationPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineStageTaskRelationMainPanel',{closable:true});

             center.add(pipelineStageTaskRelationPanel);
             center.setActiveTab(pipelineStageTaskRelationPanel);
         }
         center.setActiveTab(pipelineStageTaskRelationPanel);
     }

})