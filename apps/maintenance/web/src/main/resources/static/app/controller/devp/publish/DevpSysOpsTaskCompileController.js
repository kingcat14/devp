Ext.define('AM.controller.devp.publish.DevpSysOpsTaskCompileController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsTaskCompilePanel = center.child('devp\\.publish\\.DevpSysOpsTaskCompilePanel');
        if(!devpSysOpsTaskCompilePanel){
            devpSysOpsTaskCompilePanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskCompilePanel', {closable:true});

            center.add(devpSysOpsTaskCompilePanel);
            center.setActiveTab(devpSysOpsTaskCompilePanel);
        }
        center.setActiveTab(devpSysOpsTaskCompilePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsTaskCompilePanel = center.child('devp\\.publish\\.DevpSysOpsTaskCompileMainPanel');
         if(!devpSysOpsTaskCompilePanel){
             devpSysOpsTaskCompilePanel = Ext.create('AM.view.devp.publish.DevpSysOpsTaskCompileMainPanel',{closable:true});

             center.add(devpSysOpsTaskCompilePanel);
             center.setActiveTab(devpSysOpsTaskCompilePanel);
         }
         center.setActiveTab(devpSysOpsTaskCompilePanel);
     }

})