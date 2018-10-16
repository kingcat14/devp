Ext.define('AM.controller.speedcloud.deployscheme.ResourceRelationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourceRelationPanel = center.child('speedcloud\\.deployscheme\\.ResourceRelationPanel');
        if(!resourceRelationPanel){
            resourceRelationPanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceRelationPanel', {closable:true});

            center.add(resourceRelationPanel);
            center.setActiveTab(resourceRelationPanel);
        }
        center.setActiveTab(resourceRelationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourceRelationPanel = center.child('speedcloud\\.deployscheme\\.ResourceRelationMainPanel');
         if(!resourceRelationPanel){
             resourceRelationPanel = Ext.create('AM.view.speedcloud.deployScheme.ResourceRelationMainPanel',{closable:true});

             center.add(resourceRelationPanel);
             center.setActiveTab(resourceRelationPanel);
         }
         center.setActiveTab(resourceRelationPanel);
     }

})