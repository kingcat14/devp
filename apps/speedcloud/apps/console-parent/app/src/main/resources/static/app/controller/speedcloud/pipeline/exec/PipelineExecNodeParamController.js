Ext.define('AM.controller.speedcloud.pipeline.exec.PipelineExecNodeParamController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineExecNodeParamPanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecNodeParamPanel');
        if(!pipelineExecNodeParamPanel){
            pipelineExecNodeParamPanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecNodeParamPanel', {closable:true});

            center.add(pipelineExecNodeParamPanel);
            center.setActiveTab(pipelineExecNodeParamPanel);
        }
        center.setActiveTab(pipelineExecNodeParamPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineExecNodeParamPanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecNodeParamMainPanel');
         if(!pipelineExecNodeParamPanel){
             pipelineExecNodeParamPanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecNodeParamMainPanel',{closable:true});

             center.add(pipelineExecNodeParamPanel);
             center.setActiveTab(pipelineExecNodeParamPanel);
         }
         center.setActiveTab(pipelineExecNodeParamPanel);
     }

})