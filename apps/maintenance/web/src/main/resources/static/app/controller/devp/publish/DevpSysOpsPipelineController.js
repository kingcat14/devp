Ext.define('AM.controller.devp.publish.DevpSysOpsPipelineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsPipelinePanel = center.child('devp\\.publish\\.DevpSysOpsPipelinePanel');
        if(!devpSysOpsPipelinePanel){
            devpSysOpsPipelinePanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipelinePanel', {closable:true});

            center.add(devpSysOpsPipelinePanel);
            center.setActiveTab(devpSysOpsPipelinePanel);
        }
        center.setActiveTab(devpSysOpsPipelinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsPipelinePanel = center.child('devp\\.publish\\.DevpSysOpsPipelineMainPanel');
         if(!devpSysOpsPipelinePanel){
             devpSysOpsPipelinePanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipelineMainPanel',{closable:true});

             center.add(devpSysOpsPipelinePanel);
             center.setActiveTab(devpSysOpsPipelinePanel);
         }
         center.setActiveTab(devpSysOpsPipelinePanel);
     }

})