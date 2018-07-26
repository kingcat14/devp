Ext.define('AM.view.deploy.ops.DevpOpsElementInfoDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsElementInfoDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '系统元素扩充信息详细信息'
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
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '扩展信息代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '扩展信息名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '扩展信息别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '扩展信息描述'
                        }
                        ,{
                            itemId: 'recordStateField'
                            ,padding: '5 0 0 5'
                            ,name: 'recordState'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            itemId: 'elmRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'elmRid'
                            ,fieldLabel: '元素编号'
                        }
                        ,{
                            itemId: 'instRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'instRid'
                            ,fieldLabel: '元素实例编号'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            itemId: 'infoCode1Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoCode1'
                            ,fieldLabel: '扩展信息代码1'
                        }
                        ,{
                            itemId: 'infoValue1Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoValue1'
                            ,fieldLabel: '扩展信息值1'
                        }
                        ,{
                            itemId: 'infoCode2Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoCode2'
                            ,fieldLabel: '扩展信息代码2'
                        }
                        ,{
                            itemId: 'infoValue2Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoValue2'
                            ,fieldLabel: '扩展信息值2'
                        }
                        ,{
                            itemId: 'infoCode3Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoCode3'
                            ,fieldLabel: '扩展信息代码3'
                        }
                        ,{
                            itemId: 'infoValue3Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoValue3'
                            ,fieldLabel: '扩展信息值3'
                        }
                        ,{
                            itemId: 'infoCode4Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoCode4'
                            ,fieldLabel: '扩展信息代码4'
                        }
                        ,{
                            itemId: 'infoValue4Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoValue4'
                            ,fieldLabel: '扩展信息值4'
                        }
                        ,{
                            itemId: 'infoCode5Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoCode5'
                            ,fieldLabel: '扩展信息代码5'
                        }
                        ,{
                            itemId: 'infoValue5Field'
                            ,padding: '5 0 0 5'
                            ,name: 'infoValue5'
                            ,fieldLabel: '扩展信息值5'
                        }
                        ,{
                            itemId: 'notesField'
                            ,padding: '5 0 0 5'
                            ,name: 'notes'
                            ,fieldLabel: '备注'
                        }
                        ,{
                            itemId: 'parasCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'parasCode'
                            ,fieldLabel: '参数定义标识'
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