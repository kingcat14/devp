Ext.define('AM.controller.speedcloud.deploy.DevpSysDpyResourceRefController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourceRefPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourceRefPanel');
        if(!devpSysDpyResourceRefPanel){
            devpSysDpyResourceRefPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourceRefPanel', {closable:true});

            center.add(devpSysDpyResourceRefPanel);
            center.setActiveTab(devpSysDpyResourceRefPanel);
        }
        center.setActiveTab(devpSysDpyResourceRefPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourceRefPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourceRefMainPanel');
         if(!devpSysDpyResourceRefPanel){
             devpSysDpyResourceRefPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourceRefMainPanel',{closable:true});

             center.add(devpSysDpyResourceRefPanel);
             center.setActiveTab(devpSysDpyResourceRefPanel);
         }
         center.setActiveTab(devpSysDpyResourceRefPanel);
     }

})