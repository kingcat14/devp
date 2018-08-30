
Ext.define('AM.controller.main.MainController', {

	extend: 'Ext.app.Controller',

	stores: [
		'AM.store.security.AllResourceTreeStore'
	],

	models: [

	],

	views: [

	],

	refs: [

		{
			ref: 'mainContentPanel',
			selector: 'mainContentPanel'
		}
	],

	init: function(application) {

		this.control({
			'mainFunctionTreePanel': {
				itemclick: this.onTreepanelItemClick
			},
			'mainContentPanel > #functionPanel > mainfunctionview': {
				itemclick: this.onFunctionPanelItemClick
			}
		});

		this.application = application;

		Ext.EventManager.on(document, 'keydown', function(e, t) {
			//e.stopEvent();

			if (e.getKey() == e.BACKSPACE && ((!/^input$/i.test(t.tagName) && !/^TEXTAREA$/i.test(t.tagName)) || t.disabled || t.readOnly)) {
				e.stopEvent();
			}
		});
	},
	onTreepanelItemClick: function(tablepanel, record, item, index, e, options) {
		//Ext.LoadMaskUtil.show(this.getMainContentPanel());
		var me = this;
		var myMask = new Ext.LoadMask(this.getMainContentPanel(), {msg:"Please wait..."});


		myMask.show();

		function loadController(){

			if("function"!==record.get('type')){
				myMask.hide();
				return;
			}

			try{
				var controllerName = record.get('url');

				var controller = me.application.getController(controllerName);

				controller.init(me.application);

			}catch(e){
				Ext.MessageBox.show({
					title: '加载页面错误',
					msg: '不能加载页面,请重试或联系管理员!',
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.ERROR
				});
				Ext.MsgUtil.show('ERROR',e);
				console.log(e);

			}
			//测试时，可以使用这里
			//controller.init(me.application);
			myMask.hide();

			if(tagId){
				clearInterval(tagId);
			}
		}

		var tagId = setInterval(loadController, 1000);
	},

	onFunctionPanelItemClick: function(tablepanel,record) {
		var controllerName = record.get('url');

		var controller = this.application.getController(controllerName);

		controller.init(this.application);
	},

	loadController: function(record) {

	}
	,editUser: function(grid, record) {
		var edit = Ext.create('AM.view.user.Edit').show();

		edit.down('form').loadRecord(record);
	},

	updateUser: function(button) {
		var win    = button.up('window'),
			form   = win.down('form'),
			record = form.getRecord(),
			values = form.getValues();

		record.set(values);
		win.close();

	}


});
