Ext.define('AM.view.devp.publish.DevpSysOpsDockerAccessSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.publish.DevpSysOpsDockerAccessSearchWindow'
    ,alias: 'widget.devppublishDevpSysOpsDockerAccessSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '部署容器访问参数高级查询'
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
                            ,itemId: 'notesField'
                            ,fieldLabel: '备注'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'servicesPortField'
                            ,fieldLabel: '服务端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'servicesPortMaxField'
                            ,fieldLabel: '服务端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'servicesPortMinField'
                            ,fieldLabel: '服务端口'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'dockerPortField'
                            ,fieldLabel: '服务端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'dockerPortMaxField'
                            ,fieldLabel: '服务端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'dockerPortMinField'
                            ,fieldLabel: '服务端口'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'protocolField'
                            ,fieldLabel: '协议'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoAssignField'
                            ,fieldLabel: '自动分配'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoAssignMaxField'
                            ,fieldLabel: '自动分配'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'autoAssignMinField'
                            ,fieldLabel: '自动分配'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'hostPortField'
                            ,fieldLabel: '自动分配'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'hostPortMaxField'
                            ,fieldLabel: '自动分配'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'hostPortMinField'
                            ,fieldLabel: '自动分配'
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
                            ,itemId: 'dockerRidField'
                            ,fieldLabel: '容器编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'dockerRidMaxField'
                            ,fieldLabel: '容器编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'dockerRidMinField'
                            ,fieldLabel: '容器编号'
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
        var notesField = me.down("#notesField");
        var servicesPortField = me.down("#servicesPortField");
        var servicesPortMaxField = me.down("#servicesPortMaxField");
        var servicesPortMinField = me.down("#servicesPortMinField");
        var dockerPortField = me.down("#dockerPortField");
        var dockerPortMaxField = me.down("#dockerPortMaxField");
        var dockerPortMinField = me.down("#dockerPortMinField");
        var protocolField = me.down("#protocolField");
        var autoAssignField = me.down("#autoAssignField");
        var autoAssignMaxField = me.down("#autoAssignMaxField");
        var autoAssignMinField = me.down("#autoAssignMinField");
        var hostPortField = me.down("#hostPortField");
        var hostPortMaxField = me.down("#hostPortMaxField");
        var hostPortMinField = me.down("#hostPortMinField");
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
        var dockerRidField = me.down("#dockerRidField");
        var dockerRidMaxField = me.down("#dockerRidMaxField");
        var dockerRidMinField = me.down("#dockerRidMinField");
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
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,servicesPort:Ext.isNumber(servicesPortField.getValue())?servicesPortField.getValue():null
            ,servicesPortMax:Ext.isNumber(servicesPortMaxField.getValue())?servicesPortMaxField.getValue():null
            ,servicesPortMin:Ext.isNumber(servicesPortMinField.getValue())?servicesPortMinField.getValue():null
            ,dockerPort:Ext.isNumber(dockerPortField.getValue())?dockerPortField.getValue():null
            ,dockerPortMax:Ext.isNumber(dockerPortMaxField.getValue())?dockerPortMaxField.getValue():null
            ,dockerPortMin:Ext.isNumber(dockerPortMinField.getValue())?dockerPortMinField.getValue():null
            ,protocol:Ext.isEmpty(protocolField.getValue())?null:protocolField.getValue()
            ,autoAssign:Ext.isNumber(autoAssignField.getValue())?autoAssignField.getValue():null
            ,autoAssignMax:Ext.isNumber(autoAssignMaxField.getValue())?autoAssignMaxField.getValue():null
            ,autoAssignMin:Ext.isNumber(autoAssignMinField.getValue())?autoAssignMinField.getValue():null
            ,hostPort:Ext.isNumber(hostPortField.getValue())?hostPortField.getValue():null
            ,hostPortMax:Ext.isNumber(hostPortMaxField.getValue())?hostPortMaxField.getValue():null
            ,hostPortMin:Ext.isNumber(hostPortMinField.getValue())?hostPortMinField.getValue():null
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
            ,dockerRid:Ext.isNumber(dockerRidField.getValue())?dockerRidField.getValue():null
            ,dockerRidMax:Ext.isNumber(dockerRidMaxField.getValue())?dockerRidMaxField.getValue():null
            ,dockerRidMin:Ext.isNumber(dockerRidMinField.getValue())?dockerRidMinField.getValue():null
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