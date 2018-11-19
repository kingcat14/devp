Ext.define('AM.controller.speedcloud.pipeline.exec.PipelineExecNodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineExecNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecNodePanel');
        if(!pipelineExecNodePanel){
            pipelineExecNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecNodePanel', {closable:true});

            center.add(pipelineExecNodePanel);
            center.setActiveTab(pipelineExecNodePanel);
        }
        center.setActiveTab(pipelineExecNodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineExecNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.PipelineExecNodeMainPanel');
         if(!pipelineExecNodePanel){
             pipelineExecNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecNodeMainPanel',{closable:true});

             center.add(pipelineExecNodePanel);
             center.setActiveTab(pipelineExecNodePanel);
         }
         center.setActiveTab(pipelineExecNodePanel);
     }

})