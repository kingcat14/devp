Ext.define('AM.controller.speedcloud.config.PipelineTaskTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var pipelineTaskTypePanel = center.child('speedcloud\\.config\\.PipelineTaskTypePanel');
        if(!pipelineTaskTypePanel){
            pipelineTaskTypePanel = Ext.create('AM.view.speedcloud.config.PipelineTaskTypePanel', {closable:true});

            center.add(pipelineTaskTypePanel);
            center.setActiveTab(pipelineTaskTypePanel);
        }
        center.setActiveTab(pipelineTaskTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var pipelineTaskTypePanel = center.child('speedcloud\\.config\\.PipelineTaskTypeMainPanel');
         if(!pipelineTaskTypePanel){
             pipelineTaskTypePanel = Ext.create('AM.view.speedcloud.config.PipelineTaskTypeMainPanel',{closable:true});

             center.add(pipelineTaskTypePanel);
             center.setActiveTab(pipelineTaskTypePanel);
         }
         center.setActiveTab(pipelineTaskTypePanel);
     }

})