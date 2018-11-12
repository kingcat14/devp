Ext.define('AM.view.monitor.app.UnknownAppController', {
	extend: 'Ext.app.ViewController'
	,requires: [

	]
	,alias: 'controller.monitor.app.UnknownAppController'
    ,onAddButtonClick: function() {

        var modelConfig = {}
        var record = Ext.create('AM.model.monitor.app.UnknownApp', modelConfig);

        this.showAddWindow(record);
    }

})