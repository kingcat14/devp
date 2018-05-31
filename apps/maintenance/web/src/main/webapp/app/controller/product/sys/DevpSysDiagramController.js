Ext.define('AM.controller.product.sys.DevpSysDiagramController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDiagramPanel = center.child('product\\.sys\\.DevpSysDiagramPanel');
        if(!devpSysDiagramPanel){
            devpSysDiagramPanel = Ext.create('AM.view.product.sys.DevpSysDiagramPanel', {closable:true});

            var devpSysDiagramStore = Ext.create('AM.store.product.sys.DevpSysDiagramStore');
            devpSysDiagramStore.proxy.extraParams={searchCondition:{}};
            devpSysDiagramPanel.setStore(devpSysDiagramStore);
            devpSysDiagramStore.load();

            center.add(devpSysDiagramPanel);
            center.setActiveTab(devpSysDiagramPanel);
        }
        center.setActiveTab(devpSysDiagramPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDiagramPanel = center.child('product\\.sys\\.DevpSysDiagramMainPanel');
         if(!devpSysDiagramPanel){
             devpSysDiagramPanel = Ext.create('AM.view.product.sys.DevpSysDiagramMainPanel',{closable:true});

             center.add(devpSysDiagramPanel);
             center.setActiveTab(devpSysDiagramPanel);
         }
         center.setActiveTab(devpSysDiagramPanel);
     }

})