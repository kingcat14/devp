Ext.define('AM.controller.devp.ops.DevpOpsRequirementController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpOpsRequirementPanel = center.child('deploy\\.ops\\.DevpOpsRequirementPanel');
        if(!devpOpsRequirementPanel){
            devpOpsRequirementPanel = Ext.create('AM.view.devp.ops.DevpOpsRequirementPanel', {closable:true});

            var devpOpsRequirementStore = Ext.create('AM.store.devp.ops.DevpOpsRequirementStore');
            devpOpsRequirementStore.proxy.extraParams={searchCondition:{}};
            devpOpsRequirementPanel.setStore(devpOpsRequirementStore);
            devpOpsRequirementStore.load();

            center.add(devpOpsRequirementPanel);
            center.setActiveTab(devpOpsRequirementPanel);
        }
        center.setActiveTab(devpOpsRequirementPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpOpsRequirementPanel = center.child('deploy\\.ops\\.DevpOpsRequirementMainPanel');
         if(!devpOpsRequirementPanel){
             devpOpsRequirementPanel = Ext.create('AM.view.devp.ops.DevpOpsRequirementMainPanel',{closable:true});

             center.add(devpOpsRequirementPanel);
             center.setActiveTab(devpOpsRequirementPanel);
         }
         center.setActiveTab(devpOpsRequirementPanel);
     }

})