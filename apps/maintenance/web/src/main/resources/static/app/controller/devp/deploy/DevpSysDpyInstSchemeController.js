Ext.define('AM.controller.devp.deploy.DevpSysDpyInstSchemeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var devpSysDpyInstSchemePanel = center.child('deploy\\.deploy\\.DevpSysDpyInstSchemePanel');
        if(!devpSysDpyInstSchemePanel){
            devpSysDpyInstSchemePanel = Ext.create('AM.view.devp.deploy.DevpSysDpyInstSchemePanel', {closable:true});

            var devpSysDpyInstSchemeStore = Ext.create('AM.store.devp.deploy.DevpSysDpyInstSchemeStore');
            devpSysDpyInstSchemeStore.proxy.extraParams={searchCondition:{}};
            devpSysDpyInstSchemePanel.setStore(devpSysDpyInstSchemeStore);
            devpSysDpyInstSchemeStore.load();

            center.add(devpSysDpyInstSchemePanel);
            center.setActiveTab(devpSysDpyInstSchemePanel);
        }
        center.setActiveTab(devpSysDpyInstSchemePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('application.framework.MainController').getMainContentPanel();

         var devpSysDpyInstSchemePanel = center.child('deploy\\.deploy\\.DevpSysDpyInstSchemeMainPanel');
         if(!devpSysDpyInstSchemePanel){
             devpSysDpyInstSchemePanel = Ext.create('AM.view.devp.deploy.DevpSysDpyInstSchemeMainPanel',{closable:true});

             center.add(devpSysDpyInstSchemePanel);
             center.setActiveTab(devpSysDpyInstSchemePanel);
         }
         center.setActiveTab(devpSysDpyInstSchemePanel);
     }

})