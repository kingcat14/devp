Ext.define('AM.view.devp.publish.DevpSysOpsPipeCmpDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsPipeCmpDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '产品运维流水线对应的组件详细信息'
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
                            itemId: 'tidField'
                            ,padding: '5 0 0 5'
                            ,name: 'tid'
                            ,fieldLabel: '租户编号'
                        }
                        ,{
                            itemId: 'etypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'etype'
                            ,fieldLabel: '元素类型'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '系统元素名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '系统元素代码'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '系统元素别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '系统元素描述'
                        }
                        ,{
                            itemId: 'recordStateField'
                            ,padding: '5 0 0 5'
                            ,name: 'recordState'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'subTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'subType'
                            ,fieldLabel: '子类型'
                        }
                        ,{
                            itemId: 'updateFlagField'
                            ,padding: '5 0 0 5'
                            ,name: 'updateFlag'
                            ,fieldLabel: '是否更新'
                        }
                        ,{
                            itemId: 'prdRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'prdRid'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            itemId: 'schemeRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'schemeRid'
                            ,fieldLabel: '部署方案编号'
                        }
                        ,{
                            itemId: 'cmpRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmpRid'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            itemId: 'createUcodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'createUcode'
                            ,fieldLabel: '创建用户代码'
                        }
                        ,{
                            itemId: 'createUnameField'
                            ,padding: '5 0 0 5'
                            ,name: 'createUname'
                            ,fieldLabel: '创建用户姓名'
                        }
                        ,{
                            itemId: 'modifyUcodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'modifyUcode'
                            ,fieldLabel: '修改用户代码'
                        }
                        ,{
                            itemId: 'modifyUnameField'
                            ,padding: '5 0 0 5'
                            ,name: 'modifyUname'
                            ,fieldLabel: '修改用户姓名'
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