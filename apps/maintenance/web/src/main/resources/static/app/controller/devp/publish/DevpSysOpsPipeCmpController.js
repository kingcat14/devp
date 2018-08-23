Ext.define('AM.controller.devp.publish.DevpSysOpsPipeCmpController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsPipeCmpPanel = center.child('devp\\.publish\\.DevpSysOpsPipeCmpPanel');
        if(!devpSysOpsPipeCmpPanel){
            devpSysOpsPipeCmpPanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipeCmpPanel', {closable:true});

            center.add(devpSysOpsPipeCmpPanel);
            center.setActiveTab(devpSysOpsPipeCmpPanel);
        }
        center.setActiveTab(devpSysOpsPipeCmpPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsPipeCmpPanel = center.child('devp\\.publish\\.DevpSysOpsPipeCmpMainPanel');
         if(!devpSysOpsPipeCmpPanel){
             devpSysOpsPipeCmpPanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipeCmpMainPanel',{closable:true});

             center.add(devpSysOpsPipeCmpPanel);
             center.setActiveTab(devpSysOpsPipeCmpPanel);
         }
         center.setActiveTab(devpSysOpsPipeCmpPanel);
     }

})