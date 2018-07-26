Ext.define('AM.controller.deploy.deploy.DevpSysDpySchemeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDpySchemePanel = center.child('deploy\\.deploy\\.DevpSysDpySchemePanel');
        if(!devpSysDpySchemePanel){
            devpSysDpySchemePanel = Ext.create('AM.view.deploy.deploy.DevpSysDpySchemePanel', {closable:true});

            var devpSysDpySchemeStore = Ext.create('AM.store.deploy.deploy.DevpSysDpySchemeStore');
            devpSysDpySchemeStore.proxy.extraParams={searchCondition:{}};
            devpSysDpySchemePanel.setStore(devpSysDpySchemeStore);
            devpSysDpySchemeStore.load();

            center.add(devpSysDpySchemePanel);
            center.setActiveTab(devpSysDpySchemePanel);
        }
        center.setActiveTab(devpSysDpySchemePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDpySchemePanel = center.child('deploy\\.deploy\\.DevpSysDpySchemeMainPanel');
         if(!devpSysDpySchemePanel){
             devpSysDpySchemePanel = Ext.create('AM.view.deploy.deploy.DevpSysDpySchemeMainPanel',{closable:true});

             center.add(devpSysDpySchemePanel);
             center.setActiveTab(devpSysDpySchemePanel);
         }
         center.setActiveTab(devpSysDpySchemePanel);
     }

})