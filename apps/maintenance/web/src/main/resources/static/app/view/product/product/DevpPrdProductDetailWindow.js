Ext.define('AM.view.product.product.DevpPrdProductDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '产品定义详细信息'
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
                                            ,fieldLabel: '产品名称'
                                        }

                                        ,{
                                            itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '产品描述'
                                        }

                                 
                                        ,{
                                            itemId: 'prdDeptField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'prdDept'
                                            ,fieldLabel: '所属部门'
                                        }

                                        ,{
                                            itemId: 'devManagerField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'devManager'
                                            ,fieldLabel: '开发负责人'
                                        }

                                        ,{
                                            itemId: 'bizLineField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'bizLine'
                                            ,fieldLabel: '业务线'
                                        }

                                        ,{
                                            itemId: 'goliveField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'golive'
                                            ,fieldLabel: '启用时间'
                                            ,renderer: function (value, field) {
                                                return Ext.Date.format(value, 'Y-m-d')
                                            }
                                        }

                                        ,{
                                            itemId: 'custManagerField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'custManager'
                                            ,fieldLabel: '客户代表'
                                        }

                                        ,{
                                            itemId: 'acquisitionModeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'acquisitionMode'
                                            ,fieldLabel: '获取方式'
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
                                            itemId: 'recordStateField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'recordState'
                                            ,fieldLabel: '记录状态'
                                        }

                                        ,{
                                            itemId: 'cmodifyUcodeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'cmodifyUcode'
                                            ,fieldLabel: '修改用户代码'
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
                                            ,fieldLabel: '产品代码'
                                        }

                                        ,{
                                            itemId: 'aliasField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'alias'
                                            ,fieldLabel: '产品别名'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '产品类型'
                                        }

                                        ,{
                                            itemId: 'scopeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'scope'
                                            ,fieldLabel: '范围'
                                        }

                                        ,{
                                            itemId: 'prdOwnerField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'prdOwner'
                                            ,fieldLabel: '产品负责人'
                                        }

                                        ,{
                                            itemId: 'opsManagerField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'opsManager'
                                            ,fieldLabel: '维护负责人'
                                        }

                                        ,{
                                            itemId: 'bizManagerField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'bizManager'
                                            ,fieldLabel: '业务代表'
                                        }

                                        ,{
                                            itemId: 'majorCustField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'majorCust'
                                            ,fieldLabel: '主要客户'
                                        }

                                        ,{
                                            itemId: 'custUsageField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'custUsage'
                                            ,fieldLabel: '客户使用情况'
                                        }

                                        ,{
                                            itemId: 'acquisitionDescField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'acquisitionDesc'
                                            ,fieldLabel: '获取方式说明'
                                        }

                                        ,{
                                            itemId: 'phaseField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'phase'
                                            ,fieldLabel: '阶段'
                                        }

                                        ,{
                                            itemId: 'notesField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'notes'
                                            ,fieldLabel: '备注'
                                        }

                                        ,{
                                            itemId: 'createUcodeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'createUcode'
                                            ,fieldLabel: '创建用户代码'
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