Ext.define('AM.controller.devp.publish.DevpSysOpsTaskHostController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskHostPanel = center.child('devp\\.publish\\.DevpSysOpsTaskHostPanel');
        if(!devpSysOpsTaskHostPanel){
            devpSysOpsTaskHostPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskHostPanel', {closable:true});

            center.add(devpSysOpsTaskHostPanel);
            center.setActiveTab(devpSysOpsTaskHostPanel);
        }
        center.setActiveTab(devpSysOpsTaskHostPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskHostPanel = center.child('devp\\.publish\\.DevpSysOpsTaskHostMainPanel');
         if(!devpSysOpsTaskHostPanel){
             devpSysOpsTaskHostPanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskHostMainPanel',{closable:true});

             center.add(devpSysOpsTaskHostPanel);
             center.setActiveTab(devpSysOpsTaskHostPanel);
         }
         center.setActiveTab(devpSysOpsTaskHostPanel);
     }

})