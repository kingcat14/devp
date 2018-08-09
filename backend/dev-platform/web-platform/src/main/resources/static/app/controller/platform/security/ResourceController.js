Ext.define('AM.controller.platform.security.ResourceController', {
	extend: 'Ext.app.Controller',

	stores: [
		'platform.security.AllResourceTreeStore'
	],
	views: [
		'platform.security.ResourcePanel'
	],

	refs: [
		{
			ref: 'platformSecurityResourcePanel',
			selector: 'platformSecurityResourcePanel'
		}
	],

	init: function(application) {

		var center = application.getController('main.MainController').getMainContentPanel();

		var resourcePanel = center.child('platformSecurityResourcePanel');

		if(!resourcePanel){
			resourcePanel = Ext.widget('platformSecurityResourcePanel',{closable:true});
			center.add(resourcePanel);

			var resourceStore = Ext.create('AM.store.platform.security.ResourceStore', {pageSize :1000});
			//resourceStore.proxy.extraParams={condition:condition};
			resourcePanel.setResourceStore(resourceStore);


			center.setActiveTab(resourcePanel);
		}

		center.setActiveTab(resourcePanel);

		Ext.LoadMaskUtil.hide();
	}

});
