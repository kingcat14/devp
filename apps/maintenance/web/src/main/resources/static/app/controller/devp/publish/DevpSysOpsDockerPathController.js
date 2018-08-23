Ext.define('AM.controller.devp.publish.DevpSysOpsDockerPathController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsDockerPathPanel = center.child('devp\\.publish\\.DevpSysOpsDockerPathPanel');
        if(!devpSysOpsDockerPathPanel){
            devpSysOpsDockerPathPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerPathPanel', {closable:true});

            center.add(devpSysOpsDockerPathPanel);
            center.setActiveTab(devpSysOpsDockerPathPanel);
        }
        center.setActiveTab(devpSysOpsDockerPathPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsDockerPathPanel = center.child('devp\\.publish\\.DevpSysOpsDockerPathMainPanel');
         if(!devpSysOpsDockerPathPanel){
             devpSysOpsDockerPathPanel = Ext.create('AM.view.devp.publish.DevpSysOpsDockerPathMainPanel',{closable:true});

             center.add(devpSysOpsDockerPathPanel);
             center.setActiveTab(devpSysOpsDockerPathPanel);
         }
         center.setActiveTab(devpSysOpsDockerPathPanel);
     }

})