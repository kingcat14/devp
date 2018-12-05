Ext.define('AM.view.speedcloud.app.CodeBaseInfoDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeBaseInfoDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '代码基本信息详细信息'
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
                            itemId: 'codeRepositoryField'
                            ,padding: '5 0 0 5'
                            ,name: 'codeRepository'
                            ,fieldLabel: '代码库'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('codeRepositoryVO')?record.get('codeRepositoryVO').url:'';
                            }
                        }
                        ,{
                            itemId: 'languageField'
                            ,padding: '5 0 0 5'
                            ,name: 'language'
                            ,fieldLabel: '开发语言'
                        }
                        ,{
                            itemId: 'languageLevelField'
                            ,padding: '5 0 0 5'
                            ,name: 'languageLevel'
                            ,fieldLabel: '语言级别'
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