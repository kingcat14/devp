Ext.define('AM.view.devp.publish.DevpSysOpsTaskCompileSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsTaskCompileSearchWindow'
    ,alias: 'widget.devppublishDevpSysOpsTaskCompileSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '编译设置高级查询'
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
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'tidField'
                            ,fieldLabel: '租户编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'tidMaxField'
                            ,fieldLabel: '租户编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'tidMinField'
                            ,fieldLabel: '租户编号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'etypeField'
                            ,fieldLabel: '元素类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '系统元素名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '系统元素代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'aliasField'
                            ,fieldLabel: '系统元素别名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '系统元素描述'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'recordStateField'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'recordStateMaxField'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'recordStateMinField'
                            ,fieldLabel: '记录状态'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'subTypeField'
                            ,fieldLabel: '子类型'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmSameasField'
                            ,fieldLabel: '采用相同代码仓库'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmSameasMaxField'
                            ,fieldLabel: '采用相同代码仓库'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmSameasMinField'
                            ,fieldLabel: '采用相同代码仓库'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'cmTypeField'
                            ,fieldLabel: '代码仓库类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'cmRepositoryField'
                            ,fieldLabel: '仓库地址'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'cmTagField'
                            ,fieldLabel: '分支标识'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'cmUserField'
                            ,fieldLabel: '用户名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'cmPasswordField'
                            ,fieldLabel: '密码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'subDirField'
                            ,fieldLabel: '子目录'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'baselineField'
                            ,fieldLabel: '基线标识'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'svnCodeUrlField'
                            ,fieldLabel: '代码路径'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'buildToolsField'
                            ,fieldLabel: '构建工具'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'envVersionField'
                            ,fieldLabel: '执行环境版本'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'toolVersionField'
                            ,fieldLabel: '工具版本'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'preActionField'
                            ,fieldLabel: '构建前操作'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'buildActionField'
                            ,fieldLabel: '构建操作'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'postActionField'
                            ,fieldLabel: '构建后操作'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'notesField'
                            ,fieldLabel: '备注'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidField'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMaxField'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prdRidMinField'
                            ,fieldLabel: '产品编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeRidField'
                            ,fieldLabel: '部署方案编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeRidMaxField'
                            ,fieldLabel: '部署方案编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeRidMinField'
                            ,fieldLabel: '部署方案编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmpRidField'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmpRidMaxField'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'cmpRidMinField'
                            ,fieldLabel: '组件编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'taskRidField'
                            ,fieldLabel: '任务编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'taskRidMaxField'
                            ,fieldLabel: '任务编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'taskRidMinField'
                            ,fieldLabel: '任务编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqField'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqMaxField'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'seqMinField'
                            ,fieldLabel: '顺序号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'createUcodeField'
                            ,fieldLabel: '创建用户代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'createUnameField'
                            ,fieldLabel: '创建用户姓名'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'modifyUcodeField'
                            ,fieldLabel: '修改用户代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'modifyUnameField'
                            ,fieldLabel: '修改用户姓名'
                        }

                            ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();

        me.fireEvent('saved');


    }
    ,getCondition: function(){

        var me = this;
        var tidField = me.down("#tidField");
        var tidMaxField = me.down("#tidMaxField");
        var tidMinField = me.down("#tidMinField");
        var etypeField = me.down("#etypeField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var descriptionField = me.down("#descriptionField");
        var recordStateField = me.down("#recordStateField");
        var recordStateMaxField = me.down("#recordStateMaxField");
        var recordStateMinField = me.down("#recordStateMinField");
        var typeField = me.down("#typeField");
        var subTypeField = me.down("#subTypeField");
        var cmSameasField = me.down("#cmSameasField");
        var cmSameasMaxField = me.down("#cmSameasMaxField");
        var cmSameasMinField = me.down("#cmSameasMinField");
        var cmTypeField = me.down("#cmTypeField");
        var cmRepositoryField = me.down("#cmRepositoryField");
        var cmTagField = me.down("#cmTagField");
        var cmUserField = me.down("#cmUserField");
        var cmPasswordField = me.down("#cmPasswordField");
        var subDirField = me.down("#subDirField");
        var baselineField = me.down("#baselineField");
        var svnCodeUrlField = me.down("#svnCodeUrlField");
        var buildToolsField = me.down("#buildToolsField");
        var envVersionField = me.down("#envVersionField");
        var toolVersionField = me.down("#toolVersionField");
        var preActionField = me.down("#preActionField");
        var buildActionField = me.down("#buildActionField");
        var postActionField = me.down("#postActionField");
        var statusField = me.down("#statusField");
        var notesField = me.down("#notesField");
        var prdRidField = me.down("#prdRidField");
        var prdRidMaxField = me.down("#prdRidMaxField");
        var prdRidMinField = me.down("#prdRidMinField");
        var schemeRidField = me.down("#schemeRidField");
        var schemeRidMaxField = me.down("#schemeRidMaxField");
        var schemeRidMinField = me.down("#schemeRidMinField");
        var cmpRidField = me.down("#cmpRidField");
        var cmpRidMaxField = me.down("#cmpRidMaxField");
        var cmpRidMinField = me.down("#cmpRidMinField");
        var taskRidField = me.down("#taskRidField");
        var taskRidMaxField = me.down("#taskRidMaxField");
        var taskRidMinField = me.down("#taskRidMinField");
        var seqField = me.down("#seqField");
        var seqMaxField = me.down("#seqMaxField");
        var seqMinField = me.down("#seqMinField");
        var createUcodeField = me.down("#createUcodeField");
        var createUnameField = me.down("#createUnameField");
        var modifyUcodeField = me.down("#modifyUcodeField");
        var modifyUnameField = me.down("#modifyUnameField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,tidMax:Ext.isNumber(tidMaxField.getValue())?tidMaxField.getValue():null
            ,tidMin:Ext.isNumber(tidMinField.getValue())?tidMinField.getValue():null
            ,etype:Ext.isEmpty(etypeField.getValue())?null:etypeField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,recordState:Ext.isNumber(recordStateField.getValue())?recordStateField.getValue():null
            ,recordStateMax:Ext.isNumber(recordStateMaxField.getValue())?recordStateMaxField.getValue():null
            ,recordStateMin:Ext.isNumber(recordStateMinField.getValue())?recordStateMinField.getValue():null
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,subType:Ext.isEmpty(subTypeField.getValue())?null:subTypeField.getValue()
            ,cmSameas:Ext.isNumber(cmSameasField.getValue())?cmSameasField.getValue():null
            ,cmSameasMax:Ext.isNumber(cmSameasMaxField.getValue())?cmSameasMaxField.getValue():null
            ,cmSameasMin:Ext.isNumber(cmSameasMinField.getValue())?cmSameasMinField.getValue():null
            ,cmType:Ext.isEmpty(cmTypeField.getValue())?null:cmTypeField.getValue()
            ,cmRepository:Ext.isEmpty(cmRepositoryField.getValue())?null:cmRepositoryField.getValue()
            ,cmTag:Ext.isEmpty(cmTagField.getValue())?null:cmTagField.getValue()
            ,cmUser:Ext.isEmpty(cmUserField.getValue())?null:cmUserField.getValue()
            ,cmPassword:Ext.isEmpty(cmPasswordField.getValue())?null:cmPasswordField.getValue()
            ,subDir:Ext.isEmpty(subDirField.getValue())?null:subDirField.getValue()
            ,baseline:Ext.isEmpty(baselineField.getValue())?null:baselineField.getValue()
            ,svnCodeUrl:Ext.isEmpty(svnCodeUrlField.getValue())?null:svnCodeUrlField.getValue()
            ,buildTools:Ext.isEmpty(buildToolsField.getValue())?null:buildToolsField.getValue()
            ,envVersion:Ext.isEmpty(envVersionField.getValue())?null:envVersionField.getValue()
            ,toolVersion:Ext.isEmpty(toolVersionField.getValue())?null:toolVersionField.getValue()
            ,preAction:Ext.isEmpty(preActionField.getValue())?null:preActionField.getValue()
            ,buildAction:Ext.isEmpty(buildActionField.getValue())?null:buildActionField.getValue()
            ,postAction:Ext.isEmpty(postActionField.getValue())?null:postActionField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,prdRidMax:Ext.isNumber(prdRidMaxField.getValue())?prdRidMaxField.getValue():null
            ,prdRidMin:Ext.isNumber(prdRidMinField.getValue())?prdRidMinField.getValue():null
            ,schemeRid:Ext.isNumber(schemeRidField.getValue())?schemeRidField.getValue():null
            ,schemeRidMax:Ext.isNumber(schemeRidMaxField.getValue())?schemeRidMaxField.getValue():null
            ,schemeRidMin:Ext.isNumber(schemeRidMinField.getValue())?schemeRidMinField.getValue():null
            ,cmpRid:Ext.isNumber(cmpRidField.getValue())?cmpRidField.getValue():null
            ,cmpRidMax:Ext.isNumber(cmpRidMaxField.getValue())?cmpRidMaxField.getValue():null
            ,cmpRidMin:Ext.isNumber(cmpRidMinField.getValue())?cmpRidMinField.getValue():null
            ,taskRid:Ext.isNumber(taskRidField.getValue())?taskRidField.getValue():null
            ,taskRidMax:Ext.isNumber(taskRidMaxField.getValue())?taskRidMaxField.getValue():null
            ,taskRidMin:Ext.isNumber(taskRidMinField.getValue())?taskRidMinField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,seqMax:Ext.isNumber(seqMaxField.getValue())?seqMaxField.getValue():null
            ,seqMin:Ext.isNumber(seqMinField.getValue())?seqMinField.getValue():null
            ,createUcode:Ext.isEmpty(createUcodeField.getValue())?null:createUcodeField.getValue()
            ,createUname:Ext.isEmpty(createUnameField.getValue())?null:createUnameField.getValue()
            ,modifyUcode:Ext.isEmpty(modifyUcodeField.getValue())?null:modifyUcodeField.getValue()
            ,modifyUname:Ext.isEmpty(modifyUnameField.getValue())?null:modifyUnameField.getValue()
        };

        return condition;
    }

});