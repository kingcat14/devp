Ext.define('AM.view.platform.platform.application.AppDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'platform.platform.application.AppDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '应用详细信息'
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
                            ,fieldLabel: '应用名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '应用代码'
                        }
                        ,{
                            itemId: 'appCategoryIdField'
                            ,padding: '5 0 0 5'
                            ,name: 'appCategoryId'
                            ,fieldLabel: '应用类别'
                            ,renderer: function (value, field) {

                                var record = me.down('form').getForm().getRecord();

                                return record.get('appCategoryIdVO').name;
                            }
                        }
                        ,{
                            itemId: 'enableField'
                            ,padding: '5 0 0 5'
                            ,name: 'enable'
                            ,fieldLabel: '已启用'
                        }
                        ,{
                            itemId: 'onBoardTimeField'
                            ,padding: '5 0 0 5'
                            ,name: 'onBoardTime'
                            ,fieldLabel: '上架时间'
                            ,renderer: function (value, field) {
                                return Ext.Date.format(dt, 'Y-m-d H:i:s')
                            }
                        }
                        ,{
                            itemId: 'urlField'
                            ,padding: '5 0 0 5'
                            ,name: 'url'
                            ,fieldLabel: '应用链接'
                        }
                        ,{
                            itemId: 'visibleField'
                            ,padding: '5 0 0 5'
                            ,name: 'visible'
                            ,fieldLabel: '可见'
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'labelField'
                            ,padding: '5 0 0 5'
                            ,name: 'label'
                            ,fieldLabel: '标签'
                            ,labelAlign: 'top'
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
                            ,labelAlign: 'top'
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