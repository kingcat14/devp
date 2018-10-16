Ext.define('AM.controller.speedcloud.deployscheme.ResourceCategoryController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var resourceCategoryPanel = center.child('speedcloud\\.deployscheme\\.ResourceCategoryPanel');
        if(!resourceCategoryPanel){
            resourceCategoryPanel = Ext.create('AM.view.speedcloud.deployscheme.ResourceCategoryPanel', {closable:true});

            center.add(resourceCategoryPanel);
            center.setActiveTab(resourceCategoryPanel);
        }
        center.setActiveTab(resourceCategoryPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var resourceCategoryPanel = center.child('speedcloud\\.deployscheme\\.ResourceCategoryMainPanel');
         if(!resourceCategoryPanel){
             resourceCategoryPanel = Ext.create('AM.view.speedcloud.deployScheme.ResourceCategoryMainPanel',{closable:true});

             center.add(resourceCategoryPanel);
             center.setActiveTab(resourceCategoryPanel);
         }
         center.setActiveTab(resourceCategoryPanel);
     }

})