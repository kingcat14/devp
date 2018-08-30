Ext.define('AM.view.icode4.microservice.TransModelPropertyDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '传输对象属性详细信息'
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
                                            ,fieldLabel: '属性名'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '属性类型'
                                        }

                                        ,{
                                            itemId: 'editableField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'editable'
                                            ,fieldLabel: '可修改'
                                        }

                                        ,{
                                            itemId: 'memoField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'memo'
                                            ,fieldLabel: '备注'
                                        }

                                        ,{
                                            itemId: 'domainModelField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'domainModel'
                                            ,fieldLabel: '关联对象'
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
                                            ,fieldLabel: '属性代码'
                                        }

                                        ,{
                                            itemId: 'transModelField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'transModel'
                                            ,fieldLabel: '所属传输对象'
                                        }

                                        ,{
                                            itemId: 'nullableField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'nullable'
                                            ,fieldLabel: '可为空'
                                        }

                                        ,{
                                            itemId: 'coreRelationField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'coreRelation'
                                            ,fieldLabel: '核心对象属性'
                                        }

                                        ,{
                                            itemId: 'domainModelPropertyField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'domainModelProperty'
                                            ,fieldLabel: '关联对象属性'
                                        }
                                    ]
                                }
                            ]
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