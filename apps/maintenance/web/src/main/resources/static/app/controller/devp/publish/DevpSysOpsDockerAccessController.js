Ext.define('AM.controller.devp.publish.DevpSysOpsDockerAccessController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsDockerAccessPanel = center.child('devp\\.publish\\.DevpSysOpsDockerAccessPanel');
        if(!devpSysOpsDockerAccessPanel){
            devpSysOpsDockerAccessPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerAccessPanel', {closable:true});

            center.add(devpSysOpsDockerAccessPanel);
            center.setActiveTab(devpSysOpsDockerAccessPanel);
        }
        center.setActiveTab(devpSysOpsDockerAccessPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsDockerAccessPanel = center.child('devp\\.publish\\.DevpSysOpsDockerAccessMainPanel');
         if(!devpSysOpsDockerAccessPanel){
             devpSysOpsDockerAccessPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerAccessMainPanel',{closable:true});

             center.add(devpSysOpsDockerAccessPanel);
             center.setActiveTab(devpSysOpsDockerAccessPanel);
         }
         center.setActiveTab(devpSysOpsDockerAccessPanel);
     }

})