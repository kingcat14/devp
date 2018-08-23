Ext.define('AM.view.devp.publish.DevpSysOpsTaskBaselineDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsTaskBaselineDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '基线设置详细信息'
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
                            itemId: 'cmSameasField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmSameas'
                            ,fieldLabel: '采用相同代码仓库'
                        }
                        ,{
                            itemId: 'cmTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmType'
                            ,fieldLabel: '代码仓库类型'
                        }
                        ,{
                            itemId: 'cmRepositoryField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmRepository'
                            ,fieldLabel: '仓库地址'
                        }
                        ,{
                            itemId: 'cmTagField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmTag'
                            ,fieldLabel: '分支标识'
                        }
                        ,{
                            itemId: 'cmUserField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmUser'
                            ,fieldLabel: '用户名'
                        }
                        ,{
                            itemId: 'cmPasswordField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmPassword'
                            ,fieldLabel: '密码'
                        }
                        ,{
                            itemId: 'subDirField'
                            ,padding: '5 0 0 5'
                            ,name: 'subDir'
                            ,fieldLabel: '子目录'
                        }
                        ,{
                            itemId: 'baselineField'
                            ,padding: '5 0 0 5'
                            ,name: 'baseline'
                            ,fieldLabel: '基线标识'
                        }
                        ,{
                            itemId: 'svnFromUrlField'
                            ,padding: '5 0 0 5'
                            ,name: 'svnFromUrl'
                            ,fieldLabel: '来源代码路径'
                        }
                        ,{
                            itemId: 'svnToUrlField'
                            ,padding: '5 0 0 5'
                            ,name: 'svnToUrl'
                            ,fieldLabel: '目标基线路径'
                        }
                        ,{
                            itemId: 'operTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'operType'
                            ,fieldLabel: '操作方式'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '状态'
                        }
                        ,{
                            itemId: 'notesField'
                            ,padding: '5 0 0 5'
                            ,name: 'notes'
                            ,fieldLabel: '备注'
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
                            itemId: 'taskRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'taskRid'
                            ,fieldLabel: '任务编号'
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