Ext.define('AM.controller.deploy.ops.DevpOpsCiGroupController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpOpsCiGroupPanel = center.child('deploy\\.ops\\.DevpOpsCiGroupPanel');
        if(!devpOpsCiGroupPanel){
            devpOpsCiGroupPanel = Ext.create('AM.view.deploy.ops.DevpOpsCiGroupPanel', {closable:true});

            var devpOpsCiGroupStore = Ext.create('AM.store.deploy.ops.DevpOpsCiGroupStore');
            devpOpsCiGroupStore.proxy.extraParams={searchCondition:{}};
            devpOpsCiGroupPanel.setStore(devpOpsCiGroupStore);
            devpOpsCiGroupStore.load();

            center.add(devpOpsCiGroupPanel);
            center.setActiveTab(devpOpsCiGroupPanel);
        }
        center.setActiveTab(devpOpsCiGroupPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpOpsCiGroupPanel = center.child('deploy\\.ops\\.DevpOpsCiGroupMainPanel');
         if(!devpOpsCiGroupPanel){
             devpOpsCiGroupPanel = Ext.create('AM.view.deploy.ops.DevpOpsCiGroupMainPanel',{closable:true});

             center.add(devpOpsCiGroupPanel);
             center.setActiveTab(devpOpsCiGroupPanel);
         }
         center.setActiveTab(devpOpsCiGroupPanel);
     }

})