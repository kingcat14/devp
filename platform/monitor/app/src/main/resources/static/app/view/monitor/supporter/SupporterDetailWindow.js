Ext.define('AM.view.monitor.supporter.SupporterDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.supporter.SupporterDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '运维人员详细信息'
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
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '姓名'
                        }
                        ,{
                            itemId: 'mobileField'
                            ,padding: '5 0 0 5'
                            ,name: 'mobile'
                            ,fieldLabel: '手机号码'
                        }
                        ,{
                            itemId: 'emailField'
                            ,padding: '5 0 0 5'
                            ,name: 'email'
                            ,fieldLabel: '邮箱'
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