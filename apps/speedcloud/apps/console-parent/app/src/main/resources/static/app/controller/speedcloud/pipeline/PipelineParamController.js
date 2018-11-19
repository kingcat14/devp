Ext.define('AM.controller.speedcloud.pipeline.PipelineParamController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineParamPanel = center.child('speedcloud\\.pipeline\\.PipelineParamPanel');
        if(!pipelineParamPanel){
            pipelineParamPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineParamPanel', {closable:true});

            center.add(pipelineParamPanel);
            center.setActiveTab(pipelineParamPanel);
        }
        center.setActiveTab(pipelineParamPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineParamPanel = center.child('speedcloud\\.pipeline\\.PipelineParamMainPanel');
         if(!pipelineParamPanel){
             pipelineParamPanel = Ext.create('AM.view.speedcloud.pipeline.PipelineParamMainPanel',{closable:true});

             center.add(pipelineParamPanel);
             center.setActiveTab(pipelineParamPanel);
         }
         center.setActiveTab(pipelineParamPanel);
     }

})