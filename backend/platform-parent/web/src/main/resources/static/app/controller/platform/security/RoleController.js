Ext.define('AM.controller.platform.security.RoleController', {
    extend: 'Ext.app.Controller',

    views: [
        'platform.security.RolePanel'
    ],
	stores: [
         
    ],
    refs: [
        {
            ref: 'platformSecurityRolePanel',
            selector: 'platformSecurityRolePanel'
        }
    ],

    init: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var rolePanel = center.child('platformSecurityRolePanel');
        if(!rolePanel){
            rolePanel = Ext.widget('platformSecurityRolePanel', {closable:true});
            
            

            var store = Ext.create('AM.store.platform.security.RoleStore');
            store.proxy.extraParams={searchCondition:{}};
            rolePanel.setStore(store);
            store.load();
            
            center.add(rolePanel);
            center.setActiveTab(rolePanel);
        }
        center.setActiveTab(rolePanel);
    }

});