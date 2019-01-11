Ext.define('AM.view.application.application.AppDetailWindow', {
    extend: 'Ext.window.Window'
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
                    ,items: [
                        {
                            xtype: 'container'
                            ,layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults: {
                                        labelAlign: 'right'
                                        ,xtype: 'displayfield'
                                        ,padding: '5 0 0 5'
                                        ,blankText:'该字段为必填项'
                                        ,anchor: '100%'
                                        ,labelWidth:150
                                    }
                                    ,items: [
                                        {
                                            xtype: 'hiddenfield'
                                            ,anchor: '100%'
                                            ,itemId: 'idField'
                                            ,name: 'id'
                                            ,fieldLabel: 'Label'
                                        }

                                        ,{
                                            itemId: 'nameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'name'
                                            ,fieldLabel: '应用名称'
                                        }

                                        ,{
                                            itemId: 'tenantIdField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tenantId'
                                            ,fieldLabel: '所属租户'
                                        }

                                        ,{
                                            itemId: 'enableField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'enable'
                                            ,fieldLabel: '已启用'
                                        }

                                        ,{
                                            itemId: 'urlField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'url'
                                            ,fieldLabel: '应用链接'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults: {
                                        labelAlign: 'right'
                                        ,xtype: 'displayfield'
                                        ,padding: '5 0 0 5'
                                        ,blankText:'该字段为必填项'
                                        ,anchor: '100%'
                                        ,labelWidth:150
                                    }
                                    ,items: [
                                        {
                                            xtype: 'hiddenfield'
                                            ,anchor: '100%'
                                            ,itemId: 'versionField'
                                            ,name: 'version'
                                            ,fieldLabel: 'Label'
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
                                            itemId: 'visibleField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'visible'
                                            ,fieldLabel: '可见'
                                        }
                                    ]
                                }
                            ]
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