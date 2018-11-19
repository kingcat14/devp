Ext.define('AM.controller.speedcloud.deployscheme.SchemeInstantiationController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
        //this.initMainPanel(application);
    }
    ,initPanel: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var schemePanel = center.child('speedcloud\\.deployscheme\\.SchemeInstantiationPanel');
        if(!schemePanel){
            schemePanel = Ext.create('AM.view.speedcloud.deployscheme.SchemeInstantiationPanel', {closable:true});

            center.add(schemePanel);
            center.setActiveTab(schemePanel);
        }
        center.setActiveTab(schemePanel);
    }

})