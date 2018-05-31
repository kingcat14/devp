Ext.define('AM.view.product.sys.DevpSysElmInstInfoDetailWindow', {
    extend: 'Ext.window.Window'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '系统元素实例详细信息'
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
                                            ,fieldLabel: '扩展信息名称'
                                        }

                                        ,{
                                            itemId: 'descriptionField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'description'
                                            ,fieldLabel: '扩展信息描述'
                                        }

                                        ,{
                                            itemId: 'contRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'contRid'
                                            ,fieldLabel: '关联连接编号'
                                        }

                                        ,{
                                            itemId: 'selmRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'selmRid'
                                            ,fieldLabel: '来源系统元素编号'
                                        }

                                        ,{
                                            itemId: 'delmRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'delmRid'
                                            ,fieldLabel: '目标系统元素编号'
                                        }

                                        ,{
                                            itemId: 'dinstRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'dinstRid'
                                            ,fieldLabel: '目标系统元素实例编号'
                                        }

                                        ,{
                                            itemId: 'typeField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'type'
                                            ,fieldLabel: '设值方式'
                                        }

                                        ,{
                                            itemId: 'infoValue2Field'
                                            ,padding: '5 0 0 5'
                                            ,name: 'infoValue2'
                                            ,fieldLabel: '信息值2'
                                        }

                                        ,{
                                            itemId: 'infoValue4Field'
                                            ,padding: '5 0 0 5'
                                            ,name: 'infoValue4'
                                            ,fieldLabel: '信息值4'
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
                                            ,fieldLabel: '扩展信息代码'
                                        }

                                        ,{
                                            itemId: 'aliasField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'alias'
                                            ,fieldLabel: '扩展信息别名'
                                        }

                                        ,{
                                            itemId: 'prdRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'prdRid'
                                            ,fieldLabel: '产品编号'
                                        }

                                        ,{
                                            itemId: 'sprdRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'sprdRid'
                                            ,fieldLabel: '来源产品编号'
                                        }

                                        ,{
                                            itemId: 'dprdRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'dprdRid'
                                            ,fieldLabel: '目标产品编号'
                                        }

                                        ,{
                                            itemId: 'sinstRidField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'sinstRid'
                                            ,fieldLabel: '来源系统元素实例编号'
                                        }

                                        ,{
                                            itemId: 'seqField'
                                            ,padding: '5 0 0 5'
                                            ,name: 'seq'
                                            ,fieldLabel: '顺序号'
                                        }

                                        ,{
                                            itemId: 'infoValue1Field'
                                            ,padding: '5 0 0 5'
                                            ,name: 'infoValue1'
                                            ,fieldLabel: '信息值1'
                                        }

                                        ,{
                                            itemId: 'infoValue3Field'
                                            ,padding: '5 0 0 5'
                                            ,name: 'infoValue3'
                                            ,fieldLabel: '信息值3'
                                        }

                                        ,{
                                            itemId: 'infoValue5Field'
                                            ,padding: '5 0 0 5'
                                            ,name: 'infoValue5'
                                            ,fieldLabel: '信息值5'
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