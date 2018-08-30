Ext.define('AM.controller.icode4.microserviceboard.MicroServiceController', {
    extend: 'Ext.app.Controller'
	,requires:[
		'AM.view.icode4.microserviceboard.MicroServiceBoard'
	]
    ,init: function(application) {
        this.initPanel(application);
    }
    ,initPanel: function(application) {
		var center = application.getController('main.MainController').getMainContentPanel();

		var microServiceBoard = center.child('icode4\\.microserviceboard\\.MicroServiceBoard');
		if(!microServiceBoard){
			console.log(1)
			microServiceBoard = Ext.create('AM.view.icode4.microserviceboard.MicroServiceBoard', {closable:true});
			console.log(2)
			center.add(microServiceBoard);
			console.log(3)
			center.setActiveTab(microServiceBoard);
			console.log(4)
		}
		center.setActiveTab(microServiceBoard);

    }

})