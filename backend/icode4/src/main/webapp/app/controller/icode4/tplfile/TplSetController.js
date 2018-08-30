Ext.define('AM.controller.icode4.tplfile.TplSetController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var tplSetPanel = center.child('icode4\\.tplfile\\.TplSetPanel');
        if(!tplSetPanel){
            tplSetPanel = Ext.create('AM.view.icode4.tplfile.TplSetPanel', {closable:true});

            var tplSetStore = Ext.create('AM.store.icode4.tplfile.TplSetStore');
            tplSetStore.proxy.extraParams={searchCondition:{}};
            tplSetPanel.setStore(tplSetStore);
            tplSetStore.load();

            center.add(tplSetPanel);
            center.setActiveTab(tplSetPanel);
        }
        center.setActiveTab(tplSetPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var tplSetPanel = center.child('icode4\\.tplfile\\.TplSetMainPanel');
         if(!tplSetPanel){
             tplSetPanel = Ext.create('AM.view.icode4.tplfile.TplSetMainPanel',{closable:true});

             center.add(tplSetPanel);
             center.setActiveTab(tplSetPanel);
         }
         center.setActiveTab(tplSetPanel);
     }

})