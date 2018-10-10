Ext.define('AM.controller.speedcloud.deploy.DevpSysDpyResourceRelationTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResourceRelationTypePanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourceRelationTypePanel');
        if(!devpSysDpyResourceRelationTypePanel){
            devpSysDpyResourceRelationTypePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourceRelationTypePanel', {closable:true});

            center.add(devpSysDpyResourceRelationTypePanel);
            center.setActiveTab(devpSysDpyResourceRelationTypePanel);
        }
        center.setActiveTab(devpSysDpyResourceRelationTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResourceRelationTypePanel = center.child('speedcloud\\.deploy\\.DevpSysDpyResourceRelationTypeMainPanel');
         if(!devpSysDpyResourceRelationTypePanel){
             devpSysDpyResourceRelationTypePanel = Ext.create('AM.view.speedcloud.deploy.DevpSysDpyResourceRelationTypeMainPanel',{closable:true});

             center.add(devpSysDpyResourceRelationTypePanel);
             center.setActiveTab(devpSysDpyResourceRelationTypePanel);
         }
         center.setActiveTab(devpSysDpyResourceRelationTypePanel);
     }

})