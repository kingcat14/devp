Ext.define('AM.view.LoginView', {
    extend: 'Ext.container.Viewport',
    requires: ['AM.controller.security.LoginController','AM.model.security.LoginResult'],

    controller:'login',
    layout: 'fit',

    //style : 'background-image:url(resources/images/login.png);background-repeat: no-repeat;filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=\'scale\')";-moz-background-size:100% 100%;background-size:100% 100%;',
    items:{
        xtype:'panel'
        ,bodyCls:'login-view'
        ,layout: 'center'
        , items :[
            {
                xtype : 'container'
                ,items:[
                    {
                        xtype : 'container'
                    }
                    ,{
                        xtype: 'form'
                        ,reference: 'form'
                        ,title: '登录'
                        ,titleAlign: 'center'
                        ,frame: false
                        ,width: 320
                        ,margin: '10 0 0 0'
                        //border: 1,
                        //style: {borderColor:'#000000', borderStyle:'solid', borderWidth:'1px'},
                        ,bodyStyle : {
                            //'opacity': 0.5,
                            //background: 'url(resources/images/login.png) no-repeat #00FFFF'
                            //background: '#ffc'
                        }
                        ,defaultType: 'textfield'
                        ,defaults: {
                            anchor: '100%'
                        }
                        ,fieldDefaults: {
                            width:300
                            ,padding:10
                            // labelAlign: 'left'
                            ,labelStyle: 'font-weight: bold;'
                            ,fieldStyle: 'font-weight: bold;'
                        }
                        ,items: [{
                            allowBlank: false,
                            //fieldLabel: '用户名',
                            // padding:10,
                            name: 'username',
                            emptyText: '请输入用户名'

                        }, {
                            allowBlank: false
                            // padding:10,
                            //fieldLabel: '密&nbsp;&nbsp;&nbsp;码',
                            ,name: 'password'
                            ,emptyText: '请输入用户密码'
                            ,inputType: 'password'
                        }]
                        ,fbar: [
                            ,'->',{ xtype: 'button', text: '登录' ,listeners: {
                                    click: 'onLoginClick'
                                }}

                            ,{ xtype: 'button', text: '忘记密码?' }
                        ]

                    }

                ]
            }

        ]

    }




});