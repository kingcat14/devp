Ext.define('AM.controller.speedcloud.deploy.DevpSysDpyResourcesController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourcesPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesPanel');
        if(!devpSysDpyResourcesPanel){
            devpSysDpyResourcesPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesPanel', {closable:true});

            center.add(devpSysDpyResourcesPanel);
            center.setActiveTab(devpSysDpyResourcesPanel);
        }
        center.setActiveTab(devpSysDpyResourcesPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourcesPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesMainPanel');
         if(!devpSysDpyResourcesPanel){
             devpSysDpyResourcesPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesMainPanel',{closable:true});

             center.add(devpSysDpyResourcesPanel);
             center.setActiveTab(devpSysDpyResourcesPanel);
         }
         center.setActiveTab(devpSysDpyResourcesPanel);
     }

})