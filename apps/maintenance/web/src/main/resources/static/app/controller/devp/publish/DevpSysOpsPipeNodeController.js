Ext.define('AM.controller.devp.publish.DevpSysOpsPipeNodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsPipeNodePanel = center.child('devp\\.publish\\.DevpSysOpsPipeNodePanel');
        if(!devpSysOpsPipeNodePanel){
            devpSysOpsPipeNodePanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipeNodePanel', {closable:true});

            center.add(devpSysOpsPipeNodePanel);
            center.setActiveTab(devpSysOpsPipeNodePanel);
        }
        center.setActiveTab(devpSysOpsPipeNodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsPipeNodePanel = center.child('devp\\.publish\\.DevpSysOpsPipeNodeMainPanel');
         if(!devpSysOpsPipeNodePanel){
             devpSysOpsPipeNodePanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipeNodeMainPanel',{closable:true});

             center.add(devpSysOpsPipeNodePanel);
             center.setActiveTab(devpSysOpsPipeNodePanel);
         }
         center.setActiveTab(devpSysOpsPipeNodePanel);
     }

})