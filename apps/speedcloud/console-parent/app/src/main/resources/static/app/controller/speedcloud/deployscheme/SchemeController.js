Ext.define('AM.controller.speedcloud.deployscheme.SchemeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var schemePanel = center.child('speedcloud\\.deployscheme\\.SchemePanel');
        if(!schemePanel){
            schemePanel = Ext.create('AM.view.speedcloud.deployscheme.SchemePanel', {closable:true});

            center.add(schemePanel);
            center.setActiveTab(schemePanel);
        }
        center.setActiveTab(schemePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var schemePanel = center.child('speedcloud\\.deployscheme\\.SchemeMainPanel');
         if(!schemePanel){
             schemePanel = Ext.create('AM.view.speedcloud.deployscheme.SchemeMainPanel',{closable:true});

             center.add(schemePanel);
             center.setActiveTab(schemePanel);
         }
         center.setActiveTab(schemePanel);
     }

})