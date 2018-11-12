Ext.define('AM.view.application.framework.HeaderContainerController', {
        extend: 'Ext.app.ViewController',
        requires: [
            // 'AM.model.main.Resource'
        ]
        , alias: 'controller.HeaderContainerController'
        , loadAppInfo: function () {
            var me = this;
            this.getView().mask({msg: "Please wait..."});
            Ext.Ajax.request({
                //获取当前应用
                url: 'current/app'
                , method: 'POST'
                , scope: this
                , success: function (response, opts) {
                    this.getView().unmask();
                    console.log(response.responseText);
                    var app = Ext.decode(response.responseText)
                    me.getView().down('mainHeadercontainer').setTitle(app.name);

                }
            });
        }
        , loadUserInfo: function (component) {
            console.log("header load user info")
            Ext.Ajax.request({
                //获取当前用户
                url: '/current/account'
                , method: 'POST'
                , scope: this
                , success: function(response){
                    console.log(response.responseText);

                    var accountConfig = Ext.decode(response.responseText)

                    var account = Ext.create('AM.model.application.security.Account', accountConfig);
                    component.setHtml('您好,'+account.get('name'))
                }
            });
        }
    }
)