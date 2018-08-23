Ext.define('AM.controller.devp.publish.DevpSysOpsTaskConfigController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskConfigPanel = center.child('devp\\.publish\\.DevpSysOpsTaskConfigPanel');
        if(!devpSysOpsTaskConfigPanel){
            devpSysOpsTaskConfigPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskConfigPanel', {closable:true});

            center.add(devpSysOpsTaskConfigPanel);
            center.setActiveTab(devpSysOpsTaskConfigPanel);
        }
        center.setActiveTab(devpSysOpsTaskConfigPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskConfigPanel = center.child('devp\\.publish\\.DevpSysOpsTaskConfigMainPanel');
         if(!devpSysOpsTaskConfigPanel){
             devpSysOpsTaskConfigPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskConfigMainPanel',{closable:true});

             center.add(devpSysOpsTaskConfigPanel);
             center.setActiveTab(devpSysOpsTaskConfigPanel);
         }
         center.setActiveTab(devpSysOpsTaskConfigPanel);
     }

})