Ext.define('AM.controller.deploy.ops.DevpOpsAttachmentController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpOpsAttachmentPanel = center.child('deploy\\.ops\\.DevpOpsAttachmentPanel');
        if(!devpOpsAttachmentPanel){
            devpOpsAttachmentPanel = Ext.create('AM.view.deploy.ops.DevpOpsAttachmentPanel', {closable:true});

            // var devpOpsAttachmentStore = Ext.create('AM.store.deploy.ops.DevpOpsAttachmentStore');
            // devpOpsAttachmentStore.proxy.extraParams={searchCondition:{}};
            // devpOpsAttachmentPanel.setStore(devpOpsAttachmentStore);
            // devpOpsAttachmentStore.load();

            center.add(devpOpsAttachmentPanel);
            center.setActiveTab(devpOpsAttachmentPanel);
        }
        center.setActiveTab(devpOpsAttachmentPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpOpsAttachmentPanel = center.child('deploy\\.ops\\.DevpOpsAttachmentMainPanel');
         if(!devpOpsAttachmentPanel){
             devpOpsAttachmentPanel = Ext.create('AM.view.deploy.ops.DevpOpsAttachmentMainPanel',{closable:true});

             center.add(devpOpsAttachmentPanel);
             center.setActiveTab(devpOpsAttachmentPanel);
         }
         center.setActiveTab(devpOpsAttachmentPanel);
     }

})