Ext.define('AM.view.icode4.tplfile.TplCodeDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '文件模板详细信息'
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
                                            ,fieldLabel: '模板名称'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '模板类型'
                                        }

                                        ,{
                                            itemId: 'filePathField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'filePath'
                                            ,fieldLabel: '文件路径'
                                        }

                                        ,{
                                            itemId: 'tplSetField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tplSet'
                                            ,fieldLabel: '所属集合'
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
                                            ,fieldLabel: '模板代码'
                                        }

                                        ,{
                                            itemId: 'acceptModelTypeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'acceptModelType'
                                            ,fieldLabel: '接收模型类型'
                                        }

                                        ,{
                                            itemId: 'fileNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'fileName'
                                            ,fieldLabel: '文件名'
                                        }

                                        ,{
                                            itemId: 'overridableField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'overridable'
                                            ,fieldLabel: '可覆盖'
                                        }
                                    ]
                                }
                            ]
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'contentField'
                            ,padding: '5 0 0 5'
                            ,name: 'content'
                            ,fieldLabel: '文件内容'
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