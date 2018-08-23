Ext.define('AM.controller.devp.publish.DevpSysOpsDockerParamController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsDockerParamPanel = center.child('devp\\.publish\\.DevpSysOpsDockerParamPanel');
        if(!devpSysOpsDockerParamPanel){
            devpSysOpsDockerParamPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerParamPanel', {closable:true});

            center.add(devpSysOpsDockerParamPanel);
            center.setActiveTab(devpSysOpsDockerParamPanel);
        }
        center.setActiveTab(devpSysOpsDockerParamPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsDockerParamPanel = center.child('devp\\.publish\\.DevpSysOpsDockerParamMainPanel');
         if(!devpSysOpsDockerParamPanel){
             devpSysOpsDockerParamPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerParamMainPanel',{closable:true});

             center.add(devpSysOpsDockerParamPanel);
             center.setActiveTab(devpSysOpsDockerParamPanel);
         }
         center.setActiveTab(devpSysOpsDockerParamPanel);
     }

})