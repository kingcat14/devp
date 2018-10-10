Ext.define('AM.controller.speedcloud.deploy.DevpSysDpyResourcesCategoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourcesCategoryPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesCategoryPanel');
        if(!devpSysDpyResourcesCategoryPanel){
            devpSysDpyResourcesCategoryPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesCategoryPanel', {closable:true});

            center.add(devpSysDpyResourcesCategoryPanel);
            center.setActiveTab(devpSysDpyResourcesCategoryPanel);
        }
        center.setActiveTab(devpSysDpyResourcesCategoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourcesCategoryPanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourcesCategoryMainPanel');
         if(!devpSysDpyResourcesCategoryPanel){
             devpSysDpyResourcesCategoryPanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourcesCategoryMainPanel',{closable:true});

             center.add(devpSysDpyResourcesCategoryPanel);
             center.setActiveTab(devpSysDpyResourcesCategoryPanel);
         }
         center.setActiveTab(devpSysDpyResourcesCategoryPanel);
     }

})