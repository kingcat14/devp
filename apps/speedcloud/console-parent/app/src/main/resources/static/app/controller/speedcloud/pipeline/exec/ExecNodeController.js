Ext.define('AM.controller.speedcloud.pipeline.exec.ExecNodeController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var execNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.ExecNodePanel');
        if(!execNodePanel){
            execNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.ExecNodePanel', {closable:true});

            center.add(execNodePanel);
            center.setActiveTab(execNodePanel);
        }
        center.setActiveTab(execNodePanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var execNodePanel = center.child('speedcloud\\.pipeline\\.exec\\.ExecNodeMainPanel');
         if(!execNodePanel){
             execNodePanel = Ext.create('AM.view.speedcloud.pipeline.exec.ExecNodeMainPanel',{closable:true});

             center.add(execNodePanel);
             center.setActiveTab(execNodePanel);
         }
         center.setActiveTab(execNodePanel);
     }

})