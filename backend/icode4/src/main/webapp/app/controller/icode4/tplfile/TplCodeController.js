Ext.define('AM.controller.icode4.tplfile.TplCodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var tplCodePanel = center.child('icode4\\.tplfile\\.TplCodePanel');
        if(!tplCodePanel){
            tplCodePanel = Ext.create('AM.view.icode4.tplfile.TplCodePanel', {closable:true});

            var tplCodeStore = Ext.create('AM.store.icode4.tplfile.TplCodeStore');
            tplCodeStore.proxy.extraParams={searchCondition:{}};
            tplCodePanel.setStore(tplCodeStore);
            tplCodeStore.load();

            center.add(tplCodePanel);
            center.setActiveTab(tplCodePanel);
        }
        center.setActiveTab(tplCodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var tplCodePanel = center.child('icode4\\.tplfile\\.TplCodeMainPanel');
         if(!tplCodePanel){
             tplCodePanel = Ext.create('AM.view.icode4.tplfile.TplCodeMainPanel',{closable:true});

             center.add(tplCodePanel);
             center.setActiveTab(tplCodePanel);
         }
         center.setActiveTab(tplCodePanel);
     }

})