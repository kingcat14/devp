Ext.define('AM.view.devp.publish.DevpSysOpsPipeNodeSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsPipeNodeSearchWindow'
    ,alias: 'widget.devppublishDevpSysOpsPipeNodeSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '流水线执行节点高级查询'
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
                            xtype: 'textfield'
                            ,itemId: 'execModelField'
                            ,fieldLabel: '执行方式'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'execSeqField'
                            ,fieldLabel: '执行顺序'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'returnCodeField'
                            ,fieldLabel: '返回码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'execStatusField'
                            ,fieldLabel: '执行状态'
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
                            ,itemId: 'pipelineRidField'
                            ,fieldLabel: '流水线编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'pipelineRidMaxField'
                            ,fieldLabel: '流水线编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'pipelineRidMinField'
                            ,fieldLabel: '流水线编号'
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
                            ,itemId: 'parentRidField'
                            ,fieldLabel: '父节点编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'parentRidMaxField'
                            ,fieldLabel: '父节点编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'parentRidMinField'
                            ,fieldLabel: '父节点编号'
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
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'defaultPipelineField'
                            ,fieldLabel: '缺省选中流水线'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'defaultPipelineMaxField'
                            ,fieldLabel: '缺省选中流水线'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'defaultPipelineMinField'
                            ,fieldLabel: '缺省选中流水线'
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
        var execModelField = me.down("#execModelField");
        var execSeqField = me.down("#execSeqField");
        var returnCodeField = me.down("#returnCodeField");
        var execStatusField = me.down("#execStatusField");
        var notesField = me.down("#notesField");
        var prdRidField = me.down("#prdRidField");
        var prdRidMaxField = me.down("#prdRidMaxField");
        var prdRidMinField = me.down("#prdRidMinField");
        var schemeRidField = me.down("#schemeRidField");
        var schemeRidMaxField = me.down("#schemeRidMaxField");
        var schemeRidMinField = me.down("#schemeRidMinField");
        var pipelineRidField = me.down("#pipelineRidField");
        var pipelineRidMaxField = me.down("#pipelineRidMaxField");
        var pipelineRidMinField = me.down("#pipelineRidMinField");
        var cmpRidField = me.down("#cmpRidField");
        var cmpRidMaxField = me.down("#cmpRidMaxField");
        var cmpRidMinField = me.down("#cmpRidMinField");
        var taskRidField = me.down("#taskRidField");
        var taskRidMaxField = me.down("#taskRidMaxField");
        var taskRidMinField = me.down("#taskRidMinField");
        var parentRidField = me.down("#parentRidField");
        var parentRidMaxField = me.down("#parentRidMaxField");
        var parentRidMinField = me.down("#parentRidMinField");
        var seqField = me.down("#seqField");
        var seqMaxField = me.down("#seqMaxField");
        var seqMinField = me.down("#seqMinField");
        var defaultPipelineField = me.down("#defaultPipelineField");
        var defaultPipelineMaxField = me.down("#defaultPipelineMaxField");
        var defaultPipelineMinField = me.down("#defaultPipelineMinField");
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
            ,execModel:Ext.isEmpty(execModelField.getValue())?null:execModelField.getValue()
            ,execSeq:Ext.isEmpty(execSeqField.getValue())?null:execSeqField.getValue()
            ,returnCode:Ext.isEmpty(returnCodeField.getValue())?null:returnCodeField.getValue()
            ,execStatus:Ext.isEmpty(execStatusField.getValue())?null:execStatusField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,prdRidMax:Ext.isNumber(prdRidMaxField.getValue())?prdRidMaxField.getValue():null
            ,prdRidMin:Ext.isNumber(prdRidMinField.getValue())?prdRidMinField.getValue():null
            ,schemeRid:Ext.isNumber(schemeRidField.getValue())?schemeRidField.getValue():null
            ,schemeRidMax:Ext.isNumber(schemeRidMaxField.getValue())?schemeRidMaxField.getValue():null
            ,schemeRidMin:Ext.isNumber(schemeRidMinField.getValue())?schemeRidMinField.getValue():null
            ,pipelineRid:Ext.isNumber(pipelineRidField.getValue())?pipelineRidField.getValue():null
            ,pipelineRidMax:Ext.isNumber(pipelineRidMaxField.getValue())?pipelineRidMaxField.getValue():null
            ,pipelineRidMin:Ext.isNumber(pipelineRidMinField.getValue())?pipelineRidMinField.getValue():null
            ,cmpRid:Ext.isNumber(cmpRidField.getValue())?cmpRidField.getValue():null
            ,cmpRidMax:Ext.isNumber(cmpRidMaxField.getValue())?cmpRidMaxField.getValue():null
            ,cmpRidMin:Ext.isNumber(cmpRidMinField.getValue())?cmpRidMinField.getValue():null
            ,taskRid:Ext.isNumber(taskRidField.getValue())?taskRidField.getValue():null
            ,taskRidMax:Ext.isNumber(taskRidMaxField.getValue())?taskRidMaxField.getValue():null
            ,taskRidMin:Ext.isNumber(taskRidMinField.getValue())?taskRidMinField.getValue():null
            ,parentRid:Ext.isNumber(parentRidField.getValue())?parentRidField.getValue():null
            ,parentRidMax:Ext.isNumber(parentRidMaxField.getValue())?parentRidMaxField.getValue():null
            ,parentRidMin:Ext.isNumber(parentRidMinField.getValue())?parentRidMinField.getValue():null
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,seqMax:Ext.isNumber(seqMaxField.getValue())?seqMaxField.getValue():null
            ,seqMin:Ext.isNumber(seqMinField.getValue())?seqMinField.getValue():null
            ,defaultPipeline:Ext.isNumber(defaultPipelineField.getValue())?defaultPipelineField.getValue():null
            ,defaultPipelineMax:Ext.isNumber(defaultPipelineMaxField.getValue())?defaultPipelineMaxField.getValue():null
            ,defaultPipelineMin:Ext.isNumber(defaultPipelineMinField.getValue())?defaultPipelineMinField.getValue():null
            ,createUcode:Ext.isEmpty(createUcodeField.getValue())?null:createUcodeField.getValue()
            ,createUname:Ext.isEmpty(createUnameField.getValue())?null:createUnameField.getValue()
            ,modifyUcode:Ext.isEmpty(modifyUcodeField.getValue())?null:modifyUcodeField.getValue()
            ,modifyUname:Ext.isEmpty(modifyUnameField.getValue())?null:modifyUnameField.getValue()
        };

        return condition;
    }

});