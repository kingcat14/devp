Ext.define('AM.controller.devp.ops.DevpOpsAttachmentController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpOpsAttachmentPanel = center.child('deploy\\.ops\\.DevpOpsAttachmentPanel');
        if(!devpOpsAttachmentPanel){
            devpOpsAttachmentPanel = Ext.create('AM.view.devp.ops.DevpOpsAttachmentPanel', {closable:true});

            // var devpOpsAttachmentStore = Ext.create('AM.store.devp.ops.DevpOpsAttachmentStore');
            // devpOpsAttachmentStore.proxy.extraParams={searchCondition:{}};
            // devpOpsAttachmentPanel.setStore(devpOpsAttachmentStore);
            // devpOpsAttachmentStore.load();

            center.add(devpOpsAttachmentPanel);
            center.setActiveTab(devpOpsAttachmentPanel);
        }
        center.setActiveTab(devpOpsAttachmentPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpOpsAttachmentPanel = center.child('deploy\\.ops\\.DevpOpsAttachmentMainPanel');
         if(!devpOpsAttachmentPanel){
             devpOpsAttachmentPanel = Ext.create('AM.view.devp.ops.DevpOpsAttachmentMainPanel',{closable:true});

             center.add(devpOpsAttachmentPanel);
             center.setActiveTab(devpOpsAttachmentPanel);
         }
         center.setActiveTab(devpOpsAttachmentPanel);
     }

})