Ext.define('AM.controller.speedcloud.deploy.DevpSysDpyResourcesTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourcesTypePanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesTypePanel');
        if(!devpSysDpyResourcesTypePanel){
            devpSysDpyResourcesTypePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesTypePanel', {closable:true});

            center.add(devpSysDpyResourcesTypePanel);
            center.setActiveTab(devpSysDpyResourcesTypePanel);
        }
        center.setActiveTab(devpSysDpyResourcesTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourcesTypePanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesTypeMainPanel');
         if(!devpSysDpyResourcesTypePanel){
             devpSysDpyResourcesTypePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesTypeMainPanel',{closable:true});

             center.add(devpSysDpyResourcesTypePanel);
             center.setActiveTab(devpSysDpyResourcesTypePanel);
         }
         center.setActiveTab(devpSysDpyResourcesTypePanel);
     }

})