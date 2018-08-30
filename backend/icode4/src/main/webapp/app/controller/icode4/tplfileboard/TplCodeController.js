Ext.define('AM.controller.icode4.tplfileboard.TplCodeController', {
    extend: 'Ext.app.Controller'
	,requires:[
		'AM.view.icode4.tplfileboard.TplFileBoard'
	]
    ,init: function(application) {
        this.initPanel(application);
    }
    ,initPanel: function(application) {
		var center = application.getController('main.MainController').getMainContentPanel();

		var tplFileBoard = center.child('icode4\\.tplfileboard\\.TplFileBoard');
		if(!tplFileBoard){
			console.log(1)
			tplFileBoard = Ext.create('AM.view.icode4.tplfileboard.TplFileBoard', {closable:true});
			console.log(2)
			center.add(tplFileBoard);
			console.log(3)
			center.setActiveTab(tplFileBoard);
			console.log(4)
		}
		center.setActiveTab(tplFileBoard);

    }

})