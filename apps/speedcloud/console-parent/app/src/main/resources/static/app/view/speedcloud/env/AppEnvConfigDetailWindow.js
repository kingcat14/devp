Ext.define('AM.view.speedcloud.env.AppEnvConfigDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.env.AppEnvConfigDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '应用环境详细信息'
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
                            ,fieldLabel: '环境名称'
                        }
                        ,{
                            itemId: 'levelField'
                            ,padding: '5 0 0 5'
                            ,name: 'level'
                            ,fieldLabel: '环境级别'
                        }
                        ,{
                            itemId: 'projectField'
                            ,padding: '5 0 0 5'
                            ,name: 'project'
                            ,fieldLabel: '所属项目（产品）'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('projectVO')?record.get('projectVO').name:'';
                            }
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