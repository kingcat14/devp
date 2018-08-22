Ext.define('AM.view.devp.ops.DevpOpsIssueDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.ops.DevpOpsIssueDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '问题记录详细信息'
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
                            ,fieldLabel: '问题代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '问题名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '问题别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '问题描述'
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
                            ,fieldLabel: '问题类型'
                        }
                        ,{
                            itemId: 'issueField'
                            ,padding: '5 0 0 5'
                            ,name: 'issue'
                            ,fieldLabel: '问题说明'
                        }
                        ,{
                            itemId: 'replyField'
                            ,padding: '5 0 0 5'
                            ,name: 'reply'
                            ,fieldLabel: '问题回复'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '处理状态'
                        }
                        ,{
                            itemId: 'hasAttachmentField'
                            ,padding: '5 0 0 5'
                            ,name: 'hasAttachment'
                            ,fieldLabel: '是否有附件'
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
                            itemId: 'nexusVersionField'
                            ,padding: '5 0 0 5'
                            ,name: 'nexusVersion'
                            ,fieldLabel: '关联记录版本'
                        }
                        ,{
                            itemId: 'nexusPhaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'nexusPhase'
                            ,fieldLabel: '关联记录阶段'
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