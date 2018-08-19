Ext.application({
	name: 'AM'

	// automatically create an instance of AM.view.Viewport
	,autoCreateViewport: true
	,mainView: 'AM.view.Viewport'
	// ,views:['Viewport']
	,controllers: [
		'main.MainController'
	]
	,launch: function() {
		//Ext.create('AM.view.Viewport');
	}
});
