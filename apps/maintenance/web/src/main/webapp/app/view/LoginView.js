Ext.define('AM.view.LoginView', {
	extend: 'Ext.container.Viewport',
	requires: ['AM.controller.security.LoginController','AM.model.security.LoginResult'],

	controller:'login',
	layout: 'center',

	//style : 'background-image:url(resources/images/login.png);background-repeat: no-repeat;filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=\'scale\')";-moz-background-size:100% 100%;background-size:100% 100%;',
	items: {
		xtype: 'form',
		reference: 'form',
		width: 450,
		title: '登录 - 客服中心',
		frame: true,
		width: 320,
		bodyPadding: 10,
		bodyStyle : {
			//'opacity': 0.2,
			//background: 'url(resources/images/login.png) no-repeat #00FFFF'
			//background: '#ffc'
		},
		defaultType: 'textfield',
		fieldDefaults: {labelAlign: 'right'},
		items: [{
			allowBlank: false,
			fieldLabel: '用户名',
			name: 'username',
			emptyText: '账号'
		}, {
			allowBlank: false,
			fieldLabel: '密码',
			name: 'password',
			emptyText: '密码',
			inputType: 'password'
		}],

		buttons: [{
			text: '登录',
			listeners: {
				click: 'onLoginClick'
			}
		}],

		defaults: {
			anchor: '100%',
			labelWidth: 120
		}
	}


});