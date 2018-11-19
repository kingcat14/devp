Ext.define('AM.controller.console.jointjs.JointDataController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var jointDataPanel = center.child('console\\.jointjs\\.JointDataPanel');
        if(!jointDataPanel){
            jointDataPanel = Ext.create('AM.view.console.jointjs.JointDataPanel', {closable:true});

            center.add(jointDataPanel);
            center.setActiveTab(jointDataPanel);
        }
        center.setActiveTab(jointDataPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var jointDataPanel = center.child('console\\.jointjs\\.JointDataMainPanel');
         if(!jointDataPanel){
             jointDataPanel = Ext.create('AM.view.console.jointjs.JointDataMainPanel',{closable:true});

             center.add(jointDataPanel);
             center.setActiveTab(jointDataPanel);
         }
         center.setActiveTab(jointDataPanel);
     }

})