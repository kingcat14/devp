Ext.define('AM.controller.icode4.systemboard.SystemController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
    }
    ,initPanel: function(application) {
		var center = application.getController('main.MainController').getMainContentPanel();

		var systemBoard = center.child('icode4\\.systemboard\\.SystemBoard');
		if(!systemBoard){
			console.log(1)
			systemBoard = Ext.create('AM.view.icode4.systemboard.SystemBoard', {closable:true});
			console.log(2)
			center.add(systemBoard);
			console.log(3)
			center.setActiveTab(systemBoard);
			console.log(4)
		}
		center.setActiveTab(systemBoard);

    }

})