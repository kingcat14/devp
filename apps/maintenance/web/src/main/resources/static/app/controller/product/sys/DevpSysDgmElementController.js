Ext.define('AM.controller.product.sys.DevpSysDgmElementController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var devpSysDgmElementPanel = center.child('product\\.sys\\.DevpSysDgmElementPanel');
        if(!devpSysDgmElementPanel){
            devpSysDgmElementPanel = Ext.create('AM.view.product.sys.DevpSysDgmElementPanel', {closable:true});

            var devpSysDgmElementStore = Ext.create('AM.store.product.sys.DevpSysDgmElementStore');
            devpSysDgmElementStore.proxy.extraParams={searchCondition:{}};
            devpSysDgmElementPanel.setStore(devpSysDgmElementStore);
            devpSysDgmElementStore.load();

            center.add(devpSysDgmElementPanel);
            center.setActiveTab(devpSysDgmElementPanel);
        }
        center.setActiveTab(devpSysDgmElementPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var devpSysDgmElementPanel = center.child('product\\.sys\\.DevpSysDgmElementMainPanel');
         if(!devpSysDgmElementPanel){
             devpSysDgmElementPanel = Ext.create('AM.view.product.sys.DevpSysDgmElementMainPanel',{closable:true});

             center.add(devpSysDgmElementPanel);
             center.setActiveTab(devpSysDgmElementPanel);
         }
         center.setActiveTab(devpSysDgmElementPanel);
     }

})