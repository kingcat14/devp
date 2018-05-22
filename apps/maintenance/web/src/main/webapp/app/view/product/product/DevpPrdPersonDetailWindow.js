Ext.define('AM.view.product.product.DevpPrdPersonDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '产品干系人详细信息'
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
                                            itemId: 'tidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'tid'
                                            ,fieldLabel: '租户编号'
                                        }

                                        ,{
                                            itemId: 'nameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'name'
                                            ,fieldLabel: '用户名称'
                                        }

                                        ,{
                                            itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '用户描述'
                                        }

                                        ,{
                                            itemId: 'nexusRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'nexusRid'
                                            ,fieldLabel: '关联元素编号'
                                        }

                                        ,{
                                            itemId: 'uidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'uid'
                                            ,fieldLabel: '用户编号'
                                        }

                                        ,{
                                            itemId: 'roleField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'role'
                                            ,fieldLabel: '用户类型'
                                        }

                                        ,{
                                            itemId: 'userTidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'userTid'
                                            ,fieldLabel: '用户租户编号'
                                        }

                                        ,{
                                            itemId: 'orgNameField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'orgName'
                                            ,fieldLabel: '组织名称'
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
                                            itemId: 'codeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'code'
                                            ,fieldLabel: '用户代码'
                                        }

                                        ,{
                                            itemId: 'aliasField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'alias'
                                            ,fieldLabel: '用户别名'
                                        }

                                        ,{
                                            itemId: 'nexusTypeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'nexusType'
                                            ,fieldLabel: '关联元素类型'
                                        }

                                        ,{
                                            itemId: 'seqField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'seq'
                                            ,fieldLabel: '顺序号'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '用户类型'
                                        }

                                        ,{
                                            itemId: 'statusField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'status'
                                            ,fieldLabel: '状态'
                                        }

                                        ,{
                                            itemId: 'orgRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'orgRid'
                                            ,fieldLabel: '组织编号'
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