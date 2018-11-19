Ext.define('AM.controller.speedcloud.deployscheme.ResourceInstanceRelationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourceInstanceRelationPanel = center.child('speedcloud\\.deployscheme\\.ResourceInstanceRelationPanel');
        if(!resourceInstanceRelationPanel){
            resourceInstanceRelationPanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceInstanceRelationPanel', {closable:true});

            center.add(resourceInstanceRelationPanel);
            center.setActiveTab(resourceInstanceRelationPanel);
        }
        center.setActiveTab(resourceInstanceRelationPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourceInstanceRelationPanel = center.child('speedcloud\\.deployscheme\\.ResourceInstanceRelationMainPanel');
         if(!resourceInstanceRelationPanel){
             resourceInstanceRelationPanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceInstanceRelationMainPanel',{closable:true});

             center.add(resourceInstanceRelationPanel);
             center.setActiveTab(resourceInstanceRelationPanel);
         }
         center.setActiveTab(resourceInstanceRelationPanel);
     }

})