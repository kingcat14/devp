Ext.define('AM.controller.platform.security.AccountRoleRelationController', {
    extend: 'Ext.app.Controller',

    views: [
        'platform.security.AccountRoleRelationPanel'
    ],
	stores: [

    ],
    refs: [
        {
            ref: 'platform.securityAccountRoleRelationPanel',
            selector: 'platformSecurityAccountRoleRelationPanel'
        }
    ],

    init: function(application) {
        var center = application.getController('application.framework.MainController').getMainContentPanel();

        var accountRoleRelation = center.child('platformSecurityAccountRoleRelationPanel');
        if(!accountRoleRelation){
            accountRoleRelation = Ext.widget('platformSecurityAccountRoleRelationPanel',{closable:true});

            var store = Ext.create('AM.store.platform.security.AccountRoleRelationStore');
            store.proxy.extraParams={searchCondition:{}};
            accountRoleRelation.setStore(store);
            store.load();

            center.add(accountRoleRelation);
            center.setActiveTab(accountRoleRelation);
        }
        center.setActiveTab(accountRoleRelation);
    }

});