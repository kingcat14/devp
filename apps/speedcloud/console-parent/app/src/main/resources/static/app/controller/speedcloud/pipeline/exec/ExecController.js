Ext.define('AM.controller.speedcloud.pipeline.exec.ExecController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var execPanel = center.child('speedcloud\\.pipeline\\.exec\\.ExecPanel');
        if(!execPanel){
            execPanel = Ext.create('AM.view.speedcloud.pipeline.exec.ExecPanel', {closable:true});

            center.add(execPanel);
            center.setActiveTab(execPanel);
        }
        center.setActiveTab(execPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var execPanel = center.child('speedcloud\\.pipeline\\.exec\\.ExecMainPanel');
         if(!execPanel){
             execPanel = Ext.create('AM.view.speedcloud.pipeline.exec.ExecMainPanel',{closable:true});

             center.add(execPanel);
             center.setActiveTab(execPanel);
         }
         center.setActiveTab(execPanel);
     }

})