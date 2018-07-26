Ext.define('AM.controller.deploy.deploy.DevpSysDpyResInstController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDpyResInstPanel = center.child('deploy\\.deploy\\.DevpSysDpyResInstPanel');
        if(!devpSysDpyResInstPanel){
            devpSysDpyResInstPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyResInstPanel', {closable:true});

            var devpSysDpyResInstStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyResInstStore');
            devpSysDpyResInstStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyResInstPanel.setStore(devpSysDpyResInstStore);
            devpSysDpyResInstStore.load();

            center.add(devpSysDpyResInstPanel);
            center.setActiveTab(devpSysDpyResInstPanel);
        }
        center.setActiveTab(devpSysDpyResInstPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyResInstPanel = center.child('deploy\\.deploy\\.DevpSysDpyResInstMainPanel');
         if(!devpSysDpyResInstPanel){
             devpSysDpyResInstPanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyResInstMainPanel',{closable:true});

             center.add(devpSysDpyResInstPanel);
             center.setActiveTab(devpSysDpyResInstPanel);
         }
         center.setActiveTab(devpSysDpyResInstPanel);
     }

})