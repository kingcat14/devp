Ext.define('AM.controller.devp.publish.DevpSysOpsPipePlanController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysOpsPipePlanPanel = center.child('devp\\.publish\\.DevpSysOpsPipePlanPanel');
        if(!devpSysOpsPipePlanPanel){
            devpSysOpsPipePlanPanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipePlanPanel', {closable:true});

            center.add(devpSysOpsPipePlanPanel);
            center.setActiveTab(devpSysOpsPipePlanPanel);
        }
        center.setActiveTab(devpSysOpsPipePlanPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysOpsPipePlanPanel = center.child('devp\\.publish\\.DevpSysOpsPipePlanMainPanel');
         if(!devpSysOpsPipePlanPanel){
             devpSysOpsPipePlanPanel = Ext.create('AM.view.devp.publish.DevpSysOpsPipePlanMainPanel',{closable:true});

             center.add(devpSysOpsPipePlanPanel);
             center.setActiveTab(devpSysOpsPipePlanPanel);
         }
         center.setActiveTab(devpSysOpsPipePlanPanel);
     }

})