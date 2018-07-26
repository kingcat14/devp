Ext.define('AM.controller.deploy.deploy.DevpSysDpyInstSchemeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDpyInstSchemePanel = center.child('deploy\\.deploy\\.DevpSysDpyInstSchemePanel');
        if(!devpSysDpyInstSchemePanel){
            devpSysDpyInstSchemePanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyInstSchemePanel', {closable:true});

            var devpSysDpyInstSchemeStore = Ext.create('AM.store.deploy.deploy.DevpSysDpyInstSchemeStore');
            devpSysDpyInstSchemeStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyInstSchemePanel.setStore(devpSysDpyInstSchemeStore);
            devpSysDpyInstSchemeStore.load();

            center.add(devpSysDpyInstSchemePanel);
            center.setActiveTab(devpSysDpyInstSchemePanel);
        }
        center.setActiveTab(devpSysDpyInstSchemePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpyInstSchemePanel = center.child('deploy\\.deploy\\.DevpSysDpyInstSchemeMainPanel');
         if(!devpSysDpyInstSchemePanel){
             devpSysDpyInstSchemePanel = Ext.create('AM.view.deploy.deploy.DevpSysDpyInstSchemeMainPanel',{closable:true});

             center.add(devpSysDpyInstSchemePanel);
             center.setActiveTab(devpSysDpyInstSchemePanel);
         }
         center.setActiveTab(devpSysDpyInstSchemePanel);
     }

})