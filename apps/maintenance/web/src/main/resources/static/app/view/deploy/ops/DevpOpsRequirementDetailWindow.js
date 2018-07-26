Ext.define('AM.view.deploy.ops.DevpOpsRequirementDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'deploy.ops.DevpOpsRequirementDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '需求定义详细信息'
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
                            ,fieldLabel: '需求代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '需求名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '需求别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '需求描述'
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
                            itemId: 'contentField'
                            ,padding: '5 0 0 5'
                            ,name: 'content'
                            ,fieldLabel: '内容'
                        }
                        ,{
                            itemId: 'hasAttachmentField'
                            ,padding: '5 0 0 5'
                            ,name: 'hasAttachment'
                            ,fieldLabel: '是否有附件'
                        }
                        ,{
                            itemId: 'stereotypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'stereotype'
                            ,fieldLabel: '构造型'
                        }
                        ,{
                            itemId: 'scopeField'
                            ,padding: '5 0 0 5'
                            ,name: 'scope'
                            ,fieldLabel: '访问控制范围'
                        }
                        ,{
                            itemId: 'versionField'
                            ,padding: '5 0 0 5'
                            ,name: 'version'
                            ,fieldLabel: '版本'
                        }
                        ,{
                            itemId: 'phaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'phase'
                            ,fieldLabel: '阶段'
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