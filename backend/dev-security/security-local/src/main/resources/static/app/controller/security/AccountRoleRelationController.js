Ext.define('AM.controller.security.AccountRoleRelationController', {
    extend: 'Ext.app.Controller',

    views: [
        'security.AccountRoleRelationPanel'
    ],
	stores: [

    ],
    refs: [
        {
            ref: 'securityAccountRoleRelationPanel',
            selector: 'securityAccountRoleRelationPanel'
        }
    ],

    init: function(application) {
        var center = application.getController('main.MainController').getMainContentPanel();

        var accountRoleRelation = center.child('securityAccountRoleRelationPanel');
        if(!accountRoleRelation){
            accountRoleRelation = Ext.widget('securityAccountRoleRelationPanel',{closable:true});


            
            var store = Ext.create('AM.store.security.AccountRoleRelationStore');
            store.proxy.extraParams={searchCondition:{}};
            accountRoleRelation.setStore(store);
            store.load();

            center.add(accountRoleRelation);
            center.setActiveTab(accountRoleRelation);
        }
        center.setActiveTab(accountRoleRelation);
    }

});