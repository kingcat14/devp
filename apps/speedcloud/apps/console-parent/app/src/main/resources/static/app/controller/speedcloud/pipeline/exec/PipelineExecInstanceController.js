Ext.define('AM.controller.speedcloud.pipeline.exec.PipelineExecInstanceController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineExecInstancePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstancePanel');
        if(!pipelineExecInstancePanel){
            pipelineExecInstancePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstancePanel', {closable:true});

            center.add(pipelineExecInstancePanel);
            center.setActiveTab(pipelineExecInstancePanel);
        }
        center.setActiveTab(pipelineExecInstancePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineExecInstancePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecInstanceMainPanel');
         if(!pipelineExecInstancePanel){
             pipelineExecInstancePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecInstanceMainPanel',{closable:true});

             center.add(pipelineExecInstancePanel);
             center.setActiveTab(pipelineExecInstancePanel);
         }
         center.setActiveTab(pipelineExecInstancePanel);
     }

})