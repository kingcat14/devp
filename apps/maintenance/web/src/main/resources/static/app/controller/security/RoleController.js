Ext.define('AM.controller.security.RoleController', {
    extend: 'Ext.app.Controller',

    views: [
        'security.RolePanel'
    ],
	stores: [
         
    ],
    refs: [
        {
            ref: 'securityRolePanel',
            selector: 'securityRolePanel'
        }
    ],

    init: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var rolePanel = center.child('securityRolePanel');
        if(!rolePanel){
            rolePanel = Ext.widget('securityRolePanel', {closable:true});
            
            

            var store = Ext.create('AM.store.security.RoleStore');
            store.proxy.extraParams={searchCondition:{}};
            rolePanel.setStore(store);
            store.load();
            
            center.add(rolePanel);
            center.setActiveTab(rolePanel);
        }
        center.setActiveTab(rolePanel);
    }

});