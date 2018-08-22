Ext.define('AM.view.devp.ops.DevpOpsAttachmentDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.ops.DevpOpsAttachmentDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '附件定义详细信息'
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
                            ,fieldLabel: '附件代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '附件名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '附件别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '附件描述'
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
                            itemId: 'fileTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'fileType'
                            ,fieldLabel: '文件类型'
                        }
                        ,{
                            itemId: 'accessTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'accessType'
                            ,fieldLabel: '访问方式'
                        }
                        ,{
                            itemId: 'domainField'
                            ,padding: '5 0 0 5'
                            ,name: 'domain'
                            ,fieldLabel: '访问域'
                        }
                        ,{
                            itemId: 'addressField'
                            ,padding: '5 0 0 5'
                            ,name: 'address'
                            ,fieldLabel: '访问地址'
                        }
                        ,{
                            itemId: 'fileVersionField'
                            ,padding: '5 0 0 5'
                            ,name: 'fileVersion'
                            ,fieldLabel: '附件版本'
                        }
                        ,{
                            itemId: 'nexusTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'nexusType'
                            ,fieldLabel: '关联记录类型'
                        }
                        ,{
                            itemId: 'nexusRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'nexusRid'
                            ,fieldLabel: '关联记录编号'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
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