Ext.define('AM.controller.devp.deploy.DevpSysDpyResInstController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyResInstPanel = center.child('deploy\\.deploy\\.DevpSysDpyResInstPanel');
        if(!devpSysDpyResInstPanel){
            devpSysDpyResInstPanel = Ext.create('AM.view.devp.deploy.DevpSysDpyResInstPanel', {closable:true});

            var devpSysDpyResInstStore = Ext.create('AM.store.devp.deploy.DevpSysDpyResInstStore');
            devpSysDpyResInstStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyResInstPanel.setStore(devpSysDpyResInstStore);
            devpSysDpyResInstStore.load();

            center.add(devpSysDpyResInstPanel);
            center.setActiveTab(devpSysDpyResInstPanel);
        }
        center.setActiveTab(devpSysDpyResInstPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysDpyResInstPanel = center.child('deploy\\.deploy\\.DevpSysDpyResInstMainPanel');
         if(!devpSysDpyResInstPanel){
             devpSysDpyResInstPanel = Ext.create('AM.view.devp.deploy.DevpSysDpyResInstMainPanel',{closable:true});

             center.add(devpSysDpyResInstPanel);
             center.setActiveTab(devpSysDpyResInstPanel);
         }
         center.setActiveTab(devpSysDpyResInstPanel);
     }

})