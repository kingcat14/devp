Ext.define('AM.controller.speedcloud.deployscheme.ResourceRelationTypeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourceRelationTypePanel = center.child('speedcloud\\.deployscheme\\.ResourceRelationTypePanel');
        if(!resourceRelationTypePanel){
            resourceRelationTypePanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceRelationTypePanel', {closable:true});

            center.add(resourceRelationTypePanel);
            center.setActiveTab(resourceRelationTypePanel);
        }
        center.setActiveTab(resourceRelationTypePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourceRelationTypePanel = center.child('speedcloud\\.deployscheme\\.ResourceRelationTypeMainPanel');
         if(!resourceRelationTypePanel){
             resourceRelationTypePanel = Ext.create('AM.view.speedcloud.deployScheme.ResourceRelationTypeMainPanel',{closable:true});

             center.add(resourceRelationTypePanel);
             center.setActiveTab(resourceRelationTypePanel);
         }
         center.setActiveTab(resourceRelationTypePanel);
     }

})