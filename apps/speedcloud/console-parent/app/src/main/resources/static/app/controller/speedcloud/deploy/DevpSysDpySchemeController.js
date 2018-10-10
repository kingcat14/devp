Ext.define('AM.controller.speedcloud.deploy.DevpSysDpySchemeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpySchemePanel = center.child('speedcloud\\.deploy\\.DevpSysDpySchemePanel');
        if(!devpSysDpySchemePanel){
            devpSysDpySchemePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpySchemePanel', {closable:true});

            center.add(devpSysDpySchemePanel);
            center.setActiveTab(devpSysDpySchemePanel);
        }
        center.setActiveTab(devpSysDpySchemePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpySchemePanel = center.child('speedcloud\\.deploy\\.DevpSysDpySchemeMainPanel');
         if(!devpSysDpySchemePanel){
             devpSysDpySchemePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpySchemeMainPanel',{closable:true});

             center.add(devpSysDpySchemePanel);
             center.setActiveTab(devpSysDpySchemePanel);
         }
         center.setActiveTab(devpSysDpySchemePanel);
     }

})