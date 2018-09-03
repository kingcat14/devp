Ext.define('AM.controller.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineExecInstanceNodeParamPanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstanceNodeParamPanel');
        if(!pipelineExecInstanceNodeParamPanel){
            pipelineExecInstanceNodeParamPanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamPanel', {closable:true});

            center.add(pipelineExecInstanceNodeParamPanel);
            center.setActiveTab(pipelineExecInstanceNodeParamPanel);
        }
        center.setActiveTab(pipelineExecInstanceNodeParamPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineExecInstanceNodeParamPanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstanceNodeParamMainPanel');
         if(!pipelineExecInstanceNodeParamPanel){
             pipelineExecInstanceNodeParamPanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeParamMainPanel',{closable:true});

             center.add(pipelineExecInstanceNodeParamPanel);
             center.setActiveTab(pipelineExecInstanceNodeParamPanel);
         }
         center.setActiveTab(pipelineExecInstanceNodeParamPanel);
     }

})