Ext.define('AM.view.product.product.DevpPrdPrdlineDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '产品线定义详细信息'
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
                                            itemId: 'codeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'code'
                                            ,fieldLabel: '产品线代码'
                                        }

                                        ,{
                                            itemId: 'aliasField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'alias'
                                            ,fieldLabel: '产品线别名'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '产品线类型'
                                        }

                                        ,{
                                            itemId: 'versionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'version'
                                            ,fieldLabel: '版本'
                                        }

                                        ,{
                                            itemId: 'statusField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'status'
                                            ,fieldLabel: '状态'
                                        }

                                        ,{
                                            itemId: 'seqField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'seq'
                                            ,fieldLabel: '顺序号'
                                        }

                                        ,{
                                            itemId: 'createUcodeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'createUcode'
                                            ,fieldLabel: '创建用户代码'
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
                                            itemId: 'nameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'name'
                                            ,fieldLabel: '产品线名称'
                                        }

                                        ,{
                                            itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '产品线描述'
                                        }

                                        ,{
                                            itemId: 'domainField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'domain'
                                            ,fieldLabel: '领域'
                                        }

                                        ,{
                                            itemId: 'scopeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'scope'
                                            ,fieldLabel: '访问控制范围'
                                        }

                                        ,{
                                            itemId: 'phaseField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'phase'
                                            ,fieldLabel: '阶段'
                                        }

                                        ,{
                                            itemId: 'parentRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'parentRid'
                                            ,fieldLabel: '父产品线编号'
                                        }

                                        ,{
                                            itemId: 'recordStateField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'recordState'
                                            ,fieldLabel: '记录状态'
                                        }

                                        ,{
                                            itemId: 'modifyUcodeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'modifyUcode'
                                            ,fieldLabel: '修改用户代码'
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