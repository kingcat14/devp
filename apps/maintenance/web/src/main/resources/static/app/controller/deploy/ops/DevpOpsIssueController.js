Ext.define('AM.controller.deploy.ops.DevpOpsIssueController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpOpsIssuePanel = center.child('deploy\\.ops\\.DevpOpsIssuePanel');
        if(!devpOpsIssuePanel){
            devpOpsIssuePanel = Ext.create('AM.view.deploy.ops.DevpOpsIssuePanel', {closable:true});

            var devpOpsIssueStore = Ext.create('AM.store.deploy.ops.DevpOpsIssueStore');
            devpOpsIssueStore.proxy.extraParams={searchCondition:{}};
            devpOpsIssuePanel.setStore(devpOpsIssueStore);
            devpOpsIssueStore.load();

            center.add(devpOpsIssuePanel);
            center.setActiveTab(devpOpsIssuePanel);
        }
        center.setActiveTab(devpOpsIssuePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpOpsIssuePanel = center.child('deploy\\.ops\\.DevpOpsIssueMainPanel');
         if(!devpOpsIssuePanel){
             devpOpsIssuePanel = Ext.create('AM.view.deploy.ops.DevpOpsIssueMainPanel',{closable:true});

             center.add(devpOpsIssuePanel);
             center.setActiveTab(devpOpsIssuePanel);
         }
         center.setActiveTab(devpOpsIssuePanel);
     }

})