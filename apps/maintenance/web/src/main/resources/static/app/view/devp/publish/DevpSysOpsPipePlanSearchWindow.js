Ext.define('AM.view.devp.publish.DevpSysOpsPipePlanSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsPipePlanSearchWindow'
    ,alias: 'widget.devppublishDevpSysOpsPipePlanSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '产品运维流水线执行计划高级查询'
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
                            ,itemId: 'prePipelineRidField'
                            ,fieldLabel: '前置流水线编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prePipelineRidMaxField'
                            ,fieldLabel: '前置流水线编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'prePipelineRidMinField'
                            ,fieldLabel: '前置流水线编号'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoBuildField'
                            ,fieldLabel: '自动构建'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoBuildMaxField'
                            ,fieldLabel: '自动构建'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoBuildMinField'
                            ,fieldLabel: '自动构建'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'scheduleField'
                            ,fieldLabel: '定时执行配置'
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
        var prdRidField = me.down("#prdRidField");
        var prdRidMaxField = me.down("#prdRidMaxField");
        var prdRidMinField = me.down("#prdRidMinField");
        var schemeRidField = me.down("#schemeRidField");
        var schemeRidMaxField = me.down("#schemeRidMaxField");
        var schemeRidMinField = me.down("#schemeRidMinField");
        var prePipelineRidField = me.down("#prePipelineRidField");
        var prePipelineRidMaxField = me.down("#prePipelineRidMaxField");
        var prePipelineRidMinField = me.down("#prePipelineRidMinField");
        var autoBuildField = me.down("#autoBuildField");
        var autoBuildMaxField = me.down("#autoBuildMaxField");
        var autoBuildMinField = me.down("#autoBuildMinField");
        var scheduleField = me.down("#scheduleField");
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
            ,prdRid:Ext.isNumber(prdRidField.getValue())?prdRidField.getValue():null
            ,prdRidMax:Ext.isNumber(prdRidMaxField.getValue())?prdRidMaxField.getValue():null
            ,prdRidMin:Ext.isNumber(prdRidMinField.getValue())?prdRidMinField.getValue():null
            ,schemeRid:Ext.isNumber(schemeRidField.getValue())?schemeRidField.getValue():null
            ,schemeRidMax:Ext.isNumber(schemeRidMaxField.getValue())?schemeRidMaxField.getValue():null
            ,schemeRidMin:Ext.isNumber(schemeRidMinField.getValue())?schemeRidMinField.getValue():null
            ,prePipelineRid:Ext.isNumber(prePipelineRidField.getValue())?prePipelineRidField.getValue():null
            ,prePipelineRidMax:Ext.isNumber(prePipelineRidMaxField.getValue())?prePipelineRidMaxField.getValue():null
            ,prePipelineRidMin:Ext.isNumber(prePipelineRidMinField.getValue())?prePipelineRidMinField.getValue():null
            ,autoBuild:Ext.isNumber(autoBuildField.getValue())?autoBuildField.getValue():null
            ,autoBuildMax:Ext.isNumber(autoBuildMaxField.getValue())?autoBuildMaxField.getValue():null
            ,autoBuildMin:Ext.isNumber(autoBuildMinField.getValue())?autoBuildMinField.getValue():null
            ,schedule:Ext.isEmpty(scheduleField.getValue())?null:scheduleField.getValue()
            ,createUcode:Ext.isEmpty(createUcodeField.getValue())?null:createUcodeField.getValue()
            ,createUname:Ext.isEmpty(createUnameField.getValue())?null:createUnameField.getValue()
            ,modifyUcode:Ext.isEmpty(modifyUcodeField.getValue())?null:modifyUcodeField.getValue()
            ,modifyUname:Ext.isEmpty(modifyUnameField.getValue())?null:modifyUnameField.getValue()
        };

        return condition;
    }

});