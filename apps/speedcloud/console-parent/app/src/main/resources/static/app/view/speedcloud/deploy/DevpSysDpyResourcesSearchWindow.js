Ext.define('AM.view.speedcloud.deploy.DevpSysDpyResourcesSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deploy.DevpSysDpyResourcesSearchWindow'
    ,alias: 'widget.speedclouddeployDevpSysDpyResourcesSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '方案资源高级查询'
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
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '资源名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '资源代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'aliasField'
                            ,fieldLabel: '资源别名'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpyResourcesCategoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'categoryField'
                            ,fieldLabel: '资源类别'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpyResourcesTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'typeField'
                            ,fieldLabel: '资源类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'notesField'
                            ,fieldLabel: '备注'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '资源描述'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'versionField'
                            ,fieldLabel: '版本'
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
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.env.AppEnvConfigStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'evnField'
                            ,fieldLabel: '所属环境'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'projectField'
                            ,fieldLabel: '产品编号'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: [
                                [true,'是']
                                ,[false,'否']
                            ]
                            ,value:true
                            ,typeAhead:false
                            ,editable:false
                            ,itemId: 'outerResourceField'
                            ,fieldLabel: '外部资源'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.deploy.DevpSysDpySchemeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'schemeField'
                            ,fieldLabel: '所属方案'
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var aliasField = me.down("#aliasField");
        var categoryField = me.down("#categoryField");
        var typeField = me.down("#typeField");
        var notesField = me.down("#notesField");
        var descriptionField = me.down("#descriptionField");
        var versionField = me.down("#versionField");
        var seqField = me.down("#seqField");
        var seqMaxField = me.down("#seqMaxField");
        var seqMinField = me.down("#seqMinField");
        var evnField = me.down("#evnField");
        var statusField = me.down("#statusField");
        var projectField = me.down("#projectField");
        var outerResourceField = me.down("#outerResourceField");
        var schemeField = me.down("#schemeField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,alias:Ext.isEmpty(aliasField.getValue())?null:aliasField.getValue()
            ,category:Ext.isEmpty(categoryField.getValue())?null:categoryField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,notes:Ext.isEmpty(notesField.getValue())?null:notesField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,version:Ext.isEmpty(versionField.getValue())?null:versionField.getValue()
            ,seq:Ext.isNumber(seqField.getValue())?seqField.getValue():null
            ,seqMax:Ext.isNumber(seqMaxField.getValue())?seqMaxField.getValue():null
            ,seqMin:Ext.isNumber(seqMinField.getValue())?seqMinField.getValue():null
            ,evn:Ext.isEmpty(evnField.getValue())?null:evnField.getValue()
            ,status:Ext.isEmpty(statusField.getValue())?null:statusField.getValue()
            ,project:Ext.isEmpty(projectField.getValue())?null:projectField.getValue()
            ,outerResource:Ext.isEmpty(outerResourceField.getValue())?null:outerResourceField.getValue()
            ,scheme:Ext.isEmpty(schemeField.getValue())?null:schemeField.getValue()
        };

        return condition;
    }

});