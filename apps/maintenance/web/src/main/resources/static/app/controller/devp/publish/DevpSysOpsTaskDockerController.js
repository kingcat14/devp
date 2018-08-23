Ext.define('AM.controller.devp.publish.DevpSysOpsTaskDockerController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskDockerPanel = center.child('devp\\.publish\\.DevpSysOpsTaskDockerPanel');
        if(!devpSysOpsTaskDockerPanel){
            devpSysOpsTaskDockerPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskDockerPanel', {closable:true});

            center.add(devpSysOpsTaskDockerPanel);
            center.setActiveTab(devpSysOpsTaskDockerPanel);
        }
        center.setActiveTab(devpSysOpsTaskDockerPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskDockerPanel = center.child('devp\\.publish\\.DevpSysOpsTaskDockerMainPanel');
         if(!devpSysOpsTaskDockerPanel){
             devpSysOpsTaskDockerPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskDockerMainPanel',{closable:true});

             center.add(devpSysOpsTaskDockerPanel);
             center.setActiveTab(devpSysOpsTaskDockerPanel);
         }
         center.setActiveTab(devpSysOpsTaskDockerPanel);
     }

})