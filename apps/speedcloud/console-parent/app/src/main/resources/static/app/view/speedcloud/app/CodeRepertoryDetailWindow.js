Ext.define('AM.view.speedcloud.app.CodeRepertoryDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeRepertoryDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '代码库详细信息'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,layout: {
                        type: 'anchor'
                    }
                    ,defaults: {
                        labelAlign: 'right'
                        ,xtype: 'displayfield'
                        ,padding: '5 0 0 5'
                        ,anchor: '100%'
                        ,labelWidth:150
                    }
                    ,items: [
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'urlField'
                            ,padding: '5 0 0 5'
                            ,name: 'url'
                            ,fieldLabel: 'url'
                        }
                        ,{
                            itemId: 'developTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'developType'
                            ,fieldLabel: '开发模式'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('developTypeVO').name;
                            }
                        }
                        ,{
                            itemId: 'usernameField'
                            ,padding: '5 0 0 5'
                            ,name: 'username'
                            ,fieldLabel: '用户名'
                        }
                        ,{
                            itemId: 'passwordField'
                            ,padding: '5 0 0 5'
                            ,name: 'password'
                            ,fieldLabel: '密码'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});