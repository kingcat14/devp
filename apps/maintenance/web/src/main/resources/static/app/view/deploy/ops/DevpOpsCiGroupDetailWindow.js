Ext.define('AM.view.deploy.ops.DevpOpsCiGroupDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsCiGroupDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '资产项目分组映射详细信息'
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
                            ,fieldLabel: '名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '代码'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
                        }
                        ,{
                            itemId: 'recordStateField'
                            ,padding: '5 0 0 5'
                            ,name: 'recordState'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            itemId: 'typeCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'typeCode'
                            ,fieldLabel: '类型代码'
                        }
                        ,{
                            itemId: 'typeNameField'
                            ,padding: '5 0 0 5'
                            ,name: 'typeName'
                            ,fieldLabel: '类型名称'
                        }
                        ,{
                            itemId: 'groupRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'groupRid'
                            ,fieldLabel: '分组记录编号'
                        }
                        ,{
                            itemId: 'ciRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'ciRid'
                            ,fieldLabel: '资产记录编号'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            itemId: 'parasCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'parasCode'
                            ,fieldLabel: '参数定义标识'
                        }
                        ,{
                            itemId: 'cmodifyUcodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmodifyUcode'
                            ,fieldLabel: '修改用户代码'
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