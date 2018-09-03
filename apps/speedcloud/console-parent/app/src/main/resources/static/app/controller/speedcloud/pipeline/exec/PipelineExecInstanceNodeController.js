Ext.define('AM.controller.speedcloud.pipeline.exec.PipelineExecInstanceNodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineExecInstanceNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstanceNodePanel');
        if(!pipelineExecInstanceNodePanel){
            pipelineExecInstanceNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodePanel', {closable:true});

            center.add(pipelineExecInstanceNodePanel);
            center.setActiveTab(pipelineExecInstanceNodePanel);
        }
        center.setActiveTab(pipelineExecInstanceNodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineExecInstanceNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstanceNodeMainPanel');
         if(!pipelineExecInstanceNodePanel){
             pipelineExecInstanceNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceNodeMainPanel',{closable:true});

             center.add(pipelineExecInstanceNodePanel);
             center.setActiveTab(pipelineExecInstanceNodePanel);
         }
         center.setActiveTab(pipelineExecInstanceNodePanel);
     }

})