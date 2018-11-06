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
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var userPanel = center.child('platformSecurityAccountPanel');
        if(!userPanel){
            userPanel = Ext.widget('platformSecurityAccountPanel',{closable:true});

            var store = Ext.create('AM.store.platform.security.AccountStore');
            store.proxy.extraParams={searchCondition:{}};
            userPanel.setStore(store);
            store.load();
            
            center.add(userPanel);
            center.setActiveTab(userPanel);
        }
        center.setActiveTab(userPanel);
    }

});