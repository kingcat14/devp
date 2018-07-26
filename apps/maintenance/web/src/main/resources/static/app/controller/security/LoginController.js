Ext.define('AM.controller.security.LoginController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.login',

	loginText: 'Logging in...',

	onSpecialKey: function(field, e) {
		if (e.getKey() === e.ENTER) {
			this.doLogin();
		}
	},

	onLoginClick: function() {

		var form = this.lookupReference('form');

		if (form.isValid()) {
			Ext.getBody().mask(this.loginText);


			this.login({
				data: form.getValues(),
				scope: this,
				success: 'onLoginSuccess',
				failure: 'onLoginFailure'
			});
		}
	},

	onLoginFailure: function() {
		// Do something
		Ext.getBody().unmask();
		Ext.MessageBox.alert('登录失败', '请重新输入.');
	},

	onLoginSuccess: function() {

		Ext.getBody().unmask();

		//window.location="index.html";
	}
	,login: function(options) {
		Ext.Ajax.request({
			url: 'security/login/authenticate'
			,method: 'POST'
			,jsonData: options.data
			,scope: this
			,callback: this.onLoginReturn
			,original: options
		});
	},

	onLoginReturn: function(options, success, response) {
		options = options.original;
		var session = this.getSession(),
			resultSet;

		if (success) {
			resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.security.LoginResult').getProxy().getReader().read(response);

			if (resultSet.getSuccess()) {
				Ext.callback(options.success, options.scope);
				return;
			}
		}

		Ext.callback(options.failure, options.scope);
	}


});