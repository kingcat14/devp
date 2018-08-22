Ext.define('AM.controller.devp.ops.DevpOpsParasDefineController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpOpsParasDefinePanel = center.child('deploy\\.ops\\.DevpOpsParasDefinePanel');
        if(!devpOpsParasDefinePanel){
            devpOpsParasDefinePanel = Ext.create('AM.view.devp.ops.DevpOpsParasDefinePanel', {closable:true});

            var devpOpsParasDefineStore = Ext.create('AM.store.devp.ops.DevpOpsParasDefineStore');
            devpOpsParasDefineStore.proxy.extraParams={searchCondition:{}};
            devpOpsParasDefinePanel.setStore(devpOpsParasDefineStore);
            devpOpsParasDefineStore.load();

            center.add(devpOpsParasDefinePanel);
            center.setActiveTab(devpOpsParasDefinePanel);
        }
        center.setActiveTab(devpOpsParasDefinePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpOpsParasDefinePanel = center.child('deploy\\.ops\\.DevpOpsParasDefineMainPanel');
         if(!devpOpsParasDefinePanel){
             devpOpsParasDefinePanel = Ext.create('AM.view.devp.ops.DevpOpsParasDefineMainPanel',{closable:true});

             center.add(devpOpsParasDefinePanel);
             center.setActiveTab(devpOpsParasDefinePanel);
         }
         center.setActiveTab(devpOpsParasDefinePanel);
     }

})