
Ext.define('AM.controller.security.ResourceController', {
	extend: 'Ext.app.Controller',

	stores: [
		'security.AllResourceTreeStore'
	],
	views: [
		'security.ResourcePanel'
	],

	refs: [
		{
			ref: 'securityResourcePanel',
			selector: 'securityResourcePanel'
		}
	],

	init: function(application) {

		var center = application.getController('main.MainController').getMainContentPanel();

		var resourcePanel = center.child('securityResourcePanel');

		if(!resourcePanel){
			resourcePanel = Ext.widget('securityResourcePanel',{closable:true});
			center.add(resourcePanel);

			var resourceStore = Ext.create('AM.store.security.ResourceStore');
			//resourceStore.proxy.extraParams={condition:condition};
			resourcePanel.setResourceStore(resourceStore);


			center.setActiveTab(resourcePanel);
		}

		center.setActiveTab(resourcePanel);

		Ext.LoadMaskUtil.hide();
	}

});
