Ext.define('AM.view.speedcloud.app.CodeRepertorySearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeRepertorySearchWindow'
    ,alias: 'widget.speedcloudappCodeRepertorySearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '代码库高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var codeRepertoryTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        codeRepertoryTypeStore.proxy.isSynchronous = true;
        codeRepertoryTypeStore.proxy.extraParams={searchCondition:{configType:'CODEREPERTORY-TYPE'}};
        codeRepertoryTypeStore.load();

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'combobox'
                            ,store: codeRepertoryTypeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'urlField'
                            ,fieldLabel: 'url'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.config.DevelopTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'developTypeField'
                            ,fieldLabel: '开发模式'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'usernameField'
                            ,fieldLabel: '用户名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'passwordField'
                            ,fieldLabel: '密码'
                        }

                            ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();

        me.fireEvent('saved');


    }
    ,getCondition: function(){

        var me = this;
        var typeField = me.down("#typeField");
        var urlField = me.down("#urlField");
        var developTypeField = me.down("#developTypeField");
        var developTypeMaxField = me.down("#developTypeMaxField");
        var developTypeMinField = me.down("#developTypeMinField");
        var usernameField = me.down("#usernameField");
        var passwordField = me.down("#passwordField");

        var condition = {
            type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,url:Ext.isEmpty(urlField.getValue())?null:urlField.getValue()
            ,developType:Ext.isEmpty(developTypeField.getValue())?null:developTypeField.getValue()
            ,username:Ext.isEmpty(usernameField.getValue())?null:usernameField.getValue()
            ,password:Ext.isEmpty(passwordField.getValue())?null:passwordField.getValue()
        };

        return condition;
    }

});