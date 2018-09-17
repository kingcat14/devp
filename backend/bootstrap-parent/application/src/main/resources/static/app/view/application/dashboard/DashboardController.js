Ext.define('AM.view.application.dashboard.DashboardController', {
	extend: 'Ext.app.ViewController',
	requires: [
		// 'AM.model.main.Resource'
	]
	,alias: 'controller.application.dashboard.DashboardController'
    ,loadAppInfo: function(){
        var me = this;
        this.getView().mask({msg:"Please wait..."});
        Ext.Ajax.request({
            //获取当前用户
            url: 'current/lapp'
            ,method: 'POST'
            ,scope: this
            ,success: function(response, opts) {
				this.getView().unmask();
				console.log(response.responseText);

				var app = Ext.decode(response.responseText)

			}
        });
    }
})