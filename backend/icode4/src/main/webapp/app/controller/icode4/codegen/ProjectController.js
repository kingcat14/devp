Ext.define('AM.controller.icode4.codegen.ProjectController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var projectPanel = center.child('icode4\\.codegen\\.ProjectPanel');
        if(!projectPanel){
            projectPanel = Ext.create('AM.view.icode4.codegen.ProjectPanel', {closable:true});

            var projectStore = Ext.create('AM.store.icode4.codegen.ProjectStore');
            projectStore.proxy.extraParams={searchCondition:{}};
            projectPanel.setStore(projectStore);
            projectStore.load();

            center.add(projectPanel);
            center.setActiveTab(projectPanel);
        }
        center.setActiveTab(projectPanel);
    }
    ,initMainPanel: function(application) {
         var center = application.getController('main.MainController').getMainContentPanel();

         var projectPanel = center.child('icode4\\.codegen\\.ProjectMainPanel');
         if(!projectPanel){
             projectPanel = Ext.create('AM.view.icode4.codegen.ProjectMainPanel',{closable:true});

             center.add(projectPanel);
             center.setActiveTab(projectPanel);
         }
         center.setActiveTab(projectPanel);
     }

})