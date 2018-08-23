Ext.define('AM.controller.devp.publish.DevpSysOpsTaskPublishController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskPublishPanel = center.child('devp\\.publish\\.DevpSysOpsTaskPublishPanel');
        if(!devpSysOpsTaskPublishPanel){
            devpSysOpsTaskPublishPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskPublishPanel', {closable:true});

            center.add(devpSysOpsTaskPublishPanel);
            center.setActiveTab(devpSysOpsTaskPublishPanel);
        }
        center.setActiveTab(devpSysOpsTaskPublishPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskPublishPanel = center.child('devp\\.publish\\.DevpSysOpsTaskPublishMainPanel');
         if(!devpSysOpsTaskPublishPanel){
             devpSysOpsTaskPublishPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskPublishMainPanel',{closable:true});

             center.add(devpSysOpsTaskPublishPanel);
             center.setActiveTab(devpSysOpsTaskPublishPanel);
         }
         center.setActiveTab(devpSysOpsTaskPublishPanel);
     }

})