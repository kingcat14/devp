Ext.define('AM.controller.application.common.AttachmentController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var attachmentPanel = center.child('application\\.common\\.AttachmentPanel');
        if(!attachmentPanel){
            attachmentPanel = Ext.create('AM.view.application.common.AttachmentPanel', {closable:true});

            var attachmentStore = Ext.create('AM.store.application.common.AttachmentStore');
            attachmentStore.proxy.extraParams={searchCondition:{}};
            attachmentPanel.setStore(attachmentStore);
            attachmentStore.load();

            center.add(attachmentPanel);
            center.setActiveTab(attachmentPanel);
        }
        center.setActiveTab(attachmentPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var attachmentPanel = center.child('application\\.common\\.AttachmentMainPanel');
         if(!attachmentPanel){
             attachmentPanel = Ext.create('AM.view.application.common.AttachmentMainPanel',{closable:true});

             center.add(attachmentPanel);
             center.setActiveTab(attachmentPanel);
         }
         center.setActiveTab(attachmentPanel);
     }

})