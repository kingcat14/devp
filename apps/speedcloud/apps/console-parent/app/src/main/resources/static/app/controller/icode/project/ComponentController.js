Ext.define('AM.controller.icode.project.ComponentController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var componentPanel = center.child('icode\\.project\\.ComponentPanel');
        if(!componentPanel){
            componentPanel = Ext.create('AM.view.icode.project.ComponentPanel', {closable:true});

            center.add(componentPanel);
            center.setActiveTab(componentPanel);
        }
        center.setActiveTab(componentPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var componentPanel = center.child('icode\\.project\\.ComponentMainPanel');
         if(!componentPanel){
             componentPanel = Ext.create('AM.view.icode.project.ComponentMainPanel',{closable:true});

             center.add(componentPanel);
             center.setActiveTab(componentPanel);
         }
         center.setActiveTab(componentPanel);
     }

})