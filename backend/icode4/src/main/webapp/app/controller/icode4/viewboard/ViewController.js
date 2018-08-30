Ext.define('AM.controller.icode4.viewboard.ViewController', {
    extend: 'Ext.app.Controller'
    ,init: function(application) {
        this.initPanel(application);
    }
    ,initPanel: function(application) {
		var center = application.getController('main.MainController').getMainContentPanel();

		var viewBoard = center.child('icode4\\.viewboard\\.ViewBoard');
		if(!viewBoard){
			console.log(1)
			viewBoard = Ext.create('AM.view.icode4.viewboard.ViewBoard', {closable:true});
			console.log(2)
			center.add(viewBoard);
			console.log(3)
			center.setActiveTab(viewBoard);
			console.log(4)
		}
		center.setActiveTab(viewBoard);

    }

})