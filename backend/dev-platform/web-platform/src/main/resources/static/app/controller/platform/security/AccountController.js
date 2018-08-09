Ext.define('AM.controller.platform.security.AccountController', {
    extend: 'Ext.app.Controller',

    views: [
        'platform.security.AccountPanel'
    ],
	stores: [
         
    ],
    refs: [
        {
            ref: 'platformSecurityAccountPanel',
            selector: 'platformSecurityAccountPanel'
        }
    ],

    init: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var userPanel = center.child('platformSecurityAccountPanel');
        if(!userPanel){
            userPanel = Ext.widget('platformSecurityAccountPanel',{closable:true});
            
            
            var condition = Ext.JSON.encode({});
            var store = Ext.create('AM.store.platform.security.AccountStore');
            store.proxy.extraParams={condition:condition};
            userPanel.setStore(store);
            store.load();
            
            center.add(userPanel);
            center.setActiveTab(userPanel);
        }
        center.setActiveTab(userPanel);
    }

});