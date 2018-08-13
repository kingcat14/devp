Ext.define('AM.view.dashboard.UserDetailPanelController', {
	extend: 'Ext.app.ViewController'
	,alias: 'controller.dashboard-UserDetail'
	,loadUserInfo: function(){
		var me = this;
		this.getView().mask({msg:"Please wait..."});
		Ext.Ajax.request({
			//获取当前用户
			url: 'security/login/account'
			,method: 'POST'
			,scope: this
			,success: this.onSuccess
			,failure: this.onFailure
			// ,callback: this.onLoginReturn
			,exception: this.onAjaxproxyException
		});
	}
	, onFailure: function(response, opts) {
		this.getView().unmask();
		console.log( response);

		Ext.MessageBox.alert('获取用户信息失败（'+response.status+'）', '获取当前用户信息失败, 请重新登陆或联系研发同事。' );
	}
	, onSuccess: function(response, opts) {
		this.getView().unmask();
		console.log(response.responseText);

		var account = Ext.decode(response.responseText)

        var result = Ext.create('AM.model.application.security.Account', account);
		console.log(Ext.getClassName(result))
		this.getView().down('displayfield[name=nickName]').setValue(result.get('nickName'))
		this.getView().down('displayfield[name=name]').setValue(result.get('name'))
		this.getView().down('displayfield[name=accountName]').setValue(result.get('accountName'))
		this.getView().down('displayfield[name=mobile]').setValue(result.get('mobile'))
		this.getView().down('displayfield[name=email]').setValue(result.get('email'))


	}

})