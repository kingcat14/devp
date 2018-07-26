Ext.define('AM.controller.security.AccountController', {
    extend: 'Ext.app.Controller',

    views: [
        'security.AccountPanel'
    ],
	stores: [
         
    ],
    refs: [
        {
            ref: 'securityAccountPanel',
            selector: 'securityAccountPanel'
        }
    ],

    init: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var userPanel = center.child('securityAccountPanel');
        if(!userPanel){
            userPanel = Ext.widget('securityAccountPanel',{closable:true});
            
            
            var condition = Ext.JSON.encode({});
            var store = Ext.create('AM.store.security.AccountStore');
            store.proxy.extraParams={condition:condition};
            userPanel.setStore(store);
            store.load();
            
            center.add(userPanel);
            center.setActiveTab(userPanel);
        }
        center.setActiveTab(userPanel);
    }

});