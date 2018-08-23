Ext.define('AM.controller.devp.publish.DevpSysOpsTaskBaselineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskBaselinePanel = center.child('devp\\.publish\\.DevpSysOpsTaskBaselinePanel');
        if(!devpSysOpsTaskBaselinePanel){
            devpSysOpsTaskBaselinePanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskBaselinePanel', {closable:true});

            center.add(devpSysOpsTaskBaselinePanel);
            center.setActiveTab(devpSysOpsTaskBaselinePanel);
        }
        center.setActiveTab(devpSysOpsTaskBaselinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskBaselinePanel = center.child('devp\\.publish\\.DevpSysOpsTaskBaselineMainPanel');
         if(!devpSysOpsTaskBaselinePanel){
             devpSysOpsTaskBaselinePanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskBaselineMainPanel',{closable:true});

             center.add(devpSysOpsTaskBaselinePanel);
             center.setActiveTab(devpSysOpsTaskBaselinePanel);
         }
         center.setActiveTab(devpSysOpsTaskBaselinePanel);
     }

})