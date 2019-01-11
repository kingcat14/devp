Ext.define('AM.view.speedcloud.pipeline.jenkins.JenkinsAdapterDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JenkinsAdapterDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: 'JenkinsAdapter详细信息'
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
                            itemId: 'projectField'
                            ,padding: '5 0 0 5'
                            ,name: 'project'
                            ,fieldLabel: '所属产品'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('projectVO')?record.get('projectVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'envField'
                            ,padding: '5 0 0 5'
                            ,name: 'env'
                            ,fieldLabel: '所属环境'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('envVO')?record.get('envVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'portField'
                            ,padding: '5 0 0 5'
                            ,name: 'port'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            itemId: 'hostField'
                            ,padding: '5 0 0 5'
                            ,name: 'host'
                            ,fieldLabel: 'IP'
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