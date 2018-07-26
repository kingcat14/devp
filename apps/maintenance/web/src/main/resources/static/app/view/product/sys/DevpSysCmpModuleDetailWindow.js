Ext.define('AM.view.product.sys.DevpSysCmpModuleDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '组件对应模块详细信息'
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
                                            itemId: 'codeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'code'
                                            ,fieldLabel: '对应关系代码'
                                        }

                                        ,{
                                            itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '对应关系描述'
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
                                            itemId: 'elmRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'elmRid'
                                            ,fieldLabel: '系统元素编号'
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
                                            ,fieldLabel: '对应关系名称'
                                        }

                                        ,{
                                            itemId: 'aliasField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'alias'
                                            ,fieldLabel: '对应关系别名'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '对应关系类型'
                                        }

                                        ,{
                                            itemId: 'scopeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'scope'
                                            ,fieldLabel: '范围'
                                        }

                                        ,{
                                            itemId: 'phaseField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'phase'
                                            ,fieldLabel: '阶段'
                                        }

                                        ,{
                                            itemId: 'prdRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'prdRid'
                                            ,fieldLabel: '产品编号'
                                        }

                                        ,{
                                            itemId: 'mduRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'mduRid'
                                            ,fieldLabel: '模块编号'
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