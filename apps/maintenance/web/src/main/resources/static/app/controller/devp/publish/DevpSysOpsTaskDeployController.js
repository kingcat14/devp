Ext.define('AM.controller.devp.publish.DevpSysOpsTaskDeployController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskDeployPanel = center.child('devp\\.publish\\.DevpSysOpsTaskDeployPanel');
        if(!devpSysOpsTaskDeployPanel){
            devpSysOpsTaskDeployPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskDeployPanel', {closable:true});

            center.add(devpSysOpsTaskDeployPanel);
            center.setActiveTab(devpSysOpsTaskDeployPanel);
        }
        center.setActiveTab(devpSysOpsTaskDeployPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskDeployPanel = center.child('devp\\.publish\\.DevpSysOpsTaskDeployMainPanel');
         if(!devpSysOpsTaskDeployPanel){
             devpSysOpsTaskDeployPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskDeployMainPanel',{closable:true});

             center.add(devpSysOpsTaskDeployPanel);
             center.setActiveTab(devpSysOpsTaskDeployPanel);
         }
         center.setActiveTab(devpSysOpsTaskDeployPanel);
     }

})