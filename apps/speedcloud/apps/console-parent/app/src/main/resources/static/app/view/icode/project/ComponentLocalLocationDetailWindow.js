Ext.define('AM.view.icode.project.ComponentLocalLocationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.project.ComponentLocalLocationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '组件本地路径详细信息'
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
                            itemId: 'componentField'
                            ,padding: '5 0 0 5'
                            ,name: 'component'
                            ,fieldLabel: '组件'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('componentVO')?record.get('componentVO').code:'';
                            }
                        }
                        ,{
                            itemId: 'locationField'
                            ,padding: '5 0 0 5'
                            ,name: 'location'
                            ,fieldLabel: '本地路径'
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